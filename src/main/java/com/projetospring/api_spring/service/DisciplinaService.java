package com.projetospring.api_spring.service;

import com.projetospring.api_spring.interfaces.IService;
import com.projetospring.api_spring.model.Disciplina;
import com.projetospring.api_spring.repository.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DisciplinaService implements IService<Disciplina, Integer> {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Disciplina create(Disciplina entity) {
        log.info("Iniciando o cadastro da disciplina: {}", entity);
        return disciplinaRepository.save(entity);
    }

    @Override
    public Disciplina get(Integer id) {
        log.info("Iniciando a busca da disciplina pelo ID: {}", id);
        return disciplinaRepository
                .findById(id)
                .orElse(new Disciplina());
    }

    @Override
    public List<Disciplina> get() {
        log.info("Listando todas as disciplinas...");
        return disciplinaRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public Disciplina update(Integer id, Disciplina entity) {
        log.info("Iniciando a atualização da disciplina pelo ID: {}", id);
        //Verificando se existe a disciplina no banco de dados
        Disciplina disciplinaEncontrada = this.get(id);

        //Se a disciplina não for encontrada (ID == 0 ou null), retorne uma nova Disciplina
        if(disciplinaEncontrada.getId() != null){
            //Atualize os campos da disciplina encontrada com os dados do objeto entity
            disciplinaEncontrada.setNome(entity.getNome());

            return disciplinaRepository.save(disciplinaEncontrada);
        } else {
            throw new EntityNotFoundException("Disciplina com ID " + id + "não encontrada.");
        }
    }

    @Transactional(rollbackFor = Exception.class) //Se ocorrer alguma falha na transação, garante que as alterações no banco sejam revertidas.
    @Override
    public void delete(Integer id) {
        log.info("Iniciando o processo de exclusão com o ID: {}", id);
        disciplinaRepository.deleteById(id);
    }
}