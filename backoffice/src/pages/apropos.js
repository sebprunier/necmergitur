import React, { PropTypes } from 'react';

import FontIcon from 'material-ui/lib/font-icon';
import Paper from 'material-ui/lib/paper';

const AProposPage = React.createClass({
    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}><FontIcon className="material-icons">info</FontIcon> A propos</h1>
                <Paper style={{textAlign: 'center', padding: 32, margin: 16}} zDepth={1}>
                    <h2>Défi 672 : Comment aider à la décision, temps réel pour l’orientation des victimes, vers les structures hospitalières ?</h2>
                    <p>
                        <i>Porteur : Dr Eric Lecarpentier, APHP</i>
                    </p>
                    <p>
                        Lors d’évènements majeurs les victimes sont évacuées rapidement vers les établissements hospitaliers. <br />
                        Les évacuations sont décidées par les médecins régulateurs et évacuateurs du SAMU situés au plus près des victimes.<br />
                        Plusieurs médecins, répartis sur le terrain, peuvent décider des orientations.<br />
                        L’objectif du défi est d’apporter aux décideurs, en temps réel, une synthèse de l’ensemble des décisions prises sur le terrain, consolidée des informations collectées à l’arrivée des établissements hospitaliers.
                    </p>
                </Paper>
            </div>
        )
    }
})

export default AProposPage
