import * as React from "react";

export class CityItem extends React.Component {

  render(){ 
    const name = this.props.name
    return (
    <div>
      <input type="checkbox" name={name} value={name} checked={this.props.selected.includes(name)} onChange={this.props.handleChange}></input>
      <label for={name}>{name}</label><br></br>
    </div>
  );
  }
}