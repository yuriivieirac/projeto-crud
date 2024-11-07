package com.projetospring.api_spring.resource;

import com.projetospring.api_spring.interfaces.IResource;
import com.projetospring.api_spring.model.Aluno;
import com.projetospring.api_spring.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //Ajuda nos log do projeto
@RestController //Indica que a classe é um controlador REST
@RequestMapping("api/v1/aluno") //Mepeia as requisições para essa URL
@Tag(name = "aluno", description = "documentação do resource aluno")

public class AlunoResource implements IResource<Aluno, Integer>{

    @Autowired //Faz a injeção da dependência, permitindo que a classe tenha acesso a funcionalidade de serviço.
    private AlunoService alunoService;

    /**
     * Cria um novo aluno.
     * Metodo POST que recebe os dados do aluno no corpo da requisição e retorna o aluno criado
     * Detalhe: Aceita requisições do corpo JSON e XML e responde o usuário em formato JSON.
     */
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )

    //Documentando o Endpoint
    @Operation(
            summary = "Cria um aluno",
            description = "Método responsável por criar um aluno",
            tags = {"aluno"}
    )

    //Documentando as respostas do endpoint para criação de aluno
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso", content = {@Content(schema = @Schema(implementation = Aluno.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado") })
    @Override
    public Aluno create(@RequestBody Aluno entity) {
        return alunoService.create(entity);
    }

    /**
     * Recupera um aluno pelo ID.
     * Metodo GET que retorna os dados do aluno correspondente ao ID fornecido.
     * Resposta no formato JSON
     */
    @GetMapping(value = "/{id}", //Define o caminho da URL http://localhost:8080/api/aluno/{id}
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Recupera um aluno baseado no ID",
            description = "Método responsável por recuperar um aluno baseado no ID",
            tags = {"aluno"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de Alunos", content = {@Content(schema = @Schema(implementation = Aluno.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "204", description = "Nenhum aluno encontrado")
    })
    @Override
    public Aluno get(@PathVariable Integer id) {
        return alunoService.get(id);
    }

    /**
     * Lista todos os alunos
     * Resposta no formato JSON
     */
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Recupera lista de alunos",
            description = "Metodo responsável por recuperar a lista de alunos",
            tags = {"aluno"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de Alunos", content = {@Content(schema = @Schema(implementation = Aluno.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public List<Aluno> get() {
        return alunoService.get();
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Atualiza os dados de um aluno",
            description = "Metodo responsável por atualizar os dados do aluno buscado pelo ID",
            tags = {"aluno"}
    )
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Aluno.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }),
      @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
    })
    @Override
    public Aluno update(@PathVariable Integer id, @RequestBody Aluno entity) {
        return alunoService.update(id,entity);
    }

    /**
     * Deletar um registro com base no ID
     */
    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Deleta um aluno com base no ID",
            description = "Metodo responsável por deletar um aluno com base no ID",
            tags = {"aluno"}
    )
    @ApiResponses({
     @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso"),
     @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
     @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Override
    public void

    delete(@PathVariable Integer id) {
        alunoService.delete(id);
    }
}