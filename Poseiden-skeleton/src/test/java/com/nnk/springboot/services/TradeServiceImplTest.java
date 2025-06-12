package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TradeServiceImplTest {
    @Mock
    private JpaRepository<Trade, Integer> repository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TradeServiceImpl tradeService;

    @BeforeEach
    void setUp() {
        tradeService = new TradeServiceImpl(repository, modelMapper);
    }

    @Test
    void getAllTrade() {
        List<Trade> trades = List.of(new Trade(), new Trade());
        when(repository.findAll()).thenReturn(trades);

        List<Trade> result = tradeService.getAllTrade();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void saveTrade() {
        Trade trade = new Trade();

        tradeService.saveTrade(trade);

        verify(repository).save(trade);
    }

    @Test
    void getTradeById() {
        Trade trade = new Trade();
        when(repository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.getTradeById(1);

        assertEquals(trade, result);
    }

    @Test
    void updateTrade() {
        Trade trade = new Trade();

        assertThrows(IllegalArgumentException.class,
                () -> tradeService.updateTrade(300, trade));
    }

    @Test
    void deleteTradeById() {
        assertThrows(IllegalArgumentException.class,
                () -> tradeService.deleteTradeById(300));
    }
}