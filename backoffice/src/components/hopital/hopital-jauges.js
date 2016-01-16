import React, { PropTypes } from 'react';

import Colors from 'material-ui/lib/styles/colors';
import Paper from 'material-ui/lib/paper';

import HopitalJauge from './hopital-jauge';

const TensionBackgroundColors = {
    "Vert": Colors.green500,
    "Jaune": Colors.amber500,
    "Rouge": Colors.red500
}

const HopitalJauges = React.createClass({
    render () {
        let hopital = this.props.hopital;
        return (
            <div className="grid" style={{textAlign: 'center'}}>
                <div className="1/2 grid__cell">
                    <Paper zDepth={1} style={{padding: 8, border: `5px solid ${TensionBackgroundColors[hopital.reveil.tension]}`}}>
                        <h2>Urgences Absolues (UA)</h2>
                        <HopitalJauge key="UA" data={hopital.reveil} />
                    </Paper>
                </div>
                <div className="1/2 grid__cell">
                    <Paper zDepth={1} style={{padding: 8, border: `5px solid ${TensionBackgroundColors[hopital.urgence.tension]}`}}>
                        <h2>Urgences Relatives (UR)</h2>
                        <HopitalJauge key="UR" data={hopital.urgence} />
                    </Paper>
                </div>
            </div>
        )
    }
})

export default HopitalJauges
