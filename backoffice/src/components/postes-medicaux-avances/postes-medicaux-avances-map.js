import React, { PropTypes } from 'react'

import { GoogleMapLoader, GoogleMap, Marker } from "react-google-maps";

const PostesMedicauxAvancesMap = React.createClass({
    render () {
        let pmas = this.props.pmas;
        return (
            <section style={{height: "700px"}}>
              <GoogleMapLoader
                containerElement={
                  <div
                    {...this.props}
                    style={{
                      height: "100%"
                    }}
                  />
                }
                googleMapElement={
                  <GoogleMap
                    defaultZoom={12}
                    defaultCenter={{lat: 48.856638, lng: 2.352241}}>
                    {pmas.map((pma, index) => {
                        let coords = pma.location.split(',');
                        let marker = {
                          position: {
                            lat: parseFloat(coords[0]),
                            lng: parseFloat(coords[1]),
                          },
                          key: pma.uuid,
                          title: pma.name
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

export default PostesMedicauxAvancesMap
