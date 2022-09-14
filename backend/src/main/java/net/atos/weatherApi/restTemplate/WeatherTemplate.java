package net.atos.weatherApi.restTemplate;

import java.net.URL;
import java.util.concurrent.TimeoutException;

import org.aspectj.weaver.patterns.PerThisOrTargetPointcutVisitor;
import org.hibernate.sql.Template;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.atos.weatherApi.entity.City;
import net.bytebuddy.asm.Advice.This;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Slf4j
@Component
public class WeatherTemplate {

	private static String KEY;
	private static String URL; 
	
	public static Weather getWeather(City city) throws TimeoutException {
		
		RestTemplate template = new RestTemplate();
		
		String url = URL.replaceFirst("\\{lat\\}", String.valueOf(city.getLat()))
				.replaceFirst("\\{lon\\}", String.valueOf(city.getLon()))
				.replaceFirst("\\{API key\\}", KEY);
		
		
		
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		log.info(response.getBody());
		
		
		Weather weather;
		try {
			weather = mapper.readValue(response.getBody(), Weather.class);
			weather.setCity(city.getName());
			return weather;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
		throw new TimeoutException(URL.substring(0,URL.indexOf(".org")+4) + " is not available.");
	}
	
	@Value("${api.openweathermap.key}")
	private void setKey(String key) {
		WeatherTemplate.KEY=key;
	}
	
	@Value("${api.openweathermap.url}")
	private void setUrl(String url) {
		WeatherTemplate.URL=url;
	}
}
