package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateDependenteDTO;
import com.connectCare.connectCareApi.models.entities.Dependente;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.services.impl.DependenteServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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

    @Operation(summary = "Cadastra um novo dependente",
            description = "Cadastra uma novo dependente de acordo com as informações passadas no formato JSON.",
            tags = {"Dependentes"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Dependente.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                ))
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Dependente> create(@RequestBody @Valid CreateDependenteDTO dependente){
        Dependente novoDependente = new Dependente();
        Paciente pacienteResponsavel = new Paciente();
        pacienteResponsavel.setId(dependente.getIdResponsavel());

        BeanUtils.copyProperties(dependente, novoDependente);
        novoDependente.setResponsavel(pacienteResponsavel);

        novoDependente = service.create(novoDependente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoDependente.getId()).toUri();
        return ResponseEntity.created(location).body(novoDependente);
    }

    @Operation(summary = "Recupera um dependente de acordo com o seu ID",
            description = "Faz a recuperação dos dados de uma dependente conforme o ID passado na URL.",
            tags = {"Dependentes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Dependente.class)
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
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Dependente> getById(@PathVariable Integer id){
        Dependente dependenteEncontrado = service.getById(id);
        return ResponseEntity.ok(dependenteEncontrado);
    }
    
    @Operation(summary = "Recupera todos os dependente de acordo com o ID do responsavel",
            description = "Faz a recuperação dos dados de todos os dependentes conforme o ID do responsavel passado na URL.",
            tags = {"Dependentes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Dependente.class)
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

    @GetMapping(value = "/responsavel/{id}", produces = "application/json")
    public ResponseEntity<List<Dependente>> getByResponsavelId(@PathVariable Integer id){
        List<Dependente> dependentesEncontrados = service.getByResponsavelId(id);
        return ResponseEntity.ok(dependentesEncontrados);
    }

    @Operation(summary = "Recupera todos os dependentes",
            description = "Faz a recuperação dos dados de todos os dependentes cadastradas. (Somente ADMIN pode acessar essa rota)",
            tags = {"Dependentes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Dependente.class)
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
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Dependente>> getAll(){
        List<Dependente> dependentesEncontrados = service.getAll();
        return ResponseEntity.ok(dependentesEncontrados);
    }
    
    @Operation(summary = "Atualiza um dependente",
            description = "Atualiza as informações de um dependente conforme o que foi passado no formato JSON.",
            tags = {"Dependentes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Dependente.class)
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
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Dependente> update(@RequestBody Dependente dependente){
        Dependente dependenteAtualizado = service.update(dependente);
        return ResponseEntity.ok(dependenteAtualizado);
    }
    
    @Operation(summary = "Remove um dependente de acordo com o seu ID",
            description = "Remove as informações de um dependente conforme o ID passado na URL.",
            tags = {"Dependentes"},
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
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
