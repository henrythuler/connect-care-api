package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.models.dtos.CreatePacienteDTO;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.repositories.PacienteRepository;
import com.connectCare.connectCareApi.services.GenericService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements GenericService<Paciente> {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    public Paciente create(Paciente paciente) {
        Usuario usuarioEncontrado = usuarioService.getById(paciente.getUsuario().getId());
        paciente.setUsuario(usuarioEncontrado);
        return repository.save(paciente);
    }

    @Override
    public Paciente getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    @Override
    public List<Paciente> getAll() {
        return repository.findAll();
    }

    @Override
    public Paciente update(Paciente paciente) {
        Paciente pacienteEncontrado = repository.getReferenceById(paciente.getId());
        BeanUtils.copyProperties(paciente, pacienteEncontrado);
        return repository.save(pacienteEncontrado);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
