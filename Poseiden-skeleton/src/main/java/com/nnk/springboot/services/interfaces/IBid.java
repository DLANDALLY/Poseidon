package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.BidList;
import jakarta.validation.Valid;

import java.util.List;

public interface IBid {
    List<BidList> getAllBids();

    void saveBid(@Valid BidList bid);

    BidList getBidById(Integer id);

    void updateBidList(Integer id, BidList bidList);

    void deleteBidListById(Integer id);
}
