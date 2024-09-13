package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.PlanoSaude;
import com.connectCare.connectCareApi.repositories.PlanoSaudeRepository;
import com.connectCare.connectCareApi.services.GenericService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoSaudeServiceImpl implements GenericService<PlanoSaude> {

    @Autowired
    private PlanoSaudeRepository repository;
    
    @Autowired
    private PacienteServiceImpl pacienteService;

    @Override
    public PlanoSaude create(PlanoSaude planoSaude) {
    	Paciente pacienteEncontrado = pacienteService.getById(planoSaude.getPaciente().getId());
    	planoSaude.setPaciente(pacienteEncontrado);
        return repository.save(planoSaude);
    }

    @Override
    public PlanoSaude getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Plano de Saúde não encontrado..."));
    }

    @Override
    public List<PlanoSaude> getAll() {
        return repository.findAll();
    }

    @Override
    public PlanoSaude update(PlanoSaude planoSaude) {
        PlanoSaude planoSaudeEncontrado = repository.getReferenceById(planoSaude.getId());
        BeanUtils.copyProperties(planoSaude, planoSaudeEncontrado);
        return repository.save(planoSaudeEncontrado);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
