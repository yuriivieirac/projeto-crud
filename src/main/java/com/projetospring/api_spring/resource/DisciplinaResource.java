package com.projetospring.api_spring.resource;

import com.projetospring.api_spring.interfaces.IResource;
import com.projetospring.api_spring.model.Disciplina;
import com.projetospring.api_spring.service.DisciplinaService;
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
@RequestMapping("api/v1/disciplina")
public class DisciplinaResource implements IResource<Disciplina, Integer> {

    @Autowired
    private DisciplinaService disciplinaService;

    /**
     * Endpoint para criar uma nova disciplina
     * Resposta no formato JSON
     */
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Cria uma nova disciplina",
            description = "Metodo responsável por criar uma nova disciplina",
            tags = {"disciplina"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Disciplina criada", content = {@Content(schema = @Schema(implementation = Disciplina.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public Disciplina create(@RequestBody Disciplina entity) {
        return disciplinaService.create(entity);
    }

    /**
     * Endpoint para listar a disciplina pelo ID
     * Resposta no formato JSON
     */
    @GetMapping(
            value = "/{id}", //Define o caminho da URL http://localhost:8080/api/disciplina/{id}
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Recupera uma disciplina pelo ID",
            description = "Metodo responsável por pegar uma disciplina pelo ID",
            tags = {"disciplina"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de disciplinas", content = {@Content(schema = @Schema(implementation = Disciplina.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "204", description = "Nenhuma disciplina encontrado")
    })
    @Override
    public Disciplina get(@PathVariable Integer id) {
        return disciplinaService.get(id);
    }

    /**
     * Endpoint para listar todas as disciplinas
     * Resposta no formato JSON
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Lista todas as disciplinas",
            description = "Metodo responsável por listar todas as disciplinas",
            tags = {"disciplina"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de disciplinas", content = {@Content(schema = @Schema(implementation = Disciplina.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public List<Disciplina> get() {
        return disciplinaService.get();
    }

    /**
     * Endpoint para atualizar uma disciplina pelo ID
     * Resposta no formato JSON
     */
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Atualiza uma disciplina",
            description = "Metodo para atualizar uma disciplina pelo ID",
            tags = {"disciplina"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso", content = {@Content(schema = @Schema(implementation = Disciplina.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public Disciplina update(@PathVariable Integer id, @RequestBody Disciplina entity) {
        return disciplinaService.update(id, entity);
    }

    /**
     * Endpoint para deletar uma disciplina pelo ID
     * Resposta no formato JSON
     */
    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deleta uma disciplina",
            description = "Metodo responsavel por deletar uma disciplina através do ID",
            tags = {"disciplina"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Disciplina deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @Override
    public void delete(@PathVariable Integer id) {
        disciplinaService.delete(id);
    }
}
