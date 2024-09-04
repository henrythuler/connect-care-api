package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.repositories.EspecialidadeRepository;
import com.connectCare.connectCareApi.services.GenericService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeServiceImpl implements GenericService<Especialidade> {

    @Autowired
    private EspecialidadeRepository repository;

    @Override
    public Especialidade create(Especialidade especialidade) {
        return repository.save(especialidade);
    }

    @Override
    public Especialidade getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Especialidade não encontrada..."));
    }

    @Override
    public List<Especialidade> getAll() {
        return repository.findAll();
    }

    @Override
    public Especialidade update(Especialidade especialidade) {
        Especialidade especialidadeEncontrada = repository.getReferenceById(especialidade.getId());
        BeanUtils.copyProperties(especialidade, especialidadeEncontrada);
        return repository.save(especialidadeEncontrada);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
