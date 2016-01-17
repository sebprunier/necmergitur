import React, { PropTypes } from 'react'

import { Link } from 'react-router';

import Paper from 'material-ui/lib/paper';
import Toggle from 'material-ui/lib/toggle';
import Badge from 'material-ui/lib/badge';
import Colors from 'material-ui/lib/styles/colors';

const TensionColors = {
    "Vert": Colors.green500,
    "Jaune": Colors.amber500,
    "Orange": Colors.orange500,
    "Rouge": Colors.red500
}

const HopitauxList = React.createClass({
    renderTension(hopital) {
        if (hopital.active) {
            return (
                <span style={{fontWeight: 'bold', marginLeft: 16, color: TensionColors[hopital.reveil.tension]}}>{`[${hopital.reveil.tension}]`}</span>
            )
        } else {
            return (
                <span style={{marginLeft: 16, color: Colors.grey300}}>{"[N/A]"}</span>
            )
        }
    },

    render () {
        let hopitaux = this.props.hopitaux;
        return (
            <div>
                {hopitaux.map(hopital => { return (
                    <Paper key={hopital.uuid} zDepth={1} style={{padding: 8}}>
                        <div className="grid">
                            <div className="1/4 grid__cell">
                                <p>
                                    {this.renderTension(hopital)}
                                </p>
                            </div>
                            <div className="1/2 grid__cell">
                                <p style={{fontWeight: 'bold'}}>
                                    <Link to={`/hopitaux/${hopital.uuid}`}>
                                        {hopital.name}
                                    </Link>
                                </p>
                            </div>
                            <div className="1/4 grid__cell">
                                <div style={{float: 'right', marginTop: 16, marginRight: 16}}>
                                    <Toggle defaultToggled={hopital.active} />
                                </div>
                            </div>
                        </div>
                    </Paper>
                )})}
            </div>
        )
    }
})

export default HopitauxList
