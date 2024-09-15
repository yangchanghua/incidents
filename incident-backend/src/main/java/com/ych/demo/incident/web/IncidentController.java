package com.ych.demo.incident.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ych.demo.incident.model.Incident;
import com.ych.demo.incident.service.IncidentService;
import com.ych.demo.incident.service.impl.IncidentNotFoundException;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/incidents")
@Tag(name = "Incident")
public class IncidentController
{
	private final IncidentService incidentService;

	@Autowired
	public IncidentController(IncidentService incidentService)
	{
		this.incidentService = incidentService;
	}

	@GetMapping
	public Page<Incident> getIncidents(@RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size)
	{
		Pageable pageable = PageRequest.of(page, size);

		return incidentService.getIncidentsByTitle(title != null ? title : "", pageable);
	}

	@GetMapping("/{id}")
	public Incident getIncidentById(@PathVariable Long id)
	{
		final Incident incident = incidentService.getIncidentById(id);
		if (incident == null)
		{
			throw new IncidentNotFoundException("wrong id: " + id);
		}
		return incident;
	}

	@PostMapping
	public Incident createIncident(@RequestBody Incident incident)
	{
		return incidentService.createIncident(incident);
	}

	@DeleteMapping("/{id}")
	public void deleteIncident(@PathVariable Long id)
	{
		incidentService.deleteIncident(id);
	}

	@PatchMapping("/{id}")
	public Incident createIncident(@PathVariable Long id, @RequestBody Incident incident)
	{
		return incidentService.updateIncident(id, incident);
	}

}
