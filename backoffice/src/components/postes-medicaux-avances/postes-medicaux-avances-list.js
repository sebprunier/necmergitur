import React, { PropTypes } from 'react'

import { Link } from 'react-router';

import Paper from 'material-ui/lib/paper';

const PostesMedicauxAvancesList = React.createClass({
    render() {
        let pmas = this.props.pmas;
        return (
            <div>
                {pmas.map(pma => { return (
                    <Paper key={pma.uuid} zDepth={1} style={{padding: 8}}>
                        <p style={{fontWeight: 'bold'}}>
                            <Link to={`/pmas/${pma.uuid}`}>
                                {pma.name}
                            </Link>
                        </p>
                    </Paper>
                )})}
            </div>
        )
    }
})

export default PostesMedicauxAvancesList
