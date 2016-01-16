import React, { PropTypes } from 'react'

import { GoogleMapLoader, GoogleMap, Marker } from "react-google-maps";

const HopitauxMap = React.createClass({
    render () {
        let hopitaux = this.props.hopitaux;
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
                    defaultZoom={12}
                    defaultCenter={{lat: 48.856638, lng: 2.352241}}>
                    {hopitaux.map((hopital, index) => {
                        let coords = hopital.location.split(',');
                        let marker = {
                          position: {
                            lat: parseFloat(coords[0]),
                            lng: parseFloat(coords[1]),
                          },
                          key: hopital.uuid,
                          title: hopital.name
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
    }
})

export default HopitauxMap
