package com.ych.demo.incident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ych.demo.incident.model.Incident;


@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	Page<Incident> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
