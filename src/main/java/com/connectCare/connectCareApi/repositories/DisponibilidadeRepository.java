package com.connectCare.connectCareApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectCare.connectCareApi.models.entities.Disponibilidade;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Integer> {

    List<Disponibilidade> findByMedicoId(Integer id);
    List<Disponibilidade> findByMedicoIdAndDataDisponivelBetween(Integer id, LocalDate start, LocalDate end);

}
