import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';

const SecoursPage = React.createClass({

    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}>
                    <FontIcon className="material-icons">healing</FontIcon> Postes Médicaux Avancés
                </h1>
            </div>
        )
    }
})

export default SecoursPage
