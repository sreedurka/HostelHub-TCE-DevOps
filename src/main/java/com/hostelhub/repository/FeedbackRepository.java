package com.hostelhub.repository;
import com.hostelhub.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface FeedbackRepository extends JpaRepository<Feedback,Long>{List<Feedback> findByRegisterNumberOrderByCreatedAtDesc(String registerNumber);}
