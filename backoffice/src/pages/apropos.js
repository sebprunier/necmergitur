import React, { PropTypes } from 'react';

import FontIcon from 'material-ui/lib/font-icon';
import Paper from 'material-ui/lib/paper';
import Colors from 'material-ui/lib/styles/colors';

const AProposPage = React.createClass({
    render () {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}><FontIcon className="material-icons">info</FontIcon> A propos</h1>
                <Paper style={{textAlign: 'left', padding: 32, margin: 16, backgroundColor: Colors.grey100}} zDepth={1}>
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
                <Paper style={{textAlign: 'center', padding: 32, margin: 16}} zDepth={1}>
                    <h2>Côté technique</h2>
                        <div>
                            <a href="http://developer.android.com/">
                                <img src="./img/android.png" height="200px" title="Android" />
                            </a>
                            <a href="https://facebook.github.io/react/" style={{margin: 32}}>
                                <img src="./img/react.png" height="200px" title="React" />
                            </a>
                            <a href="https://www.google.com/design/spec/material-design/introduction.html" style={{margin: 32}}>
                                <img src="./img/material-design.png" height="200px" title="Material Design" />
                            </a>
                            <a href="http://www.marklogic.com/" style={{margin: 32}}>
                                <img src="./img/marklogic.png" height="200px" title="MarkLogic" />
                            </a>
                            <a href="https://nodejs.org" style={{margin: 32}}>
                                <img src="./img/nodejs.png" height="200px" title="Node JS" />
                            </a>
                        </div>
                </Paper>
            </div>
        )
    }
})

export default AProposPage
