package com.nnk.springboot.services;

import com.nnk.springboot.services.interfaces.ICrud;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudServiceImpl<T, ID> implements ICrud<T,ID> {
    private final JpaRepository<T, ID> repository;
    private final ModelMapper modelMapper;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void saving(T obj) {
        repository.save(obj);
    }

    @Override
    public void update(ID id, T obj) {
        if (obj == null)
            throw new IllegalArgumentException("Entity with ID " + id + " not found");

        T objectBDD = getById(id);
        modelMapper.map(obj, objectBDD);
        repository.save(objectBDD);
    }

    @Override
    public T getById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource with ID " + id + " not found"));
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id))
            throw new IllegalArgumentException("Entity with ID " + id + " not found");

        repository.deleteById(id);
    }
}

