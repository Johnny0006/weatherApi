package net.atos.weatherApi.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.atos.weatherApi.entity.City;
import net.atos.weatherApi.restTemplate.Weather;
import net.atos.weatherApi.restTemplate.WeatherTemplate;
import net.atos.weatherApi.service.CityService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Slf4j
public class CityController {
	
	private CityService service;
	
	@Autowired
	public CityController(CityService service) {
		this.service = service;
	}
	
	
	@GetMapping
	public ResponseEntity<List<City>> findAll(){
		
		List<City> cities = service.findAll();
		cities.forEach(v -> v.add(
					linkTo(CityController.class).slash(v.getId()).withSelfRel()));
		
		return ResponseEntity.ok(cities);

	}
	
	@PostMapping
	public ResponseEntity<List<Weather>> post(@RequestBody List<String> cityNames) throws TimeoutException{
		
	
		List<Weather> list = new ArrayList<>();
		for(String name : cityNames) {
			Optional<City> city = service.find(name);
			if(city.isPresent())
				list.add(WeatherTemplate.getWeather(city.get()));
		}
		
		if(list.isEmpty()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Weather> find(@PathVariable long id) throws TimeoutException{
		Optional<City> city = service.find(id);
		if(city.isEmpty()) return ResponseEntity.notFound().build();
		else return ResponseEntity.ok(WeatherTemplate.getWeather(city.get()));
	}
	
	
}
