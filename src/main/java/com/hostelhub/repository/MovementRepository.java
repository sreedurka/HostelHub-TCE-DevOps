package com.hostelhub.repository;
import com.hostelhub.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MovementRepository extends JpaRepository<Movement,Long>{long countByStatus(String status); List<Movement> findByRegisterNumberOrderByRequestedAtDesc(String registerNumber); List<Movement> findAllByOrderByRequestedAtDesc();}
