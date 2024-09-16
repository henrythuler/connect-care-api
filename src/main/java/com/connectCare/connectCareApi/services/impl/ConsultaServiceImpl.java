package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.models.entities.Consulta;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.repositories.ConsultaRepository;
import com.connectCare.connectCareApi.services.GenericService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements GenericService<Consulta> {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private PacienteServiceImpl pacienteService;

    @Autowired
    private MedicoServiceImpl medicoService;

    @Autowired
    private DisponibilidadeServiceImpl disponibilidadeService;

    @Override
    public Consulta create(Consulta consulta) {
        Paciente pacienteEncontrado = pacienteService.getById(consulta.getPaciente().getId());
        consulta.setPaciente(pacienteEncontrado);

        Medico medicoEncontrado = medicoService.getById(consulta.getMedico().getId());
        consulta.setMedico(medicoEncontrado);

        Disponibilidade disponibilidadeEncontrada = disponibilidadeService.getById(consulta.getDisponibilidade().getId());
        consulta.setDisponibilidade(disponibilidadeEncontrada);

        return repository.save(consulta);
    }

    @Override
    public Consulta getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada..."));
    }

    public List<Consulta> getByPacienteId(Integer id){
        return repository.findByPacienteId(id);
    }

    public List<Consulta> getByMedicoId(Integer id){
        return repository.findByMedicoId(id);
    }

    @Override
    public List<Consulta> getAll() {
        return repository.findAll();
    }

    @Override
    public Consulta update(Consulta consulta) {
        Consulta consultaEncontrada = repository.getReferenceById(consulta.getId());
        BeanUtils.copyProperties(consulta, consultaEncontrada);
        return repository.save(consultaEncontrada);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
