package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateDependenteDTO;
import com.connectCare.connectCareApi.models.entities.Dependente;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.services.impl.DependenteServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dependentes")
public class DependenteController {

    @Autowired
    private DependenteServiceImpl service;

    @PostMapping
    public ResponseEntity<Dependente> create(@RequestBody CreateDependenteDTO dependente){
        Dependente novoDependente = new Dependente();
        Paciente pacienteResponsavel = new Paciente();
        pacienteResponsavel.setId(dependente.getIdResponsavel());

        BeanUtils.copyProperties(dependente, novoDependente);
        novoDependente.setResponsavel(pacienteResponsavel);

        novoDependente = service.create(novoDependente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoDependente.getId()).toUri();
        return ResponseEntity.created(location).body(novoDependente);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dependente> getById(@PathVariable Integer id){
        Dependente dependenteEncontrado = service.getById(id);
        return ResponseEntity.ok(dependenteEncontrado);
    }

    @GetMapping(value = "/responsavel/{id}")
    public ResponseEntity<List<Dependente>> getByResponsavelId(@PathVariable Integer id){
        List<Dependente> dependentesEncontrados = service.getByResponsavelId(id);
        return ResponseEntity.ok(dependentesEncontrados);
    }

    @GetMapping
    public ResponseEntity<List<Dependente>> getAll(){
        List<Dependente> dependentesEncontrados = service.getAll();
        return ResponseEntity.ok(dependentesEncontrados);
    }

    @PutMapping
    public ResponseEntity<Dependente> update(@RequestBody Dependente dependente){
        Dependente dependenteAtualizado = service.update(dependente);
        return ResponseEntity.ok(dependenteAtualizado);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
