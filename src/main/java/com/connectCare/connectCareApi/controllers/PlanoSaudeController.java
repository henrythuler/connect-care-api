package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreatePlanoSaudeDTO;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.PlanoSaude;
import com.connectCare.connectCareApi.services.impl.PlanoSaudeServiceImpl;

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
@RequestMapping("/api/v1/planosdesaude")
public class PlanoSaudeController {

    @Autowired
    private PlanoSaudeServiceImpl service;

    @Operation(summary = "Cadastra um novo plano de saúde",
            description = "Cadastra um novo plano de saúde de acordo com as informações passadas no formato JSON.",
            tags = {"Planos de Saúde"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PlanoSaude.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentException.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404",  content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PlanoSaude> create(@RequestBody @Valid CreatePlanoSaudeDTO planoSaude){
    	PlanoSaude novoPlanoSaude = new PlanoSaude();
    	Paciente idPaciente = new Paciente();
        idPaciente.setId(planoSaude.getIdPaciente());
        
        BeanUtils.copyProperties(planoSaude, novoPlanoSaude);
        novoPlanoSaude.setPaciente(idPaciente);
    	
        novoPlanoSaude = service.create(novoPlanoSaude);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPlanoSaude.getId()).toUri();
        return ResponseEntity.created(location).body(novoPlanoSaude);
    }

    @Operation(summary = "Recupera um plano de saúde de acordo com o seu ID",
            description = "Faz a recuperação dos dados de um plano de saúde conforme o ID passado na URL.",
            tags = {"Planos de Saúde"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PlanoSaude.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PlanoSaude> getById(@PathVariable Integer id){
        PlanoSaude planoSaudeEncontrado = service.getById(id);
        return ResponseEntity.ok(planoSaudeEncontrado);
    }

    @Operation(summary = "Recupera um plano de saúde de acordo com o ID do paciente",
            description = "Faz a recuperação dos dados de um plano de saúde conforme o ID do paciente passado na URL.",
            tags = {"Planos de Saúde"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PlanoSaude.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/paciente/{id}", produces = "application/json")
    public ResponseEntity<PlanoSaude> getByPacienteId(@PathVariable Integer id){
        PlanoSaude planoSaudeEncontrado = service.getByPacienteId(id);
        return ResponseEntity.ok(planoSaudeEncontrado);
    }

    @Operation(summary = "Recupera todos os planos de saúde",
            description = "Faz a recuperação dos dados de todos os planos de saúde cadastrados. (Somente ADMIN pode acessar essa rota)",
            tags = {"Planos de Saúde"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PlanoSaude.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PlanoSaude>> getAll(){
        List<PlanoSaude> planosSaudeEncontrados = service.getAll();
        return ResponseEntity.ok(planosSaudeEncontrados);
    }

    @Operation(summary = "Atualiza um plano de saúde",
            description = "Atualiza as informações de um plano de saúde conforme o que foi passado no formato JSON.",
            tags = {"Planos de Saúde"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PlanoSaude.class)
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
    public ResponseEntity<PlanoSaude> update(@RequestBody PlanoSaude planoSaude){
        PlanoSaude planoSaudeAtualizado = service.update(planoSaude);
        return ResponseEntity.ok(planoSaudeAtualizado);
    }

    @Operation(summary = "Remove um plano de saúde",
            description = "Remove as informações de um plano de saúde conforme o ID passado na URL.",
            tags = {"Planos de Saúde"},
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
