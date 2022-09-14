import * as React from "react";
import { CityItem } from "./CityItem";

export class CitiesList extends React.Component {
  cityToCityItem = city => {
    const id = city.id
    const name = city.name
    // const weatherUri = city.links[0].href
   // console.log(id)
    return <CityItem key={id} name={name} selected={this.props.selected} handleChange={this.props.handleChange}/>;
  };

  render() {
    return (
        <div>
        {this.props.cities.map(this.cityToCityItem)}
        </div>
    );
  }
}