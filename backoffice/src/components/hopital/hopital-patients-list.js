import React, { PropTypes } from 'react';

import ReactCSSTransitionGroup from 'react-addons-css-transition-group';

import Card from 'material-ui/lib/card/card';
import CardActions from 'material-ui/lib/card/card-actions';
import CardHeader from 'material-ui/lib/card/card-header';
import CardMedia from 'material-ui/lib/card/card-media';
import CardTitle from 'material-ui/lib/card/card-title';
import FlatButton from 'material-ui/lib/flat-button';
import CardText from 'material-ui/lib/card/card-text';
import FontIcon from 'material-ui/lib/font-icon';

const CardStyle = {
    marginBottom: 5
}

const Avatars = {
    "Transport" : <FontIcon className="material-icons">local_taxi</FontIcon>,
    "Hopital" : <FontIcon className="material-icons">local_hotel</FontIcon>,
    "Sorti" : <FontIcon className="material-icons">directions_walk</FontIcon>
}

const ActionLabels = {
    "Transport" : "Valider l'arrivée",
    "Hopital" : "Valider la sortie",
    "Sorti" : "Voir l'historique"
}

const PatientsList = React.createClass({

    render () {
        let patients = this.props.patients;
        let patientsEtatsFilters = this.props.patientsEtatsFilters;
        let patientsUrgenceFilter = this.props.patientsUrgenceFilter;

        let filteredPatients = patients.filter(patient => patientsEtatsFilters[patient.etat] && patientsUrgenceFilter[patient.gravite]);

        if (filteredPatients.length <= 0) {
            return <div style={{textAlign: 'center', marginTop: 32}}>Aucune donnée correspondant aux filtres ...</div>
        } else {
            return (
                <div>
                    <ReactCSSTransitionGroup transitionName="patient"
                        transitionEnterTimeout={500}
                        transitionLeaveTimeout={300}
                        transitionAppear={true}
                        transitionAppearTimeout={500}>
                        {filteredPatients.map(patient => {return (
                            <Card key={patient.id} style={CardStyle}>
                                <CardHeader
                                    title={`SINUS n°${patient.id}`}
                                    subtitle={`[ ${patient.gravite} - ${patient.etat} ]`}
                                    avatar={Avatars[patient.etat]}
                                    showExpandableButton={true} />
                                <CardText expandable={true}>
                                    <p>
                                        {patient.description}
                                    </p>
                                </CardText>
                                <CardMedia expandable={true}>
                                    <img src={patient.photos[0]}/>
                                </CardMedia>
                                <CardActions>
                                    <FlatButton primary={true} label={ActionLabels[patient.etat]} />
                                </CardActions>
                            </Card>
                        )})}
                    </ReactCSSTransitionGroup>
                </div>
            )
        }
    }
})

export default PatientsList
