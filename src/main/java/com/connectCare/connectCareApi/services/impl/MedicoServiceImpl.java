package com.connectCare.connectCareApi.services.impl;

import java.util.List;

import com.connectCare.connectCareApi.exceptions.MedicoNaoEncontradoException;
import com.connectCare.connectCareApi.exceptions.NenhumRegistroEncontradoException;
import com.connectCare.connectCareApi.exceptions.OperacaoBancoDeDadosException;
import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.repositories.MedicoRepository;
import com.connectCare.connectCareApi.services.GenericService;

import jakarta.persistence.EntityNotFoundException;

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
		try {
			Usuario usuarioEncontrado = usuarioService.getById(medico.getUsuario().getId());
			medico.setUsuario(usuarioEncontrado);

			Especialidade especialidadeEncontrada = especialidadeService.getById(medico.getEspecialidade().getId());
			medico.setEspecialidade(especialidadeEncontrada);

			return repository.save(medico);
		} catch (DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo CRM já cadastrado!");
		}
		
	}

	@Override
	public Medico getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new MedicoNaoEncontradoException(id));
	}

	@Override
	public List<Medico> getAll() {
		List<Medico> medicosEncontrados = repository.findAll();
		if(medicosEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Médico");
		return medicosEncontrados;
	}

	public List<Medico> getByEspecialidadeId(Integer id){
		List<Medico> medicosEncontrados = repository.findByEspecialidadeId(id);
		if(medicosEncontrados.isEmpty()) throw new NenhumRegistroEncontradoException("Médico");
		return medicosEncontrados;
	}

	@Override
	public Medico update(Medico medico) {
		try {
			Medico medicoEncontrado = repository.getReferenceById(medico.getId());
			medicoEncontrado.setCrm(medico.getCrm());
			medicoEncontrado.setNome(medico.getNome());
			medicoEncontrado.setGenero(medico.getGenero());
			medicoEncontrado.setTelefone(medico.getTelefone());
			medicoEncontrado.setEndereco(medico.getEndereco());
			medicoEncontrado.setPresencial(medico.getPresencial());
			medicoEncontrado.setTeleconsulta(medico.getTeleconsulta());
			medicoEncontrado.setValorDaConsulta(medico.getValorDaConsulta());
	        
	        return repository.save(medicoEncontrado);
		} catch (EntityNotFoundException e){
			throw new MedicoNaoEncontradoException(medico.getId());
		} catch(DataIntegrityViolationException e){
            throw new OperacaoBancoDeDadosException("Campo CRM já cadastrado!");
        } catch (Exception e) {
        	throw new OperacaoBancoDeDadosException();
		}
		
	}

	@Override
	public void delete(Integer id) {
		try {
			Medico medicoEncontrado = repository.findById(id).orElseThrow(() -> new MedicoNaoEncontradoException(id));
			repository.delete(medicoEncontrado);
		} catch (DataIntegrityViolationException e) {
            throw new OperacaoBancoDeDadosException("Não foi possível excluir, pois esse médico está relacionado com alguma disponibilidade ou Consulta.");
			
		}
		
	}
	
}
