package com.connectCare.connectCareApi.repositories;

import com.connectCare.connectCareApi.models.entities.PlanoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoSaudeRepository extends JpaRepository<PlanoSaude, Integer> {
}
