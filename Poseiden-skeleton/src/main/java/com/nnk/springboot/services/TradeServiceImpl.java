package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITrade;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl extends CrudServiceImpl<Trade, Integer> implements ITrade {

    public TradeServiceImpl(JpaRepository<Trade, Integer> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public List<Trade> getAllTrad() {
        return getAll();
    }

    @Override
    public void saveTrade(Trade trade) {
        if (trade == null)
            throw new IllegalArgumentException("Rating doesn't be null");

        saving(trade);
    }

    @Override
    public Trade getTradeById(Integer id) {
        return getById(id);
    }

    @Override
    public void updateTrade(Integer id, Trade trade) {
        update(id, trade);
    }

    @Override
    public void deleteTradeById(Integer id) {
        deleteById(id);
    }
}
