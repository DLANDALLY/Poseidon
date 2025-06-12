package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBid;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl extends CrudServiceImpl<BidList, Integer> implements IBid {

    public BidListServiceImpl(JpaRepository<BidList, Integer> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public List<BidList> getAllBids() {
        return getAll();
    }

    @Override
    public void saveBid(BidList bid) {
        if (bid == null)
            throw new IllegalArgumentException("Bid cannot be null");

        saving(bid);
    }

    @Override
    public BidList getBidById(Integer id){
        return getById(id);
    }

    @Override
    public void updateBidList(Integer id, BidList bidList) {
        update(id, bidList);
    }

    @Override
    public void deleteBidListById(Integer id) {
        deleteById(id);
    }
}
