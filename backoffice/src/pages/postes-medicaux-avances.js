import React, { PropTypes } from 'react';

import FontIcon from 'material-ui/lib/font-icon';

import PostesMedicauxAvancesMap from '../components/postes-medicaux-avances/postes-medicaux-avances-map';
import PostesMedicauxAvancesList from '../components/postes-medicaux-avances/postes-medicaux-avances-list';

const pmas = [
    {
        uuid: "1",
        name: "Stade de France",
        location: "48.9244627,2.3579705"
    },
    {
        uuid: "2",
        name: "Bataclan",
        location: "48.8630134,2.3684218"
    },
    {
        uuid: "3",
        name: "La Belle Equipe",
        location: "48.8538463,2.3798772"
    }
];

const PostesMedicauxAvancesPage = React.createClass({

    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}>
                    <FontIcon className="material-icons">healing</FontIcon> Postes Médicaux Avancés
                </h1>
                <div className="grid">
                    <div className="1/2 grid__cell">
                        <PostesMedicauxAvancesMap pmas={pmas} />
                    </div>
                    <div className="1/2 grid__cell">
                        <PostesMedicauxAvancesList pmas={pmas} />
                    </div>
                </div>
            </div>
        )
    }
})

export default PostesMedicauxAvancesPage
