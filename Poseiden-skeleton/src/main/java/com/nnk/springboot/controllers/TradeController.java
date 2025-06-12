package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITrade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TradeController {
    private ITrade tradeService;
    // TODO: Inject Trade service
    // TODO: refac : Verifier les exceptions
    // TODO: BUG : POST Faut-il pour les redirects ajouter id ?

    //fait à verifier
    // TODO: find all Trade, add to model
    // TODO: check data valid and save to db, after saving return Trade list
    // TODO: get Trade by Id and to model then show to the form
    // TODO: check required fields, if valid call service to update Trade and return Trade list
    // TODO: Find Trade by Id and delete the Trade, return to Trade list


    @RequestMapping("/trade/list")
    public String home(Model model) {
        try {
            List<Trade> trades = tradeService.getAllTrade();
            model.addAttribute("trades", trades);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) return "trade/add";

        try{
            tradeService.saveTrade(trade);
            return "redirect:/trade/list";
        } catch (Exception e) {
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            Trade trade = tradeService.getTradeById(id);
            model.addAttribute("trade", trade);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) return "trade/update/"+id;

        try{
            tradeService.updateTrade(id, trade);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        try{
            tradeService.deleteTradeById(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "redirect:/trade/list";
    }
}
