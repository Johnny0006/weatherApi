import * as React from "react";

export class CityItem extends React.Component {

  render(){ 
    const name = this.props.name
    const id = this.props.id
    return (
    <div>
      <input type="checkbox" name={id} value={name} checked={this.props.selected.includes(id)} onChange={this.props.handleChange}></input>
      <label for={name}>{name}</label><br></br>
    </div>
  );
  }
}