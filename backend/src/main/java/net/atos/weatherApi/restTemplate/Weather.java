package net.atos.weatherApi.restTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Column;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice.This;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	private String city;
	private double lat;
	private double lon;
	private String weather;
	private double temp;
	private String iconCode;
	
	@JsonProperty("coord")
	private void unpackCoord(Map<String, Double> coord) {
		lat=coord.get("lat");
		lon=coord.get("lon");
	}
	
	@JsonProperty("main")
	private void unpackMain(Map<String, Double> main) {
		temp=BigDecimal.valueOf(kelvinToCelcius(main.get("temp"))).setScale(2,RoundingMode.HALF_UP).doubleValue();
	
	}
	
	@JsonProperty("weather")
	private void unpackWeather(List<Map<String, String>> weather) {
		this.weather=weather.get(0).get("main");
		iconCode=weather.get(0).get("icon");
	}
	
	public static double kelvinToCelcius(double K){
	    double C = K - 273.15;
	    return C;
	  }
}
