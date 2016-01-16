import React, { PropTypes } from 'react'

import { Link } from 'react-router';

import Paper from 'material-ui/lib/paper';
import Toggle from 'material-ui/lib/toggle';

const HopitauxList = React.createClass({
    render () {
        let hopitaux = this.props.hopitaux;
        return (
            <div>
                {hopitaux.map(hopital => { return (
                    <Paper key={hopital.uuid} zDepth={1} style={{padding: 8}}>
                        <div className="grid">
                            <div className="1/2 grid__cell">
                                <p style={{fontWeight: 'bold'}}>
                                    <Link to={`/hopitaux/${hopital.uuid}`}>
                                        {hopital.name}
                                    </Link>
                                </p>
                            </div>
                            <div className="1/2 grid__cell">
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
