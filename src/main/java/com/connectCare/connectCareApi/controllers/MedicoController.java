package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateMedicoDTO;
import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.MedicoServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoServiceImpl service;

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody CreateMedicoDTO medico){
        Medico novoMedico = new Medico();
        Usuario idUsuario = new Usuario();

        Especialidade idEspecialidade = new Especialidade();
        idEspecialidade.setId(medico.getIdEspecialidade());

        idUsuario.setId(medico.getIdUsuario());

        BeanUtils.copyProperties(medico, novoMedico);
        novoMedico.setUsuario(idUsuario);
        novoMedico.setEspecialidade(idEspecialidade);

        novoMedico = service.create(novoMedico);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoMedico.getId()).toUri();
        return ResponseEntity.created(location).body(novoMedico);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Medico> getById(@PathVariable Integer id){
        Medico medicoEncontrado = service.getById(id);
        return ResponseEntity.ok(medicoEncontrado);
    }

    @GetMapping(value = "/especialidade/{id}")
    public ResponseEntity<List<Medico>> getByEspecialidadeId(@PathVariable Integer id){
        List<Medico> medicosEncontrados = service.getByEspecialidadeId(id);
        return ResponseEntity.ok(medicosEncontrados);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> getAll(){
        List<Medico> medicosEncontrados = service.getAll();
        return ResponseEntity.ok(medicosEncontrados);
    }

    @PutMapping
    public ResponseEntity<Medico> update(@RequestBody Medico medico){
        Medico medicoAtualizado = service.update(medico);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}
