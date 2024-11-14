package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.EspecialidadeNaoEncontradaException;
import com.connectCare.connectCareApi.exceptions.NaoAutorizadoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.repositories.EspecialidadeRepository;
import com.connectCare.connectCareApi.services.GenericService;
import com.connectCare.connectCareApi.utils.UsuarioAutenticado;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeServiceImpl implements GenericService<Especialidade> {

    @Autowired
    private EspecialidadeRepository repository;

    @Override
    public Especialidade create(Especialidade especialidade) {
    	//Verificando se o atual usuário é ADMIN
        if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new NaoAutorizadoException();
        }
        
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
            
            //Verificando se o atual usuário é ADMIN
            if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
                throw new NaoAutorizadoException();
            }

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
            
            //Verificando se o atual usuário é ADMIN
            if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
                throw new NaoAutorizadoException();
            }
            
            repository.delete(especialidadeEncontrada);
        }catch(DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois essa especialidade está relacionada com algum médico.");
        }
    }
}
