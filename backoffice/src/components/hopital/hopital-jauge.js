import React, { PropTypes } from 'react'

import Colors from 'material-ui/lib/styles/colors';

const TensionColors = {
    "Vert": Colors.green500,
    "Jaune": Colors.amber500,
    "Rouge": Colors.red500
}

const JaugeStyle = {
    itemTitle: {
        color: Colors.grey700,
        marginTop: 16
    },
    itemValue: {
        color: Colors.grey700,
        fontSize: '2em'
    }
}

const HopitalJauge = React.createClass({
    render () {
        let data = this.props.data;
        return (
            <div className="grid">
                <div className="1/2 grid__cell">
                    <div>
                        <div style={JaugeStyle.itemTitle}>Tension</div>
                        <div style={JaugeStyle.itemValue}>
                            <span style={{color: TensionColors[data.tension]}}>{data.tension}</span>
                        </div>
                    </div>
                    <div>
                        <div style={JaugeStyle.itemTitle}>Capacité</div>
                        <div style={JaugeStyle.itemValue}>{data.nombreLitsDisponibles}</div>
                    </div>
                    <div>
                        <div style={JaugeStyle.itemTitle}>Lits occupés</div>
                        <div style={JaugeStyle.itemValue}>{data.nombreLitsOccupes}</div>
                    </div>
                    <div>
                        <div style={JaugeStyle.itemTitle}>Patients en route</div>
                        <div style={JaugeStyle.itemValue}>{data.nombrePatientsEnRoute}</div>
                    </div>
                </div>
                <div className="1/2 grid__cell">
                    <svg height="300" width="100" >
                        <rect width="50" height="300" stroke="black" fill="none" />
                    </svg>
                </div>
            </div>
        )
    }
})

export default HopitalJauge
