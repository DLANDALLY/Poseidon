package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BidListServiceImplTest {
    @Mock
    private JpaRepository<BidList, Integer> repository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private BidListServiceImpl bidListService;

    @BeforeEach
    void setUp() {
        bidListService = new BidListServiceImpl(repository, modelMapper);
    }

    @Test
    void shouldGetAllBidsSuccessfully() {
        List<BidList> bidLists = List.of(new BidList(), new BidList());
        when(repository.findAll()).thenReturn(bidLists);

        List<BidList> result = bidListService.getAllBids();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldSaveBidSuccessfully() {
        BidList bidList = new BidList();

        bidListService.saveBid(bidList);

        verify(repository).save(bidList);
    }

    @Test
    void shouldGetBidByIdSuccessfully() {
        BidList bidList = new BidList();
        when(repository.findById(1)).thenReturn(Optional.of(bidList));

        BidList result = bidListService.getBidById(1);

        assertEquals(bidList, result);
    }

    @Test
    void shouldThrowExceptionWhenUpdateBibListIsNotFound(){
        BidList bidList = new BidList();

        assertThrows(IllegalArgumentException.class,
                () -> bidListService.updateBidList(300, bidList));
    }

    @Test
    void shouldThrowExceptionWhenDeleteBibListByIdIsNotFound(){
        assertThrows(IllegalArgumentException.class,
                () -> bidListService.deleteBidListById(300));
    }
}