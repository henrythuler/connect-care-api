package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.EspecialidadeNaoEncontradaException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.repositories.EspecialidadeRepository;
import com.connectCare.connectCareApi.services.GenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeServiceImpl implements GenericService<Especialidade> {

    @Autowired
    private EspecialidadeRepository repository;

    @Override
    public Especialidade create(Especialidade especialidade) {
        return repository.save(especialidade);
    }

    @Override
    public Especialidade getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EspecialidadeNaoEncontradaException(id));
    }

    @Override
    public List<Especialidade> getAll() {
        List<Especialidade> especialidadesEncontradas = repository.findAll();

        if(especialidadesEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Especialidade");
        return especialidadesEncontradas;
    }

    @Override
    public Especialidade update(Especialidade especialidade) {
        try{
            Especialidade especialidadeEncontrada = repository.getReferenceById(especialidade.getId());

            especialidadeEncontrada.setNome(especialidade.getNome());

            return repository.save(especialidadeEncontrada);
        }catch (EntityNotFoundException e){
            throw new EspecialidadeNaoEncontradaException(especialidade.getId());
        }catch(Exception e){
            throw new OperacaoBancoDeDadosException();
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Especialidade especialidadeEncontrada = repository.findById(id).orElseThrow(() -> new EspecialidadeNaoEncontradaException(id));
            repository.delete(especialidadeEncontrada);
        }catch(DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois essa especialidade está relacionada com algum médico.");
        }
    }
}
