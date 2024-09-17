package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.ConsultaNaoEncontradaException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Consulta;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.repositories.ConsultaRepository;
import com.connectCare.connectCareApi.services.GenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

        if(!consulta.getTipoConsulta().equalsIgnoreCase("TELECONSULTA") || !consulta.getTipoConsulta().equalsIgnoreCase("PRESENCIAL"))
            throw new IllegalArgumentException("Tipo da Consulta deve ser TELECONSULTA ou PRESENCIAL.");
        if(!consulta.getFormaAgendamento().equalsIgnoreCase("CONVENIO") || !consulta.getFormaAgendamento().equalsIgnoreCase("PARTICULAR"))
            throw new IllegalArgumentException("Forma de Agendamento deve ser CONVENIO ou PARTICULAR.");

        Paciente pacienteEncontrado = pacienteService.getById(consulta.getPaciente().getId());
        consulta.setPaciente(pacienteEncontrado);

        Medico medicoEncontrado = medicoService.getById(consulta.getMedico().getId());
        consulta.setMedico(medicoEncontrado);

        Disponibilidade disponibilidadeEncontrada = disponibilidadeService.getById(consulta.getDisponibilidade().getId());
        disponibilidadeEncontrada.setAgendado(true);
        disponibilidadeService.update(disponibilidadeEncontrada);

        consulta.setDisponibilidade(disponibilidadeEncontrada);

        return repository.save(consulta);
    }

    @Override
    public Consulta getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException(id));
    }

    public List<Consulta> getByPacienteId(Integer id){
        List<Consulta> consultasEncontradas = repository.findByPacienteId(id);

        if(consultasEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Consulta");
        return consultasEncontradas;
    }

    public List<Consulta> getByMedicoId(Integer id){
        List<Consulta> consultasEncontradas = repository.findByMedicoId(id);

        if(consultasEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Consulta");
        return consultasEncontradas;
    }

    public List<Consulta> getByPacienteIdData(Integer id, GetPorIntervaloDataDTO intervaloData){
        List<Consulta> consultasEncontradas = repository.findByPacienteIdAndDisponibilidadeDataDisponivelBetween(id, intervaloData.getInicio(), intervaloData.getFim());

        if(consultasEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Consulta");
        return consultasEncontradas;
    }

    @Override
    public List<Consulta> getAll() {
        List<Consulta> consultasEncontradas = repository.findAll();

        if(consultasEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Consulta");
        return consultasEncontradas;
    }

    @Override
    public Consulta update(Consulta consulta) {
        try{
            Consulta consultaEncontrada = repository.getReferenceById(consulta.getId());

            consultaEncontrada.setTipoConsulta(consulta.getTipoConsulta());
            consultaEncontrada.setFormaAgendamento(consulta.getFormaAgendamento());

            return repository.save(consultaEncontrada);
        }catch (EntityNotFoundException e){
            throw new ConsultaNaoEncontradaException(consulta.getId());
        }catch(Exception e){
            throw new OperacaoBancoDeDadosException();
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Consulta consultaEncontrada = repository.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException(id));
            repository.delete(consultaEncontrada);
        }catch(DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException();
        }
    }
}
