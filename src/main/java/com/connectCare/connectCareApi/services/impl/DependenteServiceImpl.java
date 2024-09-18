package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.DependenteNaoEncontradoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.entities.Dependente;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.repositories.DependenteRepository;
import com.connectCare.connectCareApi.services.GenericService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenteServiceImpl implements GenericService<Dependente> {

    @Autowired
    private DependenteRepository repository;

    @Autowired
    private PacienteServiceImpl pacienteService;

    @Override
    public Dependente create(Dependente dependente) {
    	try {
    		Paciente pacienteEncontrado = pacienteService.getById(dependente.getResponsavel().getId());
            dependente.setResponsavel(pacienteEncontrado);

            return repository.save(dependente);
		} catch (DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo CPF já cadastrado!");
		}
        
    }

    @Override
    public Dependente getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new DependenteNaoEncontradoException(id));
    }

    public List<Dependente> getByResponsavelId(Integer id){
    	List<Dependente> dependentesEncontrados = repository.findByResponsavelId(id);
        if(dependentesEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Dependente");
        return dependentesEncontrados;
    }

    @Override
    public List<Dependente> getAll() {
    	List<Dependente> dependentesEncontrados = repository.findAll();
        if(dependentesEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Dependente");
        return dependentesEncontrados;
    }

    @Override
    public Dependente update(Dependente dependente) {
    	try{
            Dependente dependenteEncontrado = repository.getReferenceById(dependente.getId());

            dependenteEncontrado.setCpf(dependente.getCpf());
            dependenteEncontrado.setEndereco(dependente.getEndereco());
            dependenteEncontrado.setGenero(dependente.getGenero());
            dependenteEncontrado.setNome(dependente.getNome());
            dependenteEncontrado.setTelefone(dependente.getTelefone());
            dependenteEncontrado.setDataNascimento(dependente.getDataNascimento());

            return repository.save(dependenteEncontrado);
        }catch (EntityNotFoundException e){
            throw new DependenteNaoEncontradoException(dependente.getId());
        }catch(DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo CPF já cadastrado!");
        }catch(Exception e){
            throw new OperacaoBancoDeDadosException();
        }
    }

    @Override
    public void delete(Integer id) {
    	try {
    		Dependente dependenteEncontrado = repository.findById(id).orElseThrow(() -> new DependenteNaoEncontradoException(id));
    		repository.delete(dependenteEncontrado);
		} catch (DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois esse dependente está relacionado com alguma Consulta.");
			
		}
        
    }
}
