import React, { PropTypes } from 'react';

import HopitalJauge from './hopital-jauge';

const HopitalJauges = React.createClass({
    render () {
        let hopital = this.props.hopital;
        return (
            <div>
                <b>Urgences Absolues (UA)</b>
                <HopitalJauge key="UA" data={hopital.reveil} />

                <b>Urgences Relatives (UR)</b>
                <HopitalJauge key="UR" data={hopital.urgence} />
            </div>
        )
    }
})

export default HopitalJauges
