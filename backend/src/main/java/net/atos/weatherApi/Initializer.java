package net.atos.weatherApi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.atos.weatherApi.entity.City;
import net.atos.weatherApi.restTemplate.WeatherTemplate;
import net.atos.weatherApi.service.CityService;

@Component
public class Initializer {
	
	private CityService service;
	
	@Autowired
	public Initializer(CityService service) {
		this.service=service;
	}
	
	@PostConstruct
	private synchronized void Init() {
	}
	
}
