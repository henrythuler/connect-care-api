package com.connectCare.connectCareApi.services.impl;

import java.util.List;

import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Medico;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.repositories.DisponibilidadeRepository;
import com.connectCare.connectCareApi.services.GenericService;

@Service
public class DisponibilidadeServiceImpl implements GenericService<Disponibilidade> {
	
	@Autowired
	private DisponibilidadeRepository repository;

	@Autowired
	private MedicoServiceImpl medicoService;

	@Override
	public Disponibilidade create(Disponibilidade disponibilidade) {
		Medico medicoEncontrado = medicoService.getById(disponibilidade.getMedico().getId());
		disponibilidade.setMedico(medicoEncontrado);
		return repository.save(disponibilidade);
	}

	@Override
	public Disponibilidade getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));
	}

	public List<Disponibilidade> getByMedicoId(Integer id) {
		return repository.findByMedicoId(id);
	}

	public List<Disponibilidade> getByMedicoIdData(Integer id, GetPorIntervaloDataDTO intervaloData) {
		return repository.findByMedicoIdAndDataDisponivelBetween(id, intervaloData.getInicio(), intervaloData.getFim());
	}

	@Override
	public List<Disponibilidade> getAll() {
		return repository.findAll();
	}

	@Override
	public Disponibilidade update(Disponibilidade disponibilidade) {
		Disponibilidade disponibilidadeEncontrada = repository.getReferenceById(disponibilidade.getId());
        BeanUtils.copyProperties(disponibilidade, disponibilidadeEncontrada);
        return repository.save(disponibilidadeEncontrada);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
}
