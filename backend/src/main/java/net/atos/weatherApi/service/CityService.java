package net.atos.weatherApi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.weatherApi.entity.City;
import net.atos.weatherApi.repository.CityRepository;


@Service
public class CityService {

	private CityRepository repository;
	
	@Autowired
	public CityService(CityRepository repository) {
		this.repository = repository;
	}
	
	public Optional<City> find(long id) {
		return repository.findById(id);
	}
	
	public Optional<City> find(String name) {
		return repository.findByName(name);
	}
	
	public Optional<City> findByNameAndId(String name, long id){
		return repository.findByNameIgnoreCaseAndId(name,id);
	}
	
	public List<City> findAll() {
		return repository.findAll();
	}
	
	@Transactional
	public City save(City city) {
		return repository.save(city);
	}
	
	@Transactional
	public void delete(City city) {
		repository.delete(city);
	}
	
	@Transactional
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
