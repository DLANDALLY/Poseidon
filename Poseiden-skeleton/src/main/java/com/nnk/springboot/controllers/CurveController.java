package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurve;
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

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class CurveController {
    private ICurve curveService;

    @GetMapping("/curvePoint/list")
    public String home(Model model) {
        try {
            List<CurvePoint> curvePoints = curveService.getAllCurvePoint();
            model.addAttribute("curvePoints", curvePoints);
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) return "curvePoint/add";

        try{
            curveService.saveCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            CurvePoint curvePoint = curveService.getCurvePointById(id);
            model.addAttribute("curvePoint", curvePoint);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()) return "curvePoint/update";

        try{
            curveService.updateCurvePoint(id, curvePoint);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        try{
            curveService.deleteCurvePointById(id);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return "redirect:/curvePoint/list";
    }
}
