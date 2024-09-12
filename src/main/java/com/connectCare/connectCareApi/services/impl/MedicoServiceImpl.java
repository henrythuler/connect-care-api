package com.connectCare.connectCareApi.services.impl;

import java.util.List;

import com.connectCare.connectCareApi.models.entities.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.repositories.MedicoRepository;
import com.connectCare.connectCareApi.services.GenericService;

@Service
public class MedicoServiceImpl implements GenericService<Medico> {
	
	@Autowired
	private MedicoRepository repository;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Override
	public Medico create(Medico medico) {
		Usuario usuarioEncontrado = usuarioService.getById(medico.getUsuario().getId());
		medico.setUsuario(usuarioEncontrado);

		return repository.save(medico);
	}

	@Override
	public Medico getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
	}

	@Override
	public List<Medico> getAll() {
		return repository.findAll();
	}

	@Override
	public Medico update(Medico medico) {
		Medico medicoEncontrado = repository.getReferenceById(medico.getId());
        BeanUtils.copyProperties(medico, medicoEncontrado);
        return repository.save(medicoEncontrado);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
}
