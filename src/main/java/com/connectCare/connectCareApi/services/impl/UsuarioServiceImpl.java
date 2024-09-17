package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.exceptions.UsuarioNaoEncontradoException;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.repositories.UsuarioRepository;
import com.connectCare.connectCareApi.services.GenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements GenericService<Usuario> {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario create(Usuario usuario) {
		if(!usuario.getRole().equalsIgnoreCase("MEDICO") || !usuario.getRole().equalsIgnoreCase("PACIENTE"))
			throw new IllegalArgumentException("Role do usuário deve ser PACIENTE ou MEDICO");
		usuario.setRole(usuario.getRole().toUpperCase());
		return repository.save(usuario);
	}

	@Override
	public Usuario getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuariosEncontrados = repository.findAll();
		if(usuariosEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Usuário");
		return usuariosEncontrados;
	}

	@Override
	public Usuario update(Usuario usuario) {
		try{
			Usuario usuarioEncontrado = repository.getReferenceById(usuario.getId());

			usuarioEncontrado.setEmail(usuario.getEmail());
			usuarioEncontrado.setSenha(usuario.getSenha());

			return repository.save(usuarioEncontrado);
		}catch (EntityNotFoundException e){
			throw new UsuarioNaoEncontradoException(usuario.getId());
		}catch(DataIntegrityViolationException e){
			throw new OperacaoBancoDeDadosException("Email já cadastrado!");
		}catch(Exception e){
			throw new OperacaoBancoDeDadosException();
		}
	}

	@Override
	public void delete(Integer id) {
		try{
			Usuario usuarioEncontrado = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
			repository.delete(usuarioEncontrado);
		}catch(DataIntegrityViolationException e) {
			throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois esse usuário está relacionado com algum paciente ou médico.");
		}

	}
	
}
