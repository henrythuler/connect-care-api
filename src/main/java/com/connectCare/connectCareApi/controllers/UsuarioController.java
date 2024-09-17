package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.UsuarioDTO;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody Usuario usuario){
        Usuario novoUsuario = service.create(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(novoUsuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(location).body(usuarioDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Integer id){
        Usuario usuarioEncontrado = service.getById(id);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioEncontrado);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        List<UsuarioDTO> usuariosEncontrados = service.getAll().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(usuariosEncontrados);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> update(@RequestBody Usuario usuario){
        Usuario usuarioAtualizado = service.update(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioAtualizado);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
