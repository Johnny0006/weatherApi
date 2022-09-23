import * as React from "react";
import { CityPage } from "./CityPage";
import { WeatherPage } from "./WeatherPage";
import { BrowserRouter, Routes, Route } from "react-router-dom";

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
      fetch("http://localhost:8080/api/rest/cities" ,{mode: 'cors'})
        .then(res => res.json())
        .then(json => this.setState({ cities: json }));
        console.log('fetched '+ this.state.cities)
    }

    handleChange(event){
      let isSelected = event.currentTarget.checked;
      let name = Number(event.currentTarget.name)
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
      this.setState({weathers: []})
      try {
         console.log(JSON.stringify(this.state.selected))
         console.log("http://localhost:8080/api/rest/"+this.state.selected)
        const response = await fetch("http://localhost:8080/api/rest/" + this.state.selected, {
          method: 'GET',
          mode: 'cors',
          headers: {
            'Content-Type': 'application/json',
            Accept: 'application/json',
          }
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
            <BrowserRouter>
              <Routes>
                  <Route index element={<CityPage cities={cities} selected={this.state.selected} handleChange={this.handleChange} handleClick = {this.handleClick}/>} />
                  <Route path="weathers" element={<WeatherPage weathers={weathers}/>} />
              </Routes>
            </BrowserRouter>
          </>
        );
      }

}