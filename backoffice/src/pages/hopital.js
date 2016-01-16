import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import CircularProgress from 'material-ui/lib/circular-progress';
import Checkbox from 'material-ui/lib/checkbox';

import axios from 'axios';

import HopitalJauges from '../components/hopital/hopital-jauges'
import PatientsList from '../components/hopital/hopital-patients-list'

const HopitalPage = React.createClass({

    getInitialState() {
        return {
            loading: true,
            hopital: null,
            patients: [],
            patientsEtatsFilters: {
                "PMA" : true,
                "Transport" : true,
                "Arrivé Hopital": true,
                "Réveil" : true,
                "Urgence" : true,
                "Sorti" : false
            }
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

    onPatientEtatFilterCheck(event, checked) {
        let change = {
            "PMA" : this.state.patientsEtatsFilters["PMA"],
            "Transport" : this.state.patientsEtatsFilters["Transport"],
            "Arrivé Hopital": this.state.patientsEtatsFilters["Arrivé Hopital"],
            "Réveil" : this.state.patientsEtatsFilters["Réveil"],
            "Urgence" : this.state.patientsEtatsFilters["Urgence"],
            "Sorti" : this.state.patientsEtatsFilters["Sorti"]
        }
        change[event.target.name] = checked;
        this.setState({patientsEtatsFilters: change});
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
                            <div className="grid" style={{marginBottom: 8}}>
                                <div className="1/6 grid__cell">
                                    <Checkbox name="PMA" checked={this.state.patientsEtatsFilters["PMA"]} defaultChecked={true} label="PMA" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/6 grid__cell">
                                    <Checkbox name="Transport" checked={this.state.patientsEtatsFilters["Transport"]} defaultChecked={true} label="Transport" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/6 grid__cell">
                                    <Checkbox name="Arrivé Hopital" checked={this.state.patientsEtatsFilters["Arrivé Hopital"]} defaultChecked={true} label="Arrivé" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/6 grid__cell">
                                    <Checkbox name="Réveil" checked={this.state.patientsEtatsFilters["Réveil"]} defaultChecked={true} label="Réveil" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/6 grid__cell">
                                    <Checkbox name="Urgence" checked={this.state.patientsEtatsFilters["Urgence"]} defaultChecked={true} label="Urgence" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                                <div className="1/6 grid__cell">
                                    <Checkbox name="Sorti" checked={this.state.patientsEtatsFilters["Sorti"]} defaultChecked={false} label="Sorti" onCheck={this.onPatientEtatFilterCheck}/>
                                </div>
                            </div>
                            <PatientsList patients={this.state.patients} patientsEtatsFilters={this.state.patientsEtatsFilters} />
                        </div>
                    </div>
                </div>
            )
        }
    }
})

export default HopitalPage
