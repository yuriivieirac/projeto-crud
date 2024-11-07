package com.projetospring.api_spring.service;

import com.projetospring.api_spring.interfaces.IService;
import com.projetospring.api_spring.model.Aluno;
import com.projetospring.api_spring.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service //Indica que é uma camada de serviço
public class AlunoService implements IService<Aluno, Integer> {

    @Autowired //Repositório que gerencia a persistência de Aluno
    private AlunoRepository alunoRepository;

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public Aluno create(Aluno entity) {
        log.info("Iniciando o cadastro do aluno: {}", entity);
        return alunoRepository.save(entity); //Salva o aluno e retorna
    }

    @Override
    public Aluno get(Integer id) {
        log.info("Iniciando a busca do aluno pelo ID: {}", id);
        Optional<Aluno> alunoEncontrado = alunoRepository.findById(id); //Tentando encontrar o aluno pelo id
        if(alunoEncontrado.isPresent()){  // Verifica se o aluno foi encontrado
            return alunoEncontrado.get(); //Retorna o aluno encontrado
        } else {
            return new Aluno(); //Retorna um novo aluno caso não encontre
        }
    }

    @Override
    public List<Aluno> get() {
        log.info("Listando todos os alunos...");
        return alunoRepository.findAll(); //Retorna todos os alunos do repositório
    }

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public Aluno update(Integer id, Aluno entity) {
        log.info("Iniciando a atualização do aluno com ID: {}", id);
        Aluno alunoEncontrado = this.get(id); //Busca o aluno pelo id

        if(alunoEncontrado != null && alunoEncontrado.getId() != 0){ //Verifica se possui um id valido
            entity.setId(id); //Certifica que o id não seja sobrescrito
            return alunoRepository.save(entity); //Salva o aluno com novas informações
        } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado com o ID" + id);
        }
    }

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public void delete(Integer id) {
        log.info("Iniciando o processo de exclusão do aluno com o ID: {}", id);
        alunoRepository.deleteById(id); //Deletando aluno pelo ID
    }
}