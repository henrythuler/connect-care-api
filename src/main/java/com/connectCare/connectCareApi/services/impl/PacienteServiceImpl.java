package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.NaoAutorizadoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.exceptions.PacienteNaoEncontradoException;
import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.repositories.PacienteRepository;
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
public class PacienteServiceImpl implements GenericService<Paciente> {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    public Paciente create(Paciente paciente) {
    	try {

            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(paciente.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

    		Usuario usuarioEncontrado = usuarioService.getById(paciente.getUsuario().getId());
            paciente.setUsuario(usuarioEncontrado);
            return repository.save(paciente);
		} catch (DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Paciente já cadastrado!");
		}
        
    }

    @Override
    public Paciente getById(Integer id) {
        Paciente pacienteEncontrado = repository.findById(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));

        //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
        if(!Objects.equals(pacienteEncontrado.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
            throw new NaoAutorizadoException();
        }

        return pacienteEncontrado;
    }

    @Override
    public List<Paciente> getAll() {
        //Verificando se o atual usuário é ADMIN
        if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new NaoAutorizadoException();
        }
        List<Paciente> pacientesEncontrados = repository.findAll();
        if(pacientesEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Paciente");
        return pacientesEncontrados;
    }

    @Override
    public Paciente update(Paciente paciente) {
        try{
            Paciente pacienteEncontrado = repository.getReferenceById(paciente.getId());

            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(pacienteEncontrado.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

            pacienteEncontrado.setCpf(paciente.getCpf());
            pacienteEncontrado.setEndereco(paciente.getEndereco());
            pacienteEncontrado.setGenero(paciente.getGenero());
            pacienteEncontrado.setNome(paciente.getNome());
            pacienteEncontrado.setTelefone(paciente.getTelefone());
            pacienteEncontrado.setDataNascimento(paciente.getDataNascimento());

            return repository.save(pacienteEncontrado);
        }catch (EntityNotFoundException e){
            throw new PacienteNaoEncontradoException(paciente.getId());
        }catch(DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo CPF já cadastrado!");
        }catch(Exception e){
            throw new OperacaoBancoDeDadosException();
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Paciente pacienteEncontrado = repository.findById(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));

            //Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao paciente
            if(!Objects.equals(pacienteEncontrado.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
                throw new NaoAutorizadoException();
            }

            repository.delete(pacienteEncontrado);
        }catch(DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois esse paciente está relacionado com algum dependente.");
        }
    }

}
