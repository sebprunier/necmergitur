import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';

const PosteMedicalAvancePage = React.createClass({

    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}>
                    <FontIcon className="material-icons">healing</FontIcon> Poste Médical Avancé : {this.props.params.id}
                </h1>
            </div>
        )
    }
})

export default PosteMedicalAvancePage
