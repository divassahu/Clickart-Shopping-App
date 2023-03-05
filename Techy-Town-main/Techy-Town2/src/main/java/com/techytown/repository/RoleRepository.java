package com.techytown.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.enums.ERole;
import com.techytown.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  
	Optional<Role> findByName(ERole name);

}
