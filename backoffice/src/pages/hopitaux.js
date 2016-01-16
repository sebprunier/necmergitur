import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import List from 'material-ui/lib/lists/list';
import ListItem from 'material-ui/lib/lists/list-item';
import Toggle from 'material-ui/lib/toggle';
import CircularProgress from 'material-ui/lib/circular-progress';

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
              console.log(response);
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

    render () {
        if (this.state.loading) {
            return (
                <div style={{textAlign: 'center'}}>
                    <h1>
                        <FontIcon className="material-icons">local_hospital</FontIcon> Hôpitaux de Paris
                    </h1>
                    <CircularProgress mode="indeterminate" />
                </div>
            )
        } else {
            var hopitaux = this.state.hopitaux;
            return (
                <div>
                    <h1 style={{textAlign: 'center'}}>
                        <FontIcon className="material-icons">local_hospital</FontIcon> Hôpitaux de Paris
                    </h1>
                    <div className="grid">
                        <div className="1/2 grid__cell">
                            <section style={{height: "800px"}}>
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
                        </div>
                        <div className="1/2 grid__cell">
                            <List>
                                {hopitaux.map(hopital => <ListItem key={hopital.uuid} primaryText={hopital.name} rightToggle={<Toggle defaultToggled={hopital.active} />} />)}
                            </List>
                        </div>
                    </div>
                </div>
            )
        }
    }
})

export default HopitauxPage
