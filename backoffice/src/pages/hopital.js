import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import CircularProgress from 'material-ui/lib/circular-progress';

import axios from 'axios';

const HopitalPage = React.createClass({

    getInitialState() {
        return {
            loading: true,
            hopital: null
        };
    },

    componentDidMount() {
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
    },

    render () {
        if (this.state.loading) {
            return (
                <div style={{textAlign: 'center'}}>
                    <CircularProgress mode="indeterminate" />
                </div>
            )
        } else {
            var hopital = this.state.hopital;
            return (
                <div>
                    <h1 style={{textAlign: 'center'}}>
                        <FontIcon className="material-icons">local_hospital</FontIcon> {hopital.name}
                    </h1>
                    <div className="grid">
                        <div className="1/2 grid__cell">
                            <b>Urgences Absolues</b>
                            <ul>
                                <li>Nombre de lits disponibles : {hopital.reveil.nombreLitsDisponibles}</li>
                                <li>Nombre de patiens en route : {hopital.reveil.nombrePatientsEnRoute}</li>
                                <li>Nombre de lits occupés : {hopital.reveil.nombreLitsOccupes}</li>
                                <li>Tension : {hopital.reveil.tension}</li>
                            </ul>
                            <b>Urgences Relatives</b>
                            <ul>
                                <li>Nombre de lits disponibles : {hopital.urgence.nombreLitsDisponibles}</li>
                                <li>Nombre de patiens en route : {hopital.urgence.nombrePatientsEnRoute}</li>
                                <li>Nombre de lits occupés : {hopital.urgence.nombreLitsOccupes}</li>
                                <li>Tension : {hopital.urgence.tension}</li>
                            </ul>
                        </div>
                        <div className="1/2 grid__cell">
                            PATIENTS
                        </div>
                    </div>
                </div>
            )
        }
    }
})

export default HopitalPage
