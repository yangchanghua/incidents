package com.ych.demo.incident.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ych.demo.incident.model.Incident;


public interface IncidentService
{
	List<Incident> getAllIncidents();

	Page<Incident> getIncidentsByTitle(String title, Pageable pageable);

	Incident getIncidentById(Long id);

	Incident createIncident(Incident incident);

	void deleteIncident(Long id);

	Incident updateIncident(Long id, Incident updatedIncident);
}
