package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurve;
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
public class CurveController {
    private ICurve curveService;
    // TODO: Verifier les exceptions

    //fait à verifier
    // TODO: find all Curve Point, add to model
    // TODO: check data valid and save to db, after saving return Curve list
    // TODO: get CurvePoint by Id and to model then show to the form
    // TODO: check required fields, if valid call service to update Curve and return Curve list
    // TODO: Find Curve by Id and delete the Curve, return to Curve list

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        try {
            List<CurvePoint> curvePoints = curveService.getAllCurvePoint();
            model.addAttribute("curvePoints", curvePoints);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
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
            return "curvePoint/list";
        } catch (Exception e) {
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try {
            CurvePoint curvePoint = curveService.getCurvePointById(id);
            model.addAttribute("curvePoint", curvePoint);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
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
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        try{
            curveService.deleteCurvePointById(id);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }

        return "redirect:/curvePoint/list";
    }
}
