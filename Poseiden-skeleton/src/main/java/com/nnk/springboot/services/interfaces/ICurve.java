package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.CurvePoint;
import jakarta.validation.Valid;

import java.util.List;

public interface ICurve {
    List<CurvePoint> getAllCurvePoint();

    void saveCurvePoint(@Valid CurvePoint curvePoint);

    CurvePoint getCurvePointById(Integer id);

    void updateCurvePoint(Integer id, @Valid CurvePoint curvePoint);

    void deleteCurvePointById(Integer id);
}
