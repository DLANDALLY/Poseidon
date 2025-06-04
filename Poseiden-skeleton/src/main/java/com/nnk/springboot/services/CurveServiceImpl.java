package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.interfaces.ICurve;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CurveServiceImpl implements ICurve {
    private CurvePointRepository curvePointRepository;
    private ModelMapper modelMapper;

    @Override
    public List<CurvePoint> getAllCurvePoint() {
        return curvePointRepository.findAll();
    }

    @Override
    public void saveCurvePoint(CurvePoint curvePoint) {
        if (validateCurvePointExists(curvePoint.getCurveId()))
            throw new IllegalArgumentException("Curve point already exists");

        curvePointRepository.save(curvePoint);
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("Id cannot be empty");

        return curvePointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CurvePoint with id " + id + " not found"));

    }

    @Override
    public void updateCurvePoint(Integer id, CurvePoint curvePoint) {
        CurvePoint curvePointBDD = getCurvePointById(id);

        if (curvePoint == null)
            throw new EntityNotFoundException("Curve Point cannot be null");
        if (curvePointBDD == null)
            throw new EntityNotFoundException("Update BidList error");

        modelMapper.map(curvePoint, curvePointBDD);
        curvePointRepository.save(curvePointBDD);
    }

    @Override
    public CurvePoint getCurvePointById(int id){
        if (id == 0)
            throw new IllegalArgumentException("Id cannot be empty");

        return curvePointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BidList with id " + id + " not found"));
    }

    @Override
    public void deleteCurvePointById(Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("Id can't be null");
        CurvePoint curvePoint = getCurvePointById(id);
        curvePointRepository.delete(curvePoint);
    }

    private boolean validateCurvePointExists(int id){
        return curvePointRepository.existsById(id);
    }

}
