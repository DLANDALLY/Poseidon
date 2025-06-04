package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.RuleName;
import jakarta.validation.Valid;

import java.util.List;

public interface IRuleName {
    List<RuleName> getAllRuleName();

    void saveRuleName(@Valid RuleName ruleName);

    RuleName getRuleNameById(Integer id);

    void updateRuleName(Integer id, @Valid RuleName ruleName);

    void deleteRuleNameById(Integer id);
}
