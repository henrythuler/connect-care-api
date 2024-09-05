package com.connectCare.connectCareApi.controllers;

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
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        Usuario novoUsuario = service.create(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(location).body(novoUsuario);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id){
        Usuario usuarioEncontrado = service.getById(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuariosEncontrados = service.getAll();
        return ResponseEntity.ok(usuariosEncontrados);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        Usuario usuarioAtualizado = service.update(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

}
