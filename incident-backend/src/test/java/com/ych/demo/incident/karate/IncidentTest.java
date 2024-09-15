package com.ych.demo.incident.karate;

import org.junit.jupiter.api.Tag;

import com.intuit.karate.junit5.Karate;

@Tag("integration")
public class IncidentTest
{
	@Karate.Test
	Karate testIncidentApi() {
		return Karate.run("incident").relativeTo(getClass());
	}
}
