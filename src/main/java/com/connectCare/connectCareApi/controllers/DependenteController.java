package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.entities.Dependente;
import com.connectCare.connectCareApi.services.impl.DependenteServiceImpl;
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
    public ResponseEntity<Dependente> create(@RequestBody Dependente dependente){
        Dependente novoDependente = service.create(dependente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoDependente.getId()).toUri();
        return ResponseEntity.created(location).body(novoDependente);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dependente> getById(@PathVariable Integer id){
        Dependente dependenteEncontrado = service.getById(id);
        return ResponseEntity.ok(dependenteEncontrado);
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
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}
