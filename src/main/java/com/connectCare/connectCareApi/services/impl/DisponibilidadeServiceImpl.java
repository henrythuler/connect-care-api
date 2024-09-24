package com.connectCare.connectCareApi.services.impl;

import com.connectCare.connectCareApi.exceptions.DisponibilidadeNaoEncontradaException;
import com.connectCare.connectCareApi.exceptions.NaoAutorizadoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.dtos.GetPorIntervaloDataDTO;
import com.connectCare.connectCareApi.models.entities.Disponibilidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.repositories.DisponibilidadeRepository;
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
public class DisponibilidadeServiceImpl implements GenericService<Disponibilidade> {
	
	@Autowired
	private DisponibilidadeRepository repository;

	@Autowired
	private MedicoServiceImpl medicoService;

	@Override
	public Disponibilidade create(Disponibilidade disponibilidade) {
		Medico medicoEncontrado = medicoService.getById(disponibilidade.getMedico().getId());

		//Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao médico
		if(!Objects.equals(medicoEncontrado.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
			throw new NaoAutorizadoException();
		}

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
		//Verificando se o atual usuário é ADMIN
		if(!UsuarioAutenticado.getUsuarioAutenticado().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
			throw new NaoAutorizadoException();
		}
		List<Disponibilidade> disponibilidadesEncontradas = repository.findAll();

		if(disponibilidadesEncontradas.isEmpty()) throw new NenhumRegistroEncontradoException("Disponibilidade");
		return disponibilidadesEncontradas;
	}

	@Override
	public Disponibilidade update(Disponibilidade disponibilidade) {
		try{
			Disponibilidade disponibilidadeEncontrada = repository.getReferenceById(disponibilidade.getId());

			Medico medicoDisponibilidade = disponibilidadeEncontrada.getMedico();

			//Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao médico
			if(!Objects.equals(medicoDisponibilidade.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
				throw new NaoAutorizadoException();
			}

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

			Medico medicoDisponibilidade = disponibilidadeEncontrada.getMedico();

			//Verificando se o ID do usuário autenticado é igual ao ID do usuário associado ao médico
			if(!Objects.equals(medicoDisponibilidade.getUsuario().getId(), UsuarioAutenticado.getUsuarioAutenticado().getId())){
				throw new NaoAutorizadoException();
			}

			repository.delete(disponibilidadeEncontrada);
		}catch(DataIntegrityViolationException e) {
			throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois essa disponibilidade está relacionada com alguma consulta.");
		}
	}
	
}
