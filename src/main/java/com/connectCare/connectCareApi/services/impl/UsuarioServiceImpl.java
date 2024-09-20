package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.exceptions.UsuarioNaoEncontradoException;
import com.connectCare.connectCareApi.models.entities.UserInfoDetails;
import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.repositories.UsuarioRepository;
import com.connectCare.connectCareApi.services.GenericService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements GenericService<Usuario>, UserDetailsService {
	
	private final UsuarioRepository repository;
	
	private final PasswordEncoder encoder;
	
	public UsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(username);
		if(!usuario.isPresent()) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		  return usuario.map(UserInfoDetails::new)
				  .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado.")); 
	}

	@Override
	@Transactional
	public Usuario create(Usuario usuario) {
		try{
			if(!usuario.getRole().equalsIgnoreCase("MEDICO") && !usuario.getRole().equalsIgnoreCase("PACIENTE"))
				throw new IllegalArgumentException("Role do usuário deve ser PACIENTE ou MEDICO");
			usuario.setRole(usuario.getRole().toUpperCase());
			usuario.setPassword(encoder.encode(usuario.getPassword()));
			return repository.save(usuario);
		}catch(DataIntegrityViolationException e) {
			throw new OperacaoBancoDeDadosException("Email já cadastrado!");
		}
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
	@Transactional
	public Usuario update(Usuario usuario) {
		try{
			Usuario usuarioEncontrado = repository.getReferenceById(usuario.getId());

			usuarioEncontrado.setEmail(usuario.getEmail());
			usuarioEncontrado.setPassword(usuario.getPassword());

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
