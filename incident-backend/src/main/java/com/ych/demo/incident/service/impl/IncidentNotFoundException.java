package com.ych.demo.incident.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Incident not found")
public class IncidentNotFoundException extends RuntimeException {
	public IncidentNotFoundException(String message) {
		super(message);
	}
}
