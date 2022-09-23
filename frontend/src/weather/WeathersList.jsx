import React from "react";
import {WeatherItem} from "./WeatherItem"

export class WeathersList extends React.Component{

    weatherToWeatherItem = weather =>{
        const cityName = weather.city
        const temp = weather.temp
        const desc = weather.weather
        const icon = weather.iconCode
        return <WeatherItem key={cityName} cityName={cityName} temp={temp} desc={desc} icon={icon}/>
    };



    render(){
        return(
            <div>
                <ul>
                {this.props.weathers.map(this.weatherToWeatherItem)}
                </ul>
            </div>
        );
    }

}