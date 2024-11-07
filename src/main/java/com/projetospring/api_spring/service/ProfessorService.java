package com.projetospring.api_spring.service;

import com.projetospring.api_spring.interfaces.IService;
import com.projetospring.api_spring.model.Professor;
import com.projetospring.api_spring.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProfessorService implements IService <Professor, Integer>{

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public Professor create(Professor entity) {
        log.info("Iniciando o cadastro do professor: {}", entity);
        return professorRepository.save(entity);
    }

    @Override
    public Professor get(Integer id) {
        log.info("Iniciando a busca do professor pelo ID: {}", id);
        return professorRepository
                .findById(id)
                .orElse(new Professor());
    }

    @Override
    public List<Professor> get() {
        log.info("Listando todos os professores...");
        return professorRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public Professor update(Integer id, Professor entity) {
        log.info("Iniciando a atualização do profesor com ID: {}", id);
        //Verificando se existe o professor no banco de dados
        Professor professorEncontrado = this.get(id);

        //Se o professor não for encontrada (ID == 0 ou null), retorne uma nova Disciplina
        if(professorEncontrado != null){
            //Atualize os campos do professor encontrado com os dados do objeto entity
            professorEncontrado.setNome(entity.getNome());
            return professorRepository.save(professorEncontrado);
        }
        else {
            throw new EntityNotFoundException("Professor com o ID " +id+ "não foi encontrado.");
        }
    }

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public void delete(Integer id) {
        log.info("Iniciando o processo de exclusão do professor com ID: {}", id);
        professorRepository.deleteById(id);
    }
}