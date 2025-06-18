package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITrade;
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
public class TradeController {
    private ITrade tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        try {
            List<Trade> trades = tradeService.getAllTrade();
            model.addAttribute("trades", trades);
        } catch (Exception e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            Trade trade = tradeService.getTradeById(id);
            model.addAttribute("trade", trade);
        } catch (Exception e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        try{
            tradeService.deleteTradeById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
        return "redirect:/trade/list";
    }
}
