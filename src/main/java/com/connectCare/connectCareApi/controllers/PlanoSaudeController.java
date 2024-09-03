package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.entities.PlanoSaude;
import com.connectCare.connectCareApi.services.impl.PlanoSaudeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/planosdesaude")
public class PlanoSaudeController {

    @Autowired
    private PlanoSaudeServiceImpl service;

    @PostMapping
    public ResponseEntity<PlanoSaude> create(@RequestBody PlanoSaude planoSaude){
        PlanoSaude novoPlanoSaude = service.create(planoSaude);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPlanoSaude.getId()).toUri();
        return ResponseEntity.created(location).body(novoPlanoSaude);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlanoSaude> getById(@PathVariable Integer id){
        PlanoSaude planoSaudeEncontrado = service.getById(id);
        return ResponseEntity.ok(planoSaudeEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<PlanoSaude>> getAll(){
        List<PlanoSaude> planosSaudeEncontrados = service.getAll();
        return ResponseEntity.ok(planosSaudeEncontrados);
    }

    @PutMapping
    public ResponseEntity<PlanoSaude> update(@RequestBody PlanoSaude planoSaude){
        PlanoSaude planoSaudeAtualizado = service.update(planoSaude);
        return ResponseEntity.ok(planoSaudeAtualizado);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}