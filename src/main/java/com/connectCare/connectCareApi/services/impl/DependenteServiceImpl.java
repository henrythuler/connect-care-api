package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.DependenteNaoEncontradoException;
import com.connectCare.connectCareApi.exceptions.NaoAutorizadoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.entities.Dependente;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.repositories.DependenteRepository;
import com.connectCare.connectCareApi.services.GenericService;
import com.connectCare.connectCareApi.utils.UsuarioAutenticado;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    		
    		//Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(pacienteEncontrado.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }
    		
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
    	
    	Paciente pacienteResponsavel = pacienteService.getById(id);
    	
    	//Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao responsavel
        if(!Objects.equals(pacienteResponsavel.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
            throw new NaoAutorizadoException();
        }
    	
        if(dependentesEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Dependente");
        return dependentesEncontrados;
    }

    @Override
    public List<Dependente> getAll() {
    	//Verificando se o atual usuário é ADMIN
        if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new NaoAutorizadoException();
        }
    	
    	List<Dependente> dependentesEncontrados = repository.findAll();
        if(dependentesEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Dependente");
        return dependentesEncontrados;
    }

    @Override
    public Dependente update(Dependente dependente) {
    	try{
            Dependente dependenteEncontrado = repository.getReferenceById(dependente.getId());
            
            Paciente pacienteResponsavel = dependenteEncontrado.getResponsavel();
            
            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao responsavel
            if(!Objects.equals(pacienteResponsavel.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

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
    		
    		Paciente pacienteResponsavel = dependenteEncontrado.getResponsavel();
    		
    		//Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao responsavel
            if(!Objects.equals(pacienteResponsavel.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }
    		
    		repository.delete(dependenteEncontrado);
		} catch (DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois esse dependente está relacionado com alguma Consulta.");
			
		}
        
    }
}
