package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Trade;
import jakarta.validation.Valid;

import java.util.List;

public interface ITrade {
    List<Trade> getAllTrad();

    void saveTrade(@Valid Trade trade);

    Trade getTradeById(Integer id);

    void updateTrade(Integer id, @Valid Trade trade);

    void deleteTradeById(Integer id);
}
