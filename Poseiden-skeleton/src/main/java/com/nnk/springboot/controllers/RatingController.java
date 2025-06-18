package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRating;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class RatingController {
    private IRating ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        try {
            List<Rating> ratings = ratingService.getAllRating();
            model.addAttribute("ratings", ratings);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) return "rating/add";

        try{
            ratingService.saveRating(rating);
            return "redirect:/rating/list";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "rating/add";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            Rating rating = ratingService.getRatingById(id);
            model.addAttribute("rating", rating);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) return "rating/update/"+id;

        try{
            ratingService.updateRating(id, rating);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        try{
            ratingService.deleteRatingById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
        return "redirect:/rating/list";
    }
}
