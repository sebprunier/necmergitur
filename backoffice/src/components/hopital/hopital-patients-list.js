import React, { PropTypes } from 'react';

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
    "PMA" : <FontIcon className="material-icons">healing</FontIcon>,
    "Transport" : <FontIcon className="material-icons">local_taxi</FontIcon>,
    "Arrivé Hopital": <FontIcon className="material-icons">local_hotel</FontIcon>,
    "Réveil" : <FontIcon className="material-icons">local_hotel</FontIcon>,
    "Urgence" : <FontIcon className="material-icons">local_hotel</FontIcon>,
    "Sorti" : <FontIcon className="material-icons">directions_walk</FontIcon>
}

const ActionLabels = {
    "PMA" : "Valider l'arrivée",
    "Transport" : "Valider l'arrivée",
    "Arrivé Hopital": "Valider la sortie",
    "Réveil" : "Valider la sortie",
    "Urgence" : "Valider la sortie",
    "Sorti" : "Voir l'historique"
}

const PatientsList = React.createClass({

    render () {
        let patients = this.props.patients;
        let patientsEtatsFilters = this.props.patientsEtatsFilters;
        return (
            <div>
                {patients.map(patient => {if (patientsEtatsFilters[patient.etat]) return (
                    <Card key={patient.id} style={CardStyle}>
                        <CardHeader
                            title={`SINUS n°${patient.id}`}
                            subtitle={`[${patient.etat}]`}
                            avatar={Avatars[patient.etat]}
                            showExpandableButton={true} />
                        <CardMedia expandable={true}>
                            <img src={patient.photos[0]}/>
                        </CardMedia>
                        <CardActions>
                            <FlatButton primary={true} label={ActionLabels[patient.etat]} />
                        </CardActions>
                    </Card>
                )})}
            </div>
        )
    }
})

export default PatientsList
