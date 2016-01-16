import React, { PropTypes } from 'react';

import FontIcon from 'material-ui/lib/font-icon';

const UserProfiles = {
    AdminHopital: 'Administrateur Hôpital',
    AdminSamu: 'SAMU - Centre 15'
};

const ComponentStyle = {
    icon: {
        color: 'white',
        marginTop: 8,
        marginRight: 8,
        verticalAlign: 'bottom'
    },
    text: {
        color: 'white',
        fontWeight: 'bold',
        marginRight: 8
    }
}

const CurrentUser = React.createClass({

    getUserProfile() {
        let location = window.location.href;
        if (location.indexOf('hopital') > 0) {
            return UserProfiles.AdminHopital;
        }
        return UserProfiles.AdminSamu;
    },

    render () {
        return (
            <div>
                <FontIcon className="material-icons" style={ComponentStyle.icon}>person_outline</FontIcon>
                <span style={ComponentStyle.text}>{this.getUserProfile()}</span>
            </div>
        )
    }
})

export default CurrentUser
