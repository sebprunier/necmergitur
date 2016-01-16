import React, { PropTypes } from 'react'

import { Link } from 'react-router';

import FontIcon from 'material-ui/lib/font-icon';
import List from 'material-ui/lib/lists/list';
import ListItem from 'material-ui/lib/lists/list-item';
import Toggle from 'material-ui/lib/toggle';
import CircularProgress from 'material-ui/lib/circular-progress';
import Paper from 'material-ui/lib/paper';

import { GoogleMapLoader, GoogleMap, Marker } from "react-google-maps";

import axios from 'axios';

const HopitauxPage = React.createClass({

    getInitialState() {
        return {
            loading: true,
            hopitaux: []
        };
    },

    componentDidMount() {
        axios.get('https://stub-backend-672.herokuapp.com/api/hopitaux')
          .then(response => {
              this.setState({
                  loading: false,
                  hopitaux: response.data
              });
          })
          .catch(response => {
              // TODO gérer une erreur dans le state
              console.log(response);
          });
    },

    renderTitle() {
        return (
            <h1 style={{textAlign: 'center'}}>
                <FontIcon className="material-icons">local_hospital</FontIcon> Hôpitaux de Paris
            </h1>
        )
    },

    renderTable() {
        var hopitaux = this.state.hopitaux;
        return (
            <div>
                {hopitaux.map(hopital => { return (
                    <Paper key={hopital.uuid} zDepth={1} style={{padding: 8}}>
                        <div className="grid">
                            <div className="1/2 grid__cell">
                                <p style={{fontWeight: 'bold'}}>
                                    <Link to={`/hopital/${hopital.uuid}`}>
                                        {hopital.name}
                                    </Link>
                                </p>
                            </div>
                            <div className="1/2 grid__cell">
                                <div style={{float: 'right', marginTop: 16, marginRight: 16}}>
                                    <Toggle defaultToggled={hopital.active} />
                                </div>
                            </div>
                        </div>
                    </Paper>
                )})}
            </div>
        )
    },

    renderMap() {
        var hopitaux = this.state.hopitaux;
        return (
            <section style={{height: "700px"}}>
              <GoogleMapLoader
                containerElement={
                  <div
                    {...this.props}
                    style={{
                      height: "100%",
                    }}
                  />
                }
                googleMapElement={
                  <GoogleMap
                    ref={(map) => console.log(map)}
                    defaultZoom={12}
                    defaultCenter={{lat: 48.856638, lng: 2.352241}}>
                    {hopitaux.map((hopital, index) => {
                        let marker = {
                          position: {
                            lat: hopital.location[0],
                            lng: hopital.location[1],
                          },
                          key: hopital.uuid,
                          title: hopital.name,
                          defaultAnimation: 2
                        }
                      return (
                        <Marker {...marker} />
                      );
                    })}
                  </GoogleMap>
                }
              />
            </section>
        )
    },

    render () {
        if (this.state.loading) {
            return (
                <div style={{textAlign: 'center'}}>
                    {this.renderTitle()}
                    <CircularProgress mode="indeterminate" />
                </div>
            )
        } else {

            return (
                <div>
                    {this.renderTitle()}
                    <div className="grid">
                        <div className="1/2 grid__cell">
                            {this.renderMap()}
                        </div>
                        <div className="1/2 grid__cell">
                            {this.renderTable()}
                        </div>
                    </div>
                </div>
            )
        }
    }
})

export default HopitauxPage
