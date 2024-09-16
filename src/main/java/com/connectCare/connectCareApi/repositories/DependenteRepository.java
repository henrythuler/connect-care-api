package com.connectCare.connectCareApi.repositories;

import com.connectCare.connectCareApi.models.entities.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

    List<Dependente> findByResponsavelId(Integer id);

}
