import * as React from "react";
import { CityItem } from "./CityItem";

export class CitiesList extends React.Component {
  cityToCityItem = city => {
    const id = city.id
    const name = city.name
    return <CityItem key={id} id={id} name={name} selected={this.props.selected} handleChange={this.props.handleChange}/>;
  };

  render() {
    return (
        <div>
        {this.props.cities.map(this.cityToCityItem)}
        </div>
    );
  }
}