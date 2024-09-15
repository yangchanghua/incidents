package com.ych.demo.incident.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ych.demo.incident.model.Incident;
import com.ych.demo.incident.repository.IncidentRepository;
import com.ych.demo.incident.service.IncidentService;


@Service
public class DefaultIncidentService implements IncidentService
{

	private final IncidentRepository incidentRepository;

	@Autowired
	public DefaultIncidentService(IncidentRepository incidentRepository) {
		this.incidentRepository = incidentRepository;
	}

	@Override
	public List<Incident> getAllIncidents() {
		return incidentRepository.findAll();
	}

	@Override
	public Page<Incident> getIncidentsByTitle(String title, Pageable pageable) {
		return incidentRepository.findByTitleContainingIgnoreCase(title, pageable);
	}

	@Override
	public Incident getIncidentById(Long id) {
		return incidentRepository.findById(id).orElse(null);
	}

	@Override
	public Incident createIncident(Incident incident) {
		return incidentRepository.save(incident);
	}

	@Override
	public void deleteIncident(Long id) {
		incidentRepository.deleteById(id);
	}

	@Override
	public Incident updateIncident(Long id, Incident updatedIncident) {
		Optional<Incident> existingIncidentOpt = incidentRepository.findById(id);

		if (existingIncidentOpt.isPresent()) {
			Incident existingIncident = existingIncidentOpt.get();
			existingIncident.setTitle(updatedIncident.getTitle());
			existingIncident.setDescription(updatedIncident.getDescription());
			existingIncident.setStatus(updatedIncident.getStatus());
			return incidentRepository.save(existingIncident);
		} else {
			throw new IncidentNotFoundException("Incident not found with id: " + id);
		}
	}
}
