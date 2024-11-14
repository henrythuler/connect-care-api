package com.connectCare.connectCareApi.repositories;

import com.connectCare.connectCareApi.models.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    List<Consulta> findByPacienteId(Integer id);
    List<Consulta> findByPacienteIdAndDisponibilidadeDataDisponivelBetween(Integer id, LocalDate start, LocalDate end);
    List<Consulta> findByMedicoId(Integer id);

}
