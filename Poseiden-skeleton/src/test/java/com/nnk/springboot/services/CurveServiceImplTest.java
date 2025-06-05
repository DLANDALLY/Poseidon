package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CurveServiceImplTest {
    @Mock
    private JpaRepository<CurvePoint, Integer> repository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CurveServiceImpl curveService;

    @BeforeEach
    void setUp() {
        curveService = new CurveServiceImpl(repository, modelMapper);
    }

    @Test
    void getAllCurvePoint() {
        List<CurvePoint> curvePoints = List.of(new CurvePoint(), new CurvePoint());
        when(repository.findAll()).thenReturn(curvePoints);

        List<CurvePoint> result = curveService.getAllCurvePoint();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void saveCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();

        curveService.saveCurvePoint(curvePoint);

        verify(repository).save(curvePoint);
    }

    @Test
    void getCurvePointById() {
        CurvePoint curvePoint = new CurvePoint();
        when(repository.findById(1)).thenReturn(Optional.of(curvePoint));

        CurvePoint result = curveService.getCurvePointById(1);

        assertEquals(curvePoint, result);
    }

    @Test
    void updateCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();

        assertThrows(IllegalArgumentException.class,
                () -> curveService.updateCurvePoint(300, curvePoint));
    }

    @Test
    void deleteCurvePointById() {
        assertThrows(IllegalArgumentException.class,
                () -> curveService.deleteCurvePointById(300));
    }
}