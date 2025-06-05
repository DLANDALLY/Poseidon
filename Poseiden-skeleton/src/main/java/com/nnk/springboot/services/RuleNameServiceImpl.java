package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleName;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameServiceImpl extends CrudServiceImpl<RuleName, Integer> implements IRuleName {

    public RuleNameServiceImpl(JpaRepository<RuleName, Integer> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public List<RuleName> getAllRuleName() {
        return getAll();
    }

    @Override
    public void saveRuleName(RuleName ruleName) {
        if (ruleName == null)
            throw new IllegalArgumentException("Rule name doesn't be null");

        saving(ruleName);
    }

    @Override
    public RuleName getRuleNameById(Integer id) {
        return getById(id);
    }

    @Override
    public void updateRuleName(Integer id, RuleName ruleName) {
        update(id, ruleName);
    }

    @Override
    public void deleteRuleNameById(Integer id) {
        deleteById(id);
    }
}
