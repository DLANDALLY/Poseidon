package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.interfaces.IBid;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BidListServiceImpl implements IBid {
    private BidListRepository bidListRepository;
    private ModelMapper modelMapper;

    @Override
    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList saveBid(BidList bid) {
        if (validateBidExists(bid.getBidListId()))
            throw new IllegalArgumentException("Bid already exists");

        return bidListRepository.save(bid);
    }

    @Override
    public BidList getBidById(int id){
        if (id == 0)
            throw new IllegalArgumentException("Id cannot be empty");

        return bidListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BidList with id " + id + " not found"));
    }

    @Override
    public void updateBidList(BidList bidList) {
        BidList bidBDD = getBidById(bidList.getBidListId());

        if (bidBDD == null)
            throw new EntityNotFoundException("Update BidList error");

        modelMapper.map(bidList, bidBDD);
        bidListRepository.save(bidBDD);
    }

    @Override
    public void deleteBidListById(int id) {
        if (id == 0)
            throw new IllegalArgumentException("Id can't be null");
        BidList bidList = getBidById(id);
        bidListRepository.delete(bidList);
    }

    private boolean validateBidExists(int id){
        return bidListRepository.existsById(id);
    }
}
