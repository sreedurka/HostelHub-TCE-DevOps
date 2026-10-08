package com.hostelhub.repository;
import com.hostelhub.model.HostelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HostelUserRepository extends JpaRepository<HostelUser,Long>{Optional<HostelUser> findByUsername(String username); Optional<HostelUser> findByRegisterNumber(String registerNumber);}
