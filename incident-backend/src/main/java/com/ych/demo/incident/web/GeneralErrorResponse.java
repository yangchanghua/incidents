package com.ych.demo.incident.web;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralErrorResponse
{

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

}
