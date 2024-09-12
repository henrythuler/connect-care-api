package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.models.entities.Dependente;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.repositories.DependenteRepository;
import com.connectCare.connectCareApi.services.GenericService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenteServiceImpl implements GenericService<Dependente> {

    @Autowired
    private DependenteRepository repository;

    @Autowired
    private PacienteServiceImpl pacienteService;

    @Override
    public Dependente create(Dependente dependente) {
        Paciente pacienteEncontrado = pacienteService.getById(dependente.getPaciente().getId());
        dependente.setPaciente(pacienteEncontrado);

        return repository.save(dependente);
    }

    @Override
    public Dependente getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Dependente não encontrado..."));
    }

    @Override
    public List<Dependente> getAll() {
        return repository.findAll();
    }

    @Override
    public Dependente update(Dependente dependente) {
        Dependente dependenteEncontrado = repository.getReferenceById(dependente.getId());
        BeanUtils.copyProperties(dependente, dependenteEncontrado);
        return repository.save(dependenteEncontrado);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
