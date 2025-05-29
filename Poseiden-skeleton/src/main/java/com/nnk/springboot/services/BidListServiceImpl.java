package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.interfaces.IBid;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BidListServiceImpl implements IBid {
    private BidListRepository bidListRepository;

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

    private boolean validateBidExists(int id){
        return bidListRepository.existsById(id);
    }
}
