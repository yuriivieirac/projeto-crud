package com.projetospring.api_spring.resource;

import com.projetospring.api_spring.interfaces.IResource;
import com.projetospring.api_spring.model.Professor;
import com.projetospring.api_spring.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/professor")
public class ProfessorResource implements IResource<Professor, Integer> {

    @Autowired
    private ProfessorService professorService;

    /**
     * Endpoint para registrar um Professor
     * Resposta no formato JSON
     */
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Registra um novo professor",
            description = "Metodo para registrar um novo professor",
            tags = {"professor"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Professor criado", content = {@Content(schema = @Schema(implementation = Professor.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Dados inválidos")
    })
    @Override
    public Professor create(@RequestBody Professor entity) {
        return professorService.create(entity);
    }

    /**
     * Endpoint para listar professores por ID
     * Resposta no formato JSON
     */
    @GetMapping(
            value = "/{id}", ////Define o caminho da URL http://localhost:8080/api/professor/{id}
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Listar professores por id",
            description = "Metodo para listar os professores pelo id",
            tags = {"professor"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor encontrado", content = {@Content(schema = @Schema(implementation = Professor.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "204", description = "Nenhum professor encontrado")
    })
    @Override
    public Professor get(@PathVariable Integer id) {
        return professorService.get(id);
    }

    /**
     * Endpoint para listar todos os professores
     * Resposta no formato JSON
     */
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Listar todos os professores",
            description = "Metodo para listar todos os professores",
            tags = {"professor"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professores encontrados", content = {@Content(schema = @Schema(implementation = Professor.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public List<Professor> get() {
        return professorService.get();
    }

    /**
     * Endpoint para atualizar um professor pelo ID
     * Resposta no formato JSON
     */
    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Atuliza professor pelo ID",
            description = "Metodo para atualizar o professor pelo ID",
            tags = {"professor"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso", content = {@Content(schema = @Schema(implementation = Professor.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public Professor update(@PathVariable Integer id, @RequestBody Professor entity) {
        return professorService.update(id, entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Deleta professor pelo ID",
            description = "Metodo para deletar o professor pelo ID",
            tags = {"professor"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Professor deletado com sucesso", content = {@Content(schema = @Schema(implementation = Professor.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public void delete(@PathVariable Integer id) {
        professorService.delete(id);
    }
}
