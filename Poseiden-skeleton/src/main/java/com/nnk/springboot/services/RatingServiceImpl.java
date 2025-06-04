package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRating;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public class RatingServiceImpl extends CrudServiceImpl<Rating, Integer>  implements IRating {

    public RatingServiceImpl(JpaRepository<Rating, Integer> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public List<Rating> getAllRating(){
        return getAll();
    }

    @Override
    public void saveRating(Rating rating) {
        if (rating == null)
            throw new IllegalArgumentException("Rating doesn't be null");

        saving(rating);
    }

    @Override
    public Rating getRatingById(Integer id) {
        return getById(id);
    }

    @Override
    public void updateRating(Integer id, Rating rating) {
        update(id,rating);
    }

    @Override
    public void deleteRatingById(Integer id) {
        deleteById(id);
    }


}
