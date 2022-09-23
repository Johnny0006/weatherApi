import * as React from "react";
import { CitiesList } from "./CitiesList";
import { Link } from "react-router-dom";

export class CityPage extends React.Component{

    render(){
        const cities = this.props.cities
        const selected = this.props.selected
        const handleChange = this.props.handleChange
        const handleClick = this.props.handleClick
        return(
            <>
              <div>
                <main className="ui main text container">
                  {cities ? <CitiesList cities={cities} selected={selected} handleChange={handleChange}/> : 'Loading…'}
                </main>
              </div>
              <div>
                <Link to="/weathers">
                    <button onClick={handleClick}>Get weathers</button>
                </Link>
              </div>
            </>
        )
    }

}