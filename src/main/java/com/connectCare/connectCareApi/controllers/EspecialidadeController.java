package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.services.impl.EspecialidadeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeServiceImpl service;

    @PostMapping
    public ResponseEntity<Especialidade> create(@RequestBody Especialidade especialidade){
        Especialidade novaEspecialidade = service.create(especialidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaEspecialidade.getId()).toUri();
        return ResponseEntity.created(location).body(novaEspecialidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> getById(@PathVariable Integer id){
        Especialidade especialidadeEncontrada = service.getById(id);
        return ResponseEntity.ok(especialidadeEncontrada);
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> getAll(){
        List<Especialidade> especialidadesEncontradas = service.getAll();
        return ResponseEntity.ok(especialidadesEncontradas);
    }

    @PutMapping
    public ResponseEntity<Especialidade> update(@RequestBody Especialidade especialidade){
        Especialidade especialidadeAtualizada = service.update(especialidade);
        return ResponseEntity.ok(especialidadeAtualizada);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
