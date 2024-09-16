package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateConsultaDTO;
import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Consulta;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.services.impl.ConsultaServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaServiceImpl service;

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody CreateConsultaDTO consulta){
        Consulta novaConsulta = new Consulta();
        Medico idMedico = new Medico();
        idMedico.setId(consulta.getIdMedico());

        Paciente idPaciente = new Paciente();
        idPaciente.setId(consulta.getIdPaciente());

        Disponibilidade idDisponibilidade = new Disponibilidade();
        idDisponibilidade.setId(consulta.getIdDisponibilidade());

        BeanUtils.copyProperties(consulta, novaConsulta);
        novaConsulta.setMedico(idMedico);
        novaConsulta.setPaciente(idPaciente);
        novaConsulta.setDisponibilidade(idDisponibilidade);

        novaConsulta = service.create(novaConsulta);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaConsulta.getId()).toUri();
        return ResponseEntity.created(location).body(novaConsulta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable Integer id){
        Consulta consultaEncontrada = service.getById(id);
        return ResponseEntity.ok(consultaEncontrada);
    }

    @GetMapping(value = "/paciente/{id}")
    public ResponseEntity<List<Consulta>> getByPacienteId(@PathVariable Integer id){
        List<Consulta> consultasEncontradas = service.getByPacienteId(id);
        return ResponseEntity.ok(consultasEncontradas);
    }

    @GetMapping(value = "/paciente/{id}/data")
    public ResponseEntity<List<Consulta>> getByPacienteIdData(@PathVariable Integer id, @RequestBody GetPorIntervaloDataDTO intervalo){
        List<Consulta> consultasEncontradas = service.getByPacienteIdData(id, intervalo);
        return ResponseEntity.ok(consultasEncontradas);
    }

    @GetMapping(value = "/medico/{id}")
    public ResponseEntity<List<Consulta>> getByMedicoId(@PathVariable Integer id){
        List<Consulta> consultasEncontradas = service.getByMedicoId(id);
        return ResponseEntity.ok(consultasEncontradas);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> getAll(){
        List<Consulta> consultasEncontradas = service.getAll();
        return ResponseEntity.ok(consultasEncontradas);
    }

    @PutMapping
    public ResponseEntity<Consulta> update(@RequestBody Consulta consulta){
        Consulta consultaAtualizada = service.update(consulta);
        return ResponseEntity.ok(consultaAtualizada);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}
