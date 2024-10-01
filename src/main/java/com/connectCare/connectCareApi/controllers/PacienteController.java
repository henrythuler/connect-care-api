package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreatePacienteDTO;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.PacienteServiceImpl;
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
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl service;

    @Operation(summary = "Cadastra um novo paciente",
            description = "Cadastra um novo paciente de acordo com as informações passadas no formato JSON.",
            tags = {"Pacientes"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)
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
    public ResponseEntity<Paciente> create(@RequestBody @Valid CreatePacienteDTO paciente){
        Paciente novoPaciente = new Paciente();
        Usuario idUsuario = new Usuario();
        idUsuario.setId(paciente.getIdUsuario());

        BeanUtils.copyProperties(paciente, novoPaciente);

        novoPaciente.setUsuario(idUsuario);

        novoPaciente = service.create(novoPaciente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(location).body(novoPaciente);
    }

    @Operation(summary = "Recupera um paciente de acordo com o seu ID",
            description = "Faz a recuperação dos dados de um paciente conforme o ID passado na URL.",
            tags = {"Pacientes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Paciente> getById(@PathVariable Integer id){
        Paciente pacienteEncontrado = service.getById(id);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    @Operation(summary = "Recupera todos os pacientes",
            description = "Faz a recuperação dos dados de todos os pacientes cadastrados. (Somente ADMIN pode acessar essa rota)",
            tags = {"Pacientes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Paciente>> getAll(){
        List<Paciente> pacientesEncontrados = service.getAll();
        return ResponseEntity.ok(pacientesEncontrados);
    }

    @Operation(summary = "Atualiza um paciente",
            description = "Atualiza as informações de um paciente conforme o que foi passado no formato JSON.",
            tags = {"Pacientes"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)
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
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente){
        Paciente pacienteAtualizado = service.update(paciente);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @Operation(summary = "Remove um paciente",
            description = "Remove as informações de um paciente conforme o ID passado na URL.",
            tags = {"Médicos"},
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
