package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.components.JwtComponent;
import com.connectCare.connectCareApi.models.dtos.CreateUsuarioDTO;
import com.connectCare.connectCareApi.models.dtos.UsuarioDTO;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.UsuarioServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	
	private final UsuarioServiceImpl service;
	private final JwtComponent jwtComponent;
    private final AuthenticationManager authenticationManager; 
    UsuarioController(UsuarioServiceImpl service, JwtComponent jwtComponent, AuthenticationManager authenticationManager) { 
        this.service = service; 
        this.jwtComponent = jwtComponent; 
        this.authenticationManager = authenticationManager; 
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UsuarioDTO> create(@RequestBody CreateUsuarioDTO usuario){
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setPassword(usuario.getPassword());
        novoUsuario.setRole(usuario.getRole());
        
        novoUsuario = service.create(novoUsuario);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(novoUsuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(location).body(usuarioDTO);
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody Usuario authRequest) { 
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            String token = jwtComponent.generateToken(authRequest.getEmail());
            return ResponseEntity.ok(token); 
        }else { 
            throw new UsernameNotFoundException("Solicitação de usuário inválida!"); 
        }
    } 
    
    @GetMapping(value = "/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Integer id){
        Usuario usuarioEncontrado = service.getById(id);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioEncontrado);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping(value = "/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        List<UsuarioDTO> usuariosEncontrados = service.getAll().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(usuariosEncontrados);
    }

    @PutMapping(value = "/usuarios")
    public ResponseEntity<UsuarioDTO> update(@RequestBody Usuario usuario){
        Usuario usuarioAtualizado = service.update(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioAtualizado);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/usuarios/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
