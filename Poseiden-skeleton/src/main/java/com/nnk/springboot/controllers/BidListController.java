package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBid;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BidListController {
    private IBid bidService;

    //TODO : FT : comment gere t'on les redirection et les messages d'erreurs ??
    //TODO : refactor : model error message
    //TODO : BUG : il manque le parametre ID dans updateBidList()

    //fait a verifier
    // TODO: check data valid and save to db, after saving return bid list
    // TODO: get Bid by Id and to model then show to the form
    // TODO: check required fields, if valid call service to update Bid and return list Bid
    // TODO: Find Bid by Id and delete the bid, return to Bid list


    @RequestMapping("/bidList/list")
    public String home(Model model) {
        try{
            List<BidList> bidLists = bidService.getAllBids();
            model.addAttribute("bidLists", bidLists);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) return "bidList/add";

        try{
            bidService.saveBid(bid);
            return "bidList/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            BidList bidList = bidService.getBidById(id);
            model.addAttribute("bidList", bidList);

        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) return "bidList/update";

        try {
            bidService.updateBidList(bidList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        try{
            bidService.deleteBidListById(id);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "redirect:/bidList/list";
    }
}
