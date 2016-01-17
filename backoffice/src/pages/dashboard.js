import React, { PropTypes } from 'react';

import FontIcon from 'material-ui/lib/font-icon';

const DashboardPage = React.createClass({
    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}><FontIcon className="material-icons">dashboard</FontIcon> Tableau de bord</h1>
                <iframe src="https://kibana-necmergitur.herokuapp.com/#/dashboard/dashboard-necmergitur?embed&_a=(filters:!(),panels:!((col:1,id:gravit%C3%A9ParDate,row:1,size_x:6,size_y:3,type:visualization),(col:7,id:occupationParDate,row:1,size_x:6,size_y:3,type:visualization),(col:1,id:gravit%C3%A9ParEtatH-24,row:4,size_x:3,size_y:2,type:visualization),(col:10,id:tensionDansLesDerni%C3%A8resSemaines,row:4,size_x:3,size_y:2,type:visualization),(col:4,id:top5HopitauxNombreDePlacesParService,row:4,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:dashboard-necmergitur)&_g=(refreshInterval:(display:Off,pause:!f,section:0,value:0),time:(from:now-15d,mode:relative,to:now))" height="600" width="100%"></iframe>
            </div>
        )
    }
})

export default DashboardPage
