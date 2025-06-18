package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBid;
import jakarta.persistence.EntityNotFoundException;
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

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class BidListController {
    private IBid bidService;

    @RequestMapping("/bidList/list")
    public String home(Principal principal, Model model) {
        try{
            List<BidList> bidLists = bidService.getAllBids();
            model.addAttribute("bidLists", bidLists);
        }catch (Exception e){
            log.error(e.getMessage());
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
            return "redirect:/bidList/list";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            BidList bidList = bidService.getBidById(id);
            model.addAttribute("bidList", bidList);

        } catch (IllegalArgumentException | EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) return "bidList/update";

        try {
            bidService.updateBidList(id, bidList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        try{
            bidService.deleteBidListById(id);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return "redirect:/bidList/list";
    }
}
