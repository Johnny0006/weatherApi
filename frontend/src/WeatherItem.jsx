import React from "react";

export class WeatherItem extends React.Component{

    render(){
        const src = "http://openweathermap.org/img/w/" + this.props.icon + ".png";
        return(
            <div>
                <li>{this.props.cityName}</li>
                <ul>
                    <div id="icon"><img id="wicon" src={src}/></div>
                    <li>weather: {this.props.desc}</li>
                    <li>temperature: {this.props.temp}</li>
                </ul>
            </div>
        );
    }

}