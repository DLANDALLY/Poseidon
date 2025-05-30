package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BidListServiceImplTest {
    @Mock
    private BidListRepository bidListRepository;
    @InjectMocks
    private BidListServiceImpl bidListService;

    @Test
    void shouldGetAllBidsSuccessfully() {
        // Given
        BidList bid1 = BidList.builder().account("Account1").type("Type1").bidQuantity(10d).build();
        BidList bid2 = BidList.builder().account("Account1").type("Type1").bidQuantity(10d).build();
        List<BidList> mockBids = List.of(bid1, bid2);

        Mockito.when(bidListRepository.findAll()).thenReturn(mockBids);

        // When
        List<BidList> result = bidListService.getAllBids();

        // Then
        assertEquals(2, result.size());
        assertEquals("Account1", result.get(0).getAccount());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void shouldSaveBidSuccessfully() {
        //Given
        BidList bid1 = BidList.builder().account("Account1").type("Type1").bidQuantity(10d).build();
        Mockito.when(bidListRepository.save(bid1)).thenReturn(bid1);

        //When
        BidList result = bidListService.saveBid(bid1);

        //Then
        assertEquals("Account1", result.getAccount());
        assertEquals("Type1", result.getType());
        assertEquals(10d, result.getBidQuantity());
        verify(bidListRepository, times(1)).save(bid1);
    }

    @Test
    void shouldGetBidByIdSuccessfully() {
        //Given
        BidList bid1 = BidList.builder().account("Account1").type("Type1").bidQuantity(10d).build();
        Mockito.when(bidListRepository.findById(1)).thenReturn(Optional.ofNullable(bid1));

        //When
        BidList result = bidListService.getBidById(1);

        //Then
        assertEquals("Account1", result.getAccount());
        assertEquals("Type1", result.getType());
        assertEquals(10d, result.getBidQuantity());
        verify(bidListRepository, times(1)).findById(1);
    }
}