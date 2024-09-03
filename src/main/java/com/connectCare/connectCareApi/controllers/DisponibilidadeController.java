package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.services.impl.DisponibilidadeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/disponibilidades")
public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeServiceImpl service;

    @PostMapping
    public ResponseEntity<Disponibilidade> create(@RequestBody Disponibilidade disponibilidade){
        Disponibilidade novoDisponibilidade = service.create(disponibilidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoDisponibilidade.getId()).toUri();
        return ResponseEntity.created(location).body(novoDisponibilidade);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Disponibilidade> getById(@PathVariable Integer id){
        Disponibilidade disponibilidadeEncontrado = service.getById(id);
        return ResponseEntity.ok(disponibilidadeEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<Disponibilidade>> getAll(){
        List<Disponibilidade> disponibilidadesEncontrados = service.getAll();
        return ResponseEntity.ok(disponibilidadesEncontrados);
    }

    @PutMapping
    public ResponseEntity<Disponibilidade> update(@RequestBody Disponibilidade disponibilidade){
        Disponibilidade disponibilidadeAtualizado = service.update(disponibilidade);
        return ResponseEntity.ok(disponibilidadeAtualizado);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}
