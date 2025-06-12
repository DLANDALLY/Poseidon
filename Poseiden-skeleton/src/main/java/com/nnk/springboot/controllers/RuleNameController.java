package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleName;
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
public class RuleNameController {
    private IRuleName ruleNameService;
    // TODO: refac : Verifier les exceptions
    // TODO: BUG : POST Faut-il pour les redirects ajouter id ?

    //fait à verifier
    // TODO: find all RuleName, add to model
    // TODO: check data valid and save to db, after saving return RuleName list
    // TODO: get RuleName by Id and to model then show to the form
    // TODO: check required fields, if valid call service to update RuleName and return RuleName list
    // TODO: Find RuleName by Id and delete the RuleName, return to Rule list


    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        try {
            List<RuleName> ruleName = ruleNameService.getAllRuleName();
            model.addAttribute("ruleNames", ruleName);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) return "ruleName/add";

        try{
            ruleNameService.saveRuleName(ruleName);
            return "redirect:/ruleName/list";
        } catch (Exception e) {
            return "ruleName/add";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            RuleName ruleName = ruleNameService.getRuleNameById(id);
            model.addAttribute("ruleName", ruleName);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) return "ruleName/update/"+id;

        try{
            ruleNameService.updateRuleName(id, ruleName);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        try{
            ruleNameService.deleteRuleNameById(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "redirect:/ruleName/list";
    }
}
