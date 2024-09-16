package com.connectCare.connectCareApi.services.impl;

import java.util.List;

import com.connectCare.connectCareApi.models.entities.Especialidade;
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

	@Autowired
	private EspecialidadeServiceImpl especialidadeService;

	@Override
	public Medico create(Medico medico) {
		Usuario usuarioEncontrado = usuarioService.getById(medico.getUsuario().getId());
		medico.setUsuario(usuarioEncontrado);

		Especialidade especialidadeEncontrada = especialidadeService.getById(medico.getEspecialidade().getId());
		medico.setEspecialidade(especialidadeEncontrada);

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

	public List<Medico> getByEspecialidadeId(Integer id){
		return repository.findByEspecialidadeId(id);
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
