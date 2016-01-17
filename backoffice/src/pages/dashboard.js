import React, { PropTypes } from 'react';

import FontIcon from 'material-ui/lib/font-icon';

const DashboardPage = React.createClass({
    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}><FontIcon className="material-icons">dashboard</FontIcon> Dashboard</h1>
            </div>
        )
    }
})

export default DashboardPage
