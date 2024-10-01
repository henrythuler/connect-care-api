package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateConsultaDTO;
import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Consulta;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.services.impl.ConsultaServiceImpl;

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
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaServiceImpl service;
    
    @Operation(summary = "Cadastra uma nova Consulta",
            description = "Cadastra uma nova consulta de acordo com as informações passadas no formato JSON.",
            tags = {"Consultas"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    public ResponseEntity<Consulta> create(@RequestBody @Valid CreateConsultaDTO consulta){
        Consulta novaConsulta = new Consulta();
        Medico idMedico = new Medico();
        idMedico.setId(consulta.getIdMedico());

        Paciente idPaciente = new Paciente();
        idPaciente.setId(consulta.getIdPaciente());

        Disponibilidade idDisponibilidade = new Disponibilidade();
        idDisponibilidade.setId(consulta.getIdDisponibilidade());

        BeanUtils.copyProperties(consulta, novaConsulta);
        novaConsulta.setMedico(idMedico);
        novaConsulta.setPaciente(idPaciente);
        novaConsulta.setDisponibilidade(idDisponibilidade);

        novaConsulta = service.create(novaConsulta);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaConsulta.getId()).toUri();
        return ResponseEntity.created(location).body(novaConsulta);
    }

    @Operation(summary = "Recupera uma consulta de acordo com o seu ID",
            description = "Faz a recuperação dos dados de uma consulta conforme o ID passado na URL.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    public ResponseEntity<Consulta> getById(@PathVariable Integer id){
        Consulta consultaEncontrada = service.getById(id);
        return ResponseEntity.ok(consultaEncontrada);
    }
    
    @Operation(summary = "Recupera todas as consultas de acordo com o ID do paciente",
            description = "Faz a recuperação dos dados de todas as consultas conforme o ID do paciente passado na URL.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    @GetMapping(value = "/paciente/{id}", produces = "application/json")
    public ResponseEntity<List<Consulta>> getByPacienteId(@PathVariable Integer id){
        List<Consulta> consultasEncontradas = service.getByPacienteId(id);
        return ResponseEntity.ok(consultasEncontradas);
    }
    
    @Operation(summary = "Recupera uma consulta de acordo com o ID do paciente e a data",
            description = "Faz a recuperação dos dados de uma consulta conforme o ID do paciente passado na URL e a data.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    @GetMapping(value = "/paciente/{id}/data", produces = "application/json")
    public ResponseEntity<List<Consulta>> getByPacienteIdData(@PathVariable Integer id, @RequestBody @Valid GetPorIntervaloDataDTO intervalo){
        List<Consulta> consultasEncontradas = service.getByPacienteIdData(id, intervalo);
        return ResponseEntity.ok(consultasEncontradas);
    }

    @Operation(summary = "Recupera todas as consultas de acordo com o ID do médico",
            description = "Faz a recuperação dos dados de todas as consultas conforme o ID do médico passado na URL.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    public ResponseEntity<List<Consulta>> getByMedicoId(@PathVariable Integer id){
        List<Consulta> consultasEncontradas = service.getByMedicoId(id);
        return ResponseEntity.ok(consultasEncontradas);
    }
    
    @Operation(summary = "Recupera todas as consultas",
            description = "Faz a recuperação dos dados de todas as consultas cadastradas. (Somente ADMIN pode acessar essa rota)",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    public ResponseEntity<List<Consulta>> getAll(){
        List<Consulta> consultasEncontradas = service.getAll();
        return ResponseEntity.ok(consultasEncontradas);
    }

    @Operation(summary = "Atualiza uma consulta",
            description = "Atualiza as informações de uma consulta conforme o que foi passado no formato JSON.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)
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
    public ResponseEntity<Consulta> update(@RequestBody Consulta consulta){
        Consulta consultaAtualizada = service.update(consulta);
        return ResponseEntity.ok(consultaAtualizada);
    }

    @Operation(summary = "Remove uma consulta de acordo com o seu ID",
            description = "Remove as informações de uma consulta conforme o ID passado na URL.",
            tags = {"Consultas"},
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
