package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Rating;
import jakarta.validation.Valid;

import java.util.List;

public interface IRating {

    List<Rating> getAllRating();

    void saveRating(@Valid Rating rating);

    Rating getRatingById(Integer id);

    void updateRating(Integer id, @Valid Rating rating);

    void deleteRatingById(Integer id);
}
