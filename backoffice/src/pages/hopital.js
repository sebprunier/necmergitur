import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import CircularProgress from 'material-ui/lib/circular-progress';
import Checkbox from 'material-ui/lib/checkbox';
import Snackbar from 'material-ui/lib/snackbar';

import axios from 'axios';

import HopitalJauges from '../components/hopital/hopital-jauges'
import PatientsList from '../components/hopital/hopital-patients-list'

var socket = require('socket.io-client')('http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:5000');

const HopitalPage = React.createClass({

    getInitialState() {
        return {
            loading: true,
            hopital: null,
            patients: [],
            patientsEtatsFilters: {
                "Transport" : true,
                "Hopital" : true,
                "Sorti" : false
            },
            patientsUrgenceFilter: {
                "UA": true,
                "UR": true
            },
            showSnackbar: false
        };
    },

    loadHopitalInfo() {
        // let url = 'https://stub-backend-672.herokuapp.com/api/hopitaux/' + this.props.params.id;
        let url = 'http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:8080/LATEST/resources/hopitaux?rs:hopitaUUID=' + this.props.params.id;
        axios.get(url)
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

    loadPriseEnChargeHopital() {
        // let url = 'https://stub-backend-672.herokuapp.com/api/prises-en-charge/hopital/' + this.props.params.id;
        let url = 'http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:8080/LATEST/resources/prises-en-charge?rs:hopitalId=' + this.props.params.id;
        axios.get(url)
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

    componentDidMount() {
        // chargement des données de l'hopital
        this.loadHopitalInfo();
        this.loadPriseEnChargeHopital();

        // socketio
        socket.on('connect', () => console.log('SOCKET CONNECTED'));
        socket.on('refresh', this.refresh);
    },

    refresh() {
        console.log('REFRESH');
        // refresh des données de l'hopital
        this.loadHopitalInfo();
        this.loadPriseEnChargeHopital();
        // show snackbar
        this.setState({
            showSnackbar: true
        })
    },

    onPatientEtatFilterCheck(event, checked) {
        let change = {
            "Transport" : this.state.patientsEtatsFilters["Transport"],
            "Hopital" : this.state.patientsEtatsFilters["Hopital"],
            "Sorti" : this.state.patientsEtatsFilters["Sorti"]
        }
        change[event.target.name] = checked;
        this.setState({patientsEtatsFilters: change});
    },

    onPatientUrgenceFilterCheck(event, checked) {
        let change = {
            "UA" : this.state.patientsUrgenceFilter["UA"],
            "UR" : this.state.patientsUrgenceFilter["UR"]
        }
        change[event.target.name] = checked;
        this.setState({patientsUrgenceFilter: change});
    },

    hideSnackbar() {
        this.setState({
            showSnackbar: false
        })
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
                        <div className="1/2 grid__cell" style={{paddingLeft: 64}}>
                            <div className="grid" style={{marginBottom: 16}}>
                                <div className="1/5 grid__cell">
                                    <b>Filtrer par état</b> :
                                </div>
                                <div className="1/5 grid__cell">
                                    <Checkbox name="Transport" checked={this.state.patientsEtatsFilters["Transport"]} defaultChecked={true} label="Transport" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/5 grid__cell">
                                    <Checkbox name="Hopital" checked={this.state.patientsEtatsFilters["Hopital"]} defaultChecked={true} label="Hôpital  " onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/5 grid__cell">
                                    <Checkbox name="Sorti" checked={this.state.patientsEtatsFilters["Sorti"]} defaultChecked={false} label="Sorti" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                            </div>
                            <div className="grid" style={{marginBottom: 16}}>
                                <div className="1/5 grid__cell">
                                    <b>Filtrer par urgence</b> :
                                </div>
                                <div className="1/5 grid__cell">
                                    <Checkbox name="UA" checked={this.state.patientsUrgenceFilter["UA"]} defaultChecked={true} label="UA" onCheck={this.onPatientUrgenceFilterCheck}/>
                                </div>
                                <div className="1/5 grid__cell">
                                    <Checkbox name="UR" checked={this.state.patientsUrgenceFilter["UR"]} defaultChecked={false} label="UR" onCheck={this.onPatientUrgenceFilterCheck}/>
                                </div>
                            </div>
                            <PatientsList patients={this.state.patients} patientsEtatsFilters={this.state.patientsEtatsFilters} patientsUrgenceFilter={this.state.patientsUrgenceFilter} />
                        </div>
                    </div>
                    <Snackbar
                      open={this.state.showSnackbar}
                      message="Les données ont été mises à jour en temps réel !"
                      onRequestClose={this.hideSnackbar}
                      autoHideDuration={3000} />
                </div>
            )
        }
    }
})

export default HopitalPage
