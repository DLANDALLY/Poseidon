package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
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
class RuleNameServiceImplTest {
    @Mock
    private JpaRepository<RuleName, Integer> repository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @BeforeEach
    void setUp() {
        ruleNameService = new RuleNameServiceImpl(repository, modelMapper);
    }

    @Test
    void shouldGetAllRuleNameSuccessfully() {
        List<RuleName> ruleNames = List.of(new RuleName(), new RuleName());
        when(repository.findAll()).thenReturn(ruleNames);

        List<RuleName> result = ruleNameService.getAllRuleName();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldSaveRuleNameSuccessfully() {
        RuleName ruleName = new RuleName();

        ruleNameService.saveRuleName(ruleName);

        verify(repository).save(ruleName);
    }

    @Test
    void shouldGetRuleNameByIdSuccessfully() {
        RuleName ruleName = new RuleName();
        when(repository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName result = ruleNameService.getRuleNameById(1);

        assertEquals(ruleName, result);
    }

    @Test
    void shouldThrowExceptionWhenUpdateRuleNameIsNotFound() {
        RuleName ruleName = new RuleName();

        assertThrows(IllegalArgumentException.class,
                () -> ruleNameService.updateRuleName(300, ruleName));
    }

    @Test
    void shouldThrwExceptionWhenDeleteRuleNameByIdIsNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> ruleNameService.deleteRuleNameById(300));
    }
}