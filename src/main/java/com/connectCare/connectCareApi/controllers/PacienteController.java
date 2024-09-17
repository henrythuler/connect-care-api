package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreatePacienteDTO;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.PacienteServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl service;

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody CreatePacienteDTO paciente){
        Paciente novoPaciente = new Paciente();
        Usuario idUsuario = new Usuario();
        idUsuario.setId(paciente.getIdUsuario());

        BeanUtils.copyProperties(paciente, novoPaciente);

        novoPaciente.setUsuario(idUsuario);

        novoPaciente = service.create(novoPaciente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(location).body(novoPaciente);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Integer id){
        Paciente pacienteEncontrado = service.getById(id);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(){
        List<Paciente> pacientesEncontrados = service.getAll();
        return ResponseEntity.ok(pacientesEncontrados);
    }

    @PutMapping
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente){
        Paciente pacienteAtualizado = service.update(paciente);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
