package com.connectCare.connectCareApi.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectCare.connectCareApi.models.entities.Usuario;
import com.connectCare.connectCareApi.repositories.UsuarioRepository;
import com.connectCare.connectCareApi.services.GenericService;

@Service
public class UsuarioServiceImpl implements GenericService<Usuario> {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario create(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public Usuario getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

	@Override
	public List<Usuario> getAll() {
		return repository.findAll();
	}

	@Override
	public Usuario update(Usuario usuario) {
		Usuario usuarioEncontrado = repository.getReferenceById(usuario.getId());
        BeanUtils.copyProperties(usuario, usuarioEncontrado);
        return repository.save(usuarioEncontrado);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
}
