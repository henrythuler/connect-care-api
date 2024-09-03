package com.connectCare.connectCareApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectCare.connectCareApi.models.entities.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}
