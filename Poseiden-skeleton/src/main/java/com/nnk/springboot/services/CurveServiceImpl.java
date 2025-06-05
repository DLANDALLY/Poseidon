package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurve;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurveServiceImpl extends CrudServiceImpl<CurvePoint, Integer> implements ICurve {

    public CurveServiceImpl(JpaRepository<CurvePoint, Integer> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public List<CurvePoint> getAllCurvePoint() {
        return getAll();
    }

    @Override
    public void saveCurvePoint(CurvePoint curvePoint) {
        if (curvePoint == null)
            throw new IllegalArgumentException("Curve point doen't be null");

        saving(curvePoint);
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) {
        return getById(id);
    }

    @Override
    public void updateCurvePoint(Integer id, CurvePoint curvePoint) {
        update(id, curvePoint);
    }

    @Override
    public void deleteCurvePointById(Integer id) {
        deleteById(id);
    }
}
