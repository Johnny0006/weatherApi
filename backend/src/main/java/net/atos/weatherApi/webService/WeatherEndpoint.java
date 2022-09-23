package net.atos.weatherApi.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import lombok.extern.slf4j.Slf4j;
import net.atos.weatherApi.GetWeather;
import net.atos.weatherApi.GetWeatherFault;
import net.atos.weatherApi.GetWeatherFaultMessage;
import net.atos.weatherApi.GetWeatherResponse;
import net.atos.weatherApi.Weather;
import net.atos.weatherApi.restTemplate.WeatherTemplate;
import net.atos.weatherApi.service.CityService;

@Slf4j
@Endpoint
public class WeatherEndpoint implements Weather {
	
	private static final String NAMESPACE_URI = "http://www.example.org/Weather/";
	
	private final CityService cityService;

	@Autowired
	public WeatherEndpoint(CityService cityService) {
		this.cityService=cityService;
	}
	
	@Override
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetWeather")
	@ResponsePayload
	public GetWeatherResponse getWeatherOperation(@RequestPayload GetWeather parameters) throws GetWeatherFaultMessage {
		try {
			net.atos.weatherApi.restTemplate.Weather weather = WeatherTemplate.getWeather(cityService.findByNameAndId(parameters.getCityName(),parameters.getId()).get());
			GetWeatherResponse response = new GetWeatherResponse();
			response.setTemp(weather.getTemp());
			response.setDesc(weather.getWeather());
			log.info(String.valueOf(response.getTemp())+" "+response.getDesc());
			return response;
		} catch (Exception e) {
			GetWeatherFault gFault = new GetWeatherFault();
			gFault.setDesc(e.getMessage());
			throw new GetWeatherFaultMessage("error",gFault);
		}
	}

}



