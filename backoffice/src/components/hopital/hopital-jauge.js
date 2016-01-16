import React, { PropTypes } from 'react'

const HopitalJauge = React.createClass({
    render () {
        let data = this.props.data;
        return (
            <ul>
                <li>Nombre de lits disponibles : {data.nombreLitsDisponibles}</li>
                <li>Nombre de patiens en route : {data.nombrePatientsEnRoute}</li>
                <li>Nombre de lits occupés : {data.nombreLitsOccupes}</li>
                <li>Tension : {data.tension}</li>
            </ul>
        )
    }
})

export default HopitalJauge
