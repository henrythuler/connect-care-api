package com.connectCare.connectCareApi.controllers;

import com.connectCare.connectCareApi.models.dtos.CreateMedicoDTO;
import com.connectCare.connectCareApi.models.dtos.GetMedicoDTO;
import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.services.impl.MedicoServiceImpl;
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
@RequestMapping("/api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoServiceImpl service;

    @Operation(summary = "Cadastra um novo médico",
            description = "Cadastra um novo médico de acordo com as informações passadas no formato JSON.",
            tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = GetMedicoDTO.class)
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
    public ResponseEntity<GetMedicoDTO> create(@RequestBody @Valid CreateMedicoDTO medico){
        Medico novoMedico = new Medico();
        Usuario idUsuario = new Usuario();

        Especialidade idEspecialidade = new Especialidade();
        idEspecialidade.setId(medico.getIdEspecialidade());

        idUsuario.setId(medico.getIdUsuario());

        novoMedico.setGenero(medico.getGenero().charAt(0));

        BeanUtils.copyProperties(medico, novoMedico);
        novoMedico.setUsuario(idUsuario);
        novoMedico.setEspecialidade(idEspecialidade);

        novoMedico = service.create(novoMedico);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoMedico.getId()).toUri();
        return ResponseEntity.created(location).body(new GetMedicoDTO(novoMedico));
    }

    @Operation(summary = "Recupera um médico de acordo com o seu ID",
            description = "Faz a recuperação dos dados de um médico conforme o ID passado na URL.",
            tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = GetMedicoDTO.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GetMedicoDTO> getById(@PathVariable Integer id){
        Medico medicoEncontrado = service.getById(id);
        GetMedicoDTO medicoDTO = new GetMedicoDTO(medicoEncontrado);
        return ResponseEntity.ok(medicoDTO);
    }

    @Operation(summary = "Recupera os médicos de acordo com o ID da especialidade",
            description = "Faz a recuperação dos dados dos médicos conforme o ID da especialidade passado na URL.",
            tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = GetMedicoDTO.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(value = "/especialidade/{id}", produces = "application/json")
    public ResponseEntity<List<GetMedicoDTO>> getByEspecialidadeId(@PathVariable Integer id){
        List<Medico> medicosEncontrados = service.getByEspecialidadeId(id);
        List<GetMedicoDTO> medicoDTOS = medicosEncontrados.stream().map(GetMedicoDTO::new).toList();
        return ResponseEntity.ok(medicoDTOS);
    }

    @Operation(summary = "Recupera todos os médicos",
            description = "Faz a recuperação dos dados de todos os médicos cadastrados. (Somente ADMIN pode acessar essa rota)",
            tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = GetMedicoDTO.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            ))
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<GetMedicoDTO>> getAll(){
        List<Medico> medicosEncontrados = service.getAll();
        List<GetMedicoDTO> medicoDTOS = medicosEncontrados.stream().map(GetMedicoDTO::new).toList();
        return ResponseEntity.ok(medicoDTOS);
    }

    @Operation(summary = "Atualiza um médico",
            description = "Atualiza as informações de um médico conforme o que foi passado no formato JSON.",
            tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content =
                        @Content(mediaType = "application/json", schema = @Schema(implementation = GetMedicoDTO.class)
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
    public ResponseEntity<GetMedicoDTO> update(@RequestBody Medico medico){
        Medico medicoAtualizado = service.update(medico);
        GetMedicoDTO medicoDTO = new GetMedicoDTO(medicoAtualizado);
        return ResponseEntity.ok(medicoDTO);
    }

    @Operation(summary = "Remove um médico",
            description = "Remove as informações de um médico conforme o ID passado na URL.",
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
