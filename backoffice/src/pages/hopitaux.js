import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import List from 'material-ui/lib/lists/list';
import ListItem from 'material-ui/lib/lists/list-item';
import Toggle from 'material-ui/lib/toggle';

import { GoogleMapLoader, GoogleMap, Marker } from "react-google-maps";

// TODO récupérer les hôpitaux via un appel REST
const hopitaux = [
    {"name": "Hôpital Adélaïde-Hautval", "location": [49.001485,2.4022900999999592]},
    {"name": "Hôpital Albert-Chenevier", "location": [48.789161,2.458933200000047]},
    {"name": "Hôpital Ambroise-Paré", "location": [48.8490858,2.236287500000003]},
    {"name": "Hôpital Antoine-Béclère", "location": [48.7885168,2.2549008999999387]},
    {"name": "Hôpital Armand-Trousseau", "location": [48.8426852,2.405337599999939]},
    {"name": "Hôpital Avicenne", "location": [48.9151155,2.424411599999985]},
    {"name": "Hôpital Beaujon", "location": [48.9086511,2.307994000000008]},
    {"name": "Hôpital Bicêtre", "location": [48.8096614,2.353025900000034]},
    {"name": "Hôpital Bichat-Claude Bernard", "location": [48.8990543,2.330894199999989]},
    {"name": "Hôpital Bretonneau", "location": [48.89000009999999,2.330546199999958]},
    {"name": "Hôpital Broca", "location": [48.83424369999999,2.3475230999999894]},
    {"name": "Hôpital Charles-Foix", "location": [48.8058373,2.3953947999999627]},
    {"name": "Hôpital Cochin", "location": [48.8373986,2.338868899999966]},
    {"name": "Hôpital Corentin-Celton", "location": [48.8277665,2.2788743999999497]},
    {"name": "Hôpital Emile-Roux", "location": [48.7492527,2.4805863000000272]},
    {"name": "Hôpital Européen Georges-Pompidou", "location": [48.8393498,2.273569500000008]},
    {"name": "Hôpital Fernand-Widal", "location": [48.88129499999999,2.3581001000000015]},
    {"name": "Hôpital Georges-Clemenceau", "location": [48.5234782,2.4327193000000307]},
    {"name": "Hôpital Henri-Mondor", "location": [48.7971279,2.453096500000015]},
    {"name": "Hôpital Hôtel-Dieu", "location": [48.8535815,2.348914700000023]},
    {"name": "Hôpital Jean-Verdier", "location": [48.9173632,2.491495200000031]},
    {"name": "Hôpital Joffre-Dupuytren", "location": [48.67448479999999,2.4128217999999606]},
    {"name": "Hôpital La Collégiale", "location": [48.8386933,2.3523059999999987]},
    {"name": "Hôpital La Roche-Guyon", "location": [49.0801282,1.6258936000000404]},
    {"name": "Hôpital La Rochefoucauld ", "location": [48.8319823,2.3320820999999796]},
    {"name": "Hôpital Lariboisière", "location": [48.8822652,2.35283400000003]},
    {"name": "Hôpital Louis-Mourier", "location": [48.9237119,2.236540200000036]},
    {"name": "Hôpital Marin de Hendaye", "location": [43.37638099999999,-1.7481754000000365]},
    {"name": "Hôpital Maritime de Berck", "location": [50.401538,1.560241000000019]},
    {"name": "Hôpital Paul Doumer", "location": [49.353812,2.495337500000005]},
    {"name": "Hôpital Paul-Brousse", "location": [48.7946141,2.3604315000000042]},
    {"name": "Hôpital Raymond-Poincaré", "location": [48.8385988,2.1706744000000526]},
    {"name": "Hôpital René-Muret", "location": [48.9363646,2.511524799999961]},
    {"name": "Hôpital Rothschild", "location": [48.842771,2.3978577000000314]},
    {"name": "Hôpital Saint-Antoine", "location": [48.8491126,2.3827059999999847]},
    {"name": "Hôpital Saint-Louis", "location": [48.8732305,2.3698087000000214]},
    {"name": "Hôpital Sainte-Périne", "location": [48.84498869999999,2.2666427999999996]},
    {"name": "Hôpital San Salvadour", "location": [43.086171,6.113191000000029]},
    {"name": "Hôpital Tenon", "location": [48.8664424,2.399996299999998]},
    {"name": "Hôpital universitaire Necker-Enfants malades", "location": [48.8463504,2.315448599999968]},
    {"name": "Hôpital Universitaire Pitié-Salpêtrière", "location": [48.83840499999999,2.3611693999999943]},
    {"name": "Hôpital universitaire Robert-Debré", "location": [48.8788894,2.4033964000000196]},
    {"name": "Hôpital Vaugirard", "location": [48.8341994,2.29432010000005]}
]

const HopitauxPage = React.createClass({
    getInitialState() {
        return {
            markers: [{
              position: {
                lat: 25.0112183,
                lng: 121.52067570000001,
              },
              key: "Taiwan",
              defaultAnimation: 2
            }]
        };
    },

    render () {
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
                                defaultZoom={3}
                                defaultCenter={{lat: -25.363882, lng: 131.044922}}>
                                {this.state.markers.map((marker, index) => {
                                  return (
                                    <Marker
                                      {...marker} />
                                  );
                                })}
                              </GoogleMap>
                            }
                          />
                        </section>
                    </div>
                    <div className="1/2 grid__cell">
                        <List>
                            {hopitaux.map(hopital => <ListItem key={hopital.name} primaryText={hopital.name} rightToggle={<Toggle />} />)}
                        </List>
                    </div>
                </div>
            </div>
        )
    }
})

export default HopitauxPage
