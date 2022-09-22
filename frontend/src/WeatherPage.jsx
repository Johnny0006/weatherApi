import * as React from "react";
import { WeathersList } from "./WeathersList";
import { Link } from "react-router-dom";

export class WeatherPage extends React.Component{

    render(){
        const weathers = this.props.weathers
        return(
            <>
                <div>
                    <main>
                        {weathers.length>0 ? <WeathersList weathers={weathers}/> : 'Waiting for request'}
                    </main>
                </div>
                <div>
                    <Link to="/">
                        <button>back</button>
                    </Link>
                </div>
            </>
        )
    }

}