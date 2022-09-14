import * as React from "react";
import { CitiesList } from "./CitiesList";
import { WeathersList } from "./WeathersList";

export class App extends React.Component {
  

  constructor(props){
    super(props)
    this.state = {
      cities: [],
      weathers: [],
      selected: []
    };
    this.handleChange = this.handleChange.bind(this)
  }
  
    componentDidMount() {
      console.log('I was triggered during componentDidMount')
      fetch("http://localhost:8080/api" ,{mode: 'cors'})
        .then(res => res.json())
        .then(json => this.setState({ cities: json }));
        console.log('fetched '+ this.state.cities)
    }

    handleChange(event){
      let isSelected = event.currentTarget.checked;
      let name = event.currentTarget.name
      console.log(isSelected)
      console.log(name)
      if(isSelected){
        if(this.state.selected.length<5)
        this.setState({selected: [...this.state.selected, name]})
      }else{
        this.setState({selected: this.state.selected.filter(v => v!==name)})
      }
    }

    handleClick = async () => {
      try {
         console.log(JSON.stringify(this.state.selected))

        const response = await fetch("http://localhost:8080/api", {
          method: 'POST',
          mode: 'cors',
          body: JSON.stringify(this.state.selected),
          headers: {
            'Content-Type': 'application/json',
            Accept: 'application/json',
          },
        });
        
      

        if (!response.ok) {
          throw new Error(`Error! status: ${response.status}`);
        }
  
        const result = await response.json();
  
        console.log('result is: ', JSON.stringify(result, null, 4));
  
        this.setState({weathers: result});
      } catch (err) {
        console.log(err)
      }
    }


    render() {
        const cities = this.state.cities;
        const weathers = this.state.weathers;
        console.log('render')
        console.log(this.state.selected)
        return (
          <>
            <div>
              <main className="ui main text container">
                {cities ? <CitiesList cities={cities} selected={this.state.selected} handleChange={this.handleChange}/> : 'Loading…'}
              </main>
            </div>
            <div>
              <button onClick={this.handleClick}>Get weathers</button>
            </div>
            <div>
              <main>
                {weathers ? <WeathersList weathers={weathers}/> : 'Waiting for request'}
              </main>
            </div>
          </>
        );
      }

}