package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
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
class RatingServiceImplTest {
    @Mock
    private JpaRepository<Rating, Integer> repository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setUp() {
        ratingService = new RatingServiceImpl(repository, modelMapper);
    }

    @Test
    void shouldGetAllRatingSuccessfully() {
        List<Rating> ratings = List.of(new Rating(), new Rating());
        when(repository.findAll()).thenReturn(ratings);

        List<Rating> result = ratingService.getAllRating();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldSaveRatingSuccessfully() {
        Rating rating = new Rating();

        ratingService.saveRating(rating);

        verify(repository).save(rating);
    }

    @Test
    void shouldThrowExceptionWhenSaveRatingIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> ratingService.saveRating(null));
    }

    @Test
    void shouldGetRatingByIdSuccessfully() {
        Rating rating = new Rating();
        when(repository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.getRatingById(1);

        assertEquals(rating, result);
    }

    @Test
    void shouldThrowExceptionWhenUpdateRatingIsNotFound() {
        Rating rating = new Rating();

        assertThrows(IllegalArgumentException.class,
                () -> ratingService.updateRating(300, rating));
    }

    @Test
    void shouldDeleteRatingByIdSuccessfully() {
        assertThrows(IllegalArgumentException.class,
                () -> ratingService.deleteRatingById(300));
    }
}