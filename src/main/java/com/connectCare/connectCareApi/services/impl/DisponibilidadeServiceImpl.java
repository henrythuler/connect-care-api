package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.DisponibilidadeNaoEncontradaException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.repositories.DisponibilidadeRepository;
import com.connectCare.connectCareApi.services.GenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
		return repository.findById(id).orElseThrow(() -> new DisponibilidadeNaoEncontradaException(id));
	}

	public List<Disponibilidade> getByMedicoId(Integer id) {
		List<Disponibilidade> disponibilidadesEncontradas = repository.findByMedicoId(id);

		if(disponibilidadesEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Disponiblidade");
		return disponibilidadesEncontradas;
	}

	public List<Disponibilidade> getByMedicoIdData(Integer id, GetPorIntervaloDataDTO intervaloData) {
		List<Disponibilidade> disponibilidadesEncontradas = repository.findByMedicoIdAndDataDisponivelBetween(id, intervaloData.getInicio(),intervaloData.getFim());

		if(disponibilidadesEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Disponibilidade");
		return disponibilidadesEncontradas;
	}

	@Override
	public List<Disponibilidade> getAll() {
		List<Disponibilidade> disponibilidadesEncontradas = repository.findAll();

		if(disponibilidadesEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Disponibilidade");
		return disponibilidadesEncontradas;
	}

	@Override
	public Disponibilidade update(Disponibilidade disponibilidade) {
		try{
			Disponibilidade disponibilidadeEncontrada = repository.getReferenceById(disponibilidade.getId());

			disponibilidadeEncontrada.setAgendado(disponibilidade.isAgendado());
			disponibilidadeEncontrada.setDataDisponivel(disponibilidade.getDataDisponivel());
			disponibilidadeEncontrada.setHorarioDisponivel(disponibilidade.getHorarioDisponivel());

			return repository.save(disponibilidadeEncontrada);
		}catch (EntityNotFoundException e){
			throw new DisponibilidadeNaoEncontradaException(disponibilidade.getId());
		}catch(Exception e){
			throw new OperacaoBancoDeDadosException();
		}
	}

	@Override
	public void delete(Integer id) {
		try{
			Disponibilidade disponibilidadeEncontrada = repository.findById(id).orElseThrow(() -> new DisponibilidadeNaoEncontradaException(id));
			repository.delete(disponibilidadeEncontrada);
		}catch(DataIntegrityViolationException e) {
			throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois esse paciente está relacionado com algum dependente.");
		}
	}
	
}
