import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import CircularProgress from 'material-ui/lib/circular-progress';

import axios from 'axios';

import HopitalJauges from '../components/hopital/hopital-jauges'
import PatientsList from '../components/hopital/hopital-patients-list'

const HopitalPage = React.createClass({

    getInitialState() {
        return {
            loading: true,
            hopital: null,
            patients: []
        };
    },

    componentDidMount() {
        // Chargement des données de l'hopital
        axios.get('https://stub-backend-672.herokuapp.com/api/hopitaux/' + this.props.params.id)
          .then(response => {
              this.setState({
                  loading: false,
                  hopital: response.data
              });
          })
          .catch(response => {
              // TODO gérer une erreur dans le state
              console.log(response);
          });

          // Chargement des prises en charge
          axios.get('https://stub-backend-672.herokuapp.com/api/prises-en-charge/hopital/' + this.props.params.id)
            .then(response => {
                this.setState({
                    patients: response.data
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
                    <CircularProgress mode="indeterminate" />
                </div>
            )
        } else {
            let hopital = this.state.hopital;
            return (
                <div>
                    <h1 style={{textAlign: 'center'}}>
                        <FontIcon className="material-icons">local_hospital</FontIcon> {hopital.name}
                    </h1>
                    <div className="grid">
                        <div className="1/2 grid__cell">
                            <HopitalJauges hopital={hopital} />
                        </div>
                        <div className="1/2 grid__cell">
                            <PatientsList patients={this.state.patients} />
                        </div>
                    </div>
                </div>
            )
        }
    }
})

export default HopitalPage
