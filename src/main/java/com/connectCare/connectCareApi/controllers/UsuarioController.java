package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.components.JwtComponent;
import com.connectCare.connectCareApi.models.dtos.CreateUsuarioDTO;
import com.connectCare.connectCareApi.models.dtos.LoginDTO;
import com.connectCare.connectCareApi.models.dtos.TokenDTO;
import com.connectCare.connectCareApi.models.dtos.UsuarioDTO;
import com.connectCare.connectCareApi.models.entities.UserInfoDetails;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    @Operation(summary = "Cadastra um novo usuário",
        description = "Cadastra um novo usuário de acordo com as informações passadas no formato JSON.",
        tags = {"Autenticação"},
        responses = {
            @ApiResponse(description = "Created", responseCode = "201", content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)
            )),
            @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentException.class)
            )),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
        ))
    })
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UsuarioDTO> create(@RequestBody @Valid CreateUsuarioDTO usuario){
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setPassword(usuario.getPassword());
        novoUsuario.setRole(usuario.getRole());
        
        novoUsuario = service.create(novoUsuario);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(novoUsuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(location).body(usuarioDTO);
    }

    @Operation(summary = "Login de usuário",
            description = "Faz o login de um usuário conforme as informações passadas no formato JSON.",
            tags = {"Autenticação"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentException.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TokenDTO> authenticateAndGetToken(@RequestBody @Valid LoginDTO authRequest) {
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            String token = jwtComponent.generateToken(authRequest.getEmail());
            LocalDateTime expiracao = LocalDateTime.ofInstant(jwtComponent.extractExpiration(token).toInstant(), ZoneId.systemDefault());
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            Integer id = userInfoDetails.getId();
            return ResponseEntity.ok(new TokenDTO(token, expiracao, id));
        }else { 
            throw new UsernameNotFoundException("Solicitação de usuário inválida!"); 
        }
    }

    @Operation(summary = "Recupera um usuário de acordo com o seu ID",
            description = "Faz a recuperação dos dados de um usuário conforme o ID passado na URL.",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/usuarios/{id}", produces = "application/json")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Integer id){
        Usuario usuarioEncontrado = service.getById(id);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioEncontrado);
        return ResponseEntity.ok(usuarioDTO);
    }

    @Operation(summary = "Recupera todos os usuários",
            description = "Faz a recuperação dos dados de todos os usuários cadastrados. (Somente ADMIN pode acessar essa rota)",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/usuarios", produces = "application/json")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        List<UsuarioDTO> usuariosEncontrados = service.getAll().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(usuariosEncontrados);
    }

    @Operation(summary = "Atualiza um usuário",
            description = "Atualiza as informações de um usuário conforme o que foi passado no formato JSON.",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @PutMapping(value = "/usuarios", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UsuarioDTO> update(@RequestBody Usuario usuario){
        Usuario usuarioAtualizado = service.update(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioAtualizado);
        return ResponseEntity.ok(usuarioDTO);
    }

    @Operation(summary = "Remove um usuário",
            description = "Remove as informações de um usuário conforme o ID passado na URL.",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @DeleteMapping("/usuarios/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
