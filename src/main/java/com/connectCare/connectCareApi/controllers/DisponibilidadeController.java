package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateDisponibilidadeDTO;
import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.services.impl.DisponibilidadeServiceImpl;

import com.connectCare.connectCareApi.utils.UsuarioAutenticado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/disponibilidades")
public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeServiceImpl service;
    
    @Operation(summary = "Cadastra uma nova Disponibilidade",
            description = "Cadastra uma nova Disponibilidade de acordo com as informações passadas no formato JSON.",
            tags = {"Disponibilidades"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Disponibilidade.class)
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
    public ResponseEntity<Disponibilidade> create(@RequestBody CreateDisponibilidadeDTO disponibilidade){
        Disponibilidade novaDisponibilidade = new Disponibilidade();
        Medico idMedico = new Medico();
        idMedico.setId(disponibilidade.getIdMedico());

        BeanUtils.copyProperties(disponibilidade, novaDisponibilidade);
        novaDisponibilidade.setMedico(idMedico);

        novaDisponibilidade = service.create(novaDisponibilidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaDisponibilidade.getId()).toUri();
        return ResponseEntity.created(location).body(novaDisponibilidade);
    }
    
    @Operation(summary = "Recupera uma disponibilidade de acordo com o seu ID",
            description = "Faz a recuperação dos dados de uma disponibilidade conforme o ID passado na URL.",
            tags = {"Disponibilidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Disponibilidade.class)
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
    public ResponseEntity<Disponibilidade> getById(@PathVariable Integer id){
        Disponibilidade disponibilidadeEncontrado = service.getById(id);
        return ResponseEntity.ok(disponibilidadeEncontrado);
    }
    
    @Operation(summary = "Recupera todos as disponibilidades de acordo com o ID do médico",
            description = "Faz a recuperação dos dados de todas as disponibilidades cadastradas conforme o ID do médico passado na URL.",
            tags = {"Disponibilidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Disponibilidade.class)
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
    @GetMapping(value = "/medico/{id}", produces = "application/json")
    public ResponseEntity<List<Disponibilidade>> getByMedicoId(@PathVariable Integer id){
        List<Disponibilidade> disponibilidadesEncontradas = service.getByMedicoId(id);
        return ResponseEntity.ok(disponibilidadesEncontradas);
    }
    
    @Operation(summary = "Recupera todos as disponibilidades de acordo com o ID do médico e data",
            description = "Faz a recuperação dos dados de todas as disponibilidades cadastradas conforme o ID do médico e data passadas na URL.",
            tags = {"Disponibilidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Disponibilidade.class)
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
    @GetMapping(value = "/medico/{id}/data", produces = "application/json")
    public ResponseEntity<List<Disponibilidade>> getByMedicoIdData(@PathVariable Integer id, @RequestBody GetPorIntervaloDataDTO intervalo){
        List<Disponibilidade> disponibilidadesEncontradas = service.getByMedicoIdData(id, intervalo);
        return ResponseEntity.ok(disponibilidadesEncontradas);
    }
    
    @Operation(summary = "Recupera todos as disponibilidades",
            description = "Faz a recuperação dos dados de todas as disponibilidades cadastradas. (Somente ADMIN pode acessar essa rota)",
            tags = {"Disponibilidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Disponibilidade.class)
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
    public ResponseEntity<List<Disponibilidade>> getAll(){
        List<Disponibilidade> disponibilidadesEncontrados = service.getAll();
        System.out.println(UsuarioAutenticado.getUsuarioAutenticado().getId());
        return ResponseEntity.ok(disponibilidadesEncontrados);
    }
    
    @Operation(summary = "Atualiza uma disponibilidade",
            description = "Atualiza as informações de uma disponibilidade conforme o que foi passado no formato JSON.",
            tags = {"Disponibilidades"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = CreateDisponibilidadeDTO.class)
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
    public ResponseEntity<Disponibilidade> update(@RequestBody Disponibilidade disponibilidade){
        Disponibilidade disponibilidadeAtualizado = service.update(disponibilidade);
        return ResponseEntity.ok(disponibilidadeAtualizado);
    }

    @Operation(summary = "Remove uma disponibilidade de acordo com o seu ID",
            description = "Remove as informações de uma disponibilidade conforme o ID passado na URL.",
            tags = {"Disponibilidades"},
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
