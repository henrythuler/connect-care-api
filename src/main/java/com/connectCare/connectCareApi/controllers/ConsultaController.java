package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.entities.Consulta;
import com.connectCare.connectCareApi.services.impl.ConsultaServiceImpl;
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
    public ResponseEntity<Consulta> create(@RequestBody Consulta consulta){
        Consulta novoConsulta = service.create(consulta);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoConsulta.getId()).toUri();
        return ResponseEntity.created(location).body(novoConsulta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable Integer id){
        Consulta consultaEncontrada = service.getById(id);
        return ResponseEntity.ok(consultaEncontrada);
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
