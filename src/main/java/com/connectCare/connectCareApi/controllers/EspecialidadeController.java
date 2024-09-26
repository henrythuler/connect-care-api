package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.services.impl.EspecialidadeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    
    @Operation(summary = "Cadastra uma nova especialidade",
            description = "Cadastra uma nova especialidade de acordo com as informações passadas no formato JSON. (Somente ADMIN pode acessar essa rota)",
            tags = {"Especialidades"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class)
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

    @PostMapping
    public ResponseEntity<Especialidade> create(@RequestBody Especialidade especialidade){
        Especialidade novaEspecialidade = service.create(especialidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaEspecialidade.getId()).toUri();
        return ResponseEntity.created(location).body(novaEspecialidade);
    }
    
    @Operation(summary = "Recupera uma especialidade de acordo com o seu ID",
            description = "Faz a recuperação dos dados de uma especialidade conforme o ID passado na URL.",
            tags = {"Especialidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class)
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

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> getById(@PathVariable Integer id){
        Especialidade especialidadeEncontrada = service.getById(id);
        return ResponseEntity.ok(especialidadeEncontrada);
    }
    
    @Operation(summary = "Recupera todos as especialidades",
            description = "Faz a recuperação dos dados de todas as especialidades cadastradas.",
            tags = {"Especialidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class)
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

    @GetMapping
    public ResponseEntity<List<Especialidade>> getAll(){
        List<Especialidade> especialidadesEncontradas = service.getAll();
        return ResponseEntity.ok(especialidadesEncontradas);
    }
    
    @Operation(summary = "Atualiza uma Especialidade",
            description = "Atualiza as informações de uma Especialidade conforme o que foi passado no formato JSON. (Somente ADMIN pode acessar essa rota)",
            tags = {"Especialidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Especialidade.class)
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

    @PutMapping
    public ResponseEntity<Especialidade> update(@RequestBody Especialidade especialidade){
        Especialidade especialidadeAtualizada = service.update(especialidade);
        return ResponseEntity.ok(especialidadeAtualizada);
    }

    @Operation(summary = "Remove uma especialidade de acordo com o seu ID",
            description = "Remove as informações de uma especialidade conforme o ID passado na URL. (Somente ADMIN pode acessar essa rota)",
            tags = {"Especialidades"},
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
