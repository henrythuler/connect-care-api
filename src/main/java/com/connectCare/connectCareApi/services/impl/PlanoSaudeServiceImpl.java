package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.NaoAutorizadoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.exceptions.PlanoSaudeNaoEncontradoException;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.PlanoSaude;
import com.connectCare.connectCareApi.repositories.PlanoSaudeRepository;
import com.connectCare.connectCareApi.services.GenericService;

import com.connectCare.connectCareApi.utils.UsuarioAutenticado;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlanoSaudeServiceImpl implements GenericService<PlanoSaude> {

    @Autowired
    private PlanoSaudeRepository repository;
    
    @Autowired
    private PacienteServiceImpl pacienteService;

    @Override
    public PlanoSaude create(PlanoSaude planoSaude) {
    	try {
    		Paciente pacienteEncontrado = pacienteService.getById(planoSaude.getPaciente().getId());

            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(pacienteEncontrado.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

        	planoSaude.setPaciente(pacienteEncontrado);
            return repository.save(planoSaude);
		} catch (DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo numero da carteirinha já cadastrado!");
		}
    	
    }

    @Override
    public PlanoSaude getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new PlanoSaudeNaoEncontradoException(id));
    }

    public PlanoSaude getByPacienteId(Integer id) {
        PlanoSaude planoSaudeEncontrado = repository.findByPacienteId(id);
        Paciente pacientePlano = planoSaudeEncontrado.getPaciente();

        //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
        if(!Objects.equals(pacientePlano.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
            throw new NaoAutorizadoException();
        }

        return planoSaudeEncontrado;
    }

    @Override
    public List<PlanoSaude> getAll() {
        //Verificando se o atual usuário é ADMIN
        if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new NaoAutorizadoException();
        }
    	List<PlanoSaude> planosSaudeEncontrados = repository.findAll();
    	if(planosSaudeEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Plano de Saúde");
        return planosSaudeEncontrados;
    }

    @Override
    public PlanoSaude update(PlanoSaude planoSaude) {
    	try {
    		PlanoSaude planoSaudeEncontrado = repository.getReferenceById(planoSaude.getId());

            Paciente pacientePlano = planoSaudeEncontrado.getPaciente();

            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(pacientePlano.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

    		planoSaudeEncontrado.setConvenio(planoSaude.getConvenio());
    		planoSaudeEncontrado.setPlano(planoSaude.getPlano());
    		planoSaudeEncontrado.setNumCarteirinha(planoSaude.getNumCarteirinha());
    		
            return repository.save(planoSaudeEncontrado);
		} catch (EntityNotFoundException e){
			throw new PlanoSaudeNaoEncontradoException(planoSaude.getId());
		} catch(DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo número da carteirinha já cadastrado!");
        } catch (Exception e) {
        	throw new OperacaoBancoDeDadosException();
		}
        
    }

    @Override
    public void delete(Integer id) {
    	try {
    		PlanoSaude planoSaudeEncontrado = repository.findById(id).orElseThrow(() -> new PlanoSaudeNaoEncontradoException(id));

            Paciente pacientePlano = planoSaudeEncontrado.getPaciente();

            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(pacientePlano.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

    		repository.delete(planoSaudeEncontrado);
		} catch (DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir o plano de saúde.");
		}
        
    }
}
