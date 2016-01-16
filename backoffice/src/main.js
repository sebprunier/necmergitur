import React from 'react';
import ReactDOM from 'react-dom';

import { Router, Route, IndexRoute } from 'react-router';
import createHistory from 'history/lib/createHashHistory';

import HopitauxPage from './pages/hopitaux';
import HopitalPage from './pages/hopital';
import NotFoundPage from './pages/not-found';

import AppBar from 'material-ui/lib/app-bar';
import injectTapEventPlugin from 'react-tap-event-plugin';

// Needed for onTouchTap
// Can go away when react 1.0 release
// Check this repo:
// https://github.com/zilverline/react-tap-event-plugin
injectTapEventPlugin();

require('babel-polyfill');

const App = React.createClass({
    render () {
        return (
            <div>
                <AppBar
                    title="Nec Mergitur - Défi 671" />
                {this.props.children}
            </div>
        )
    }
})

const router = (
    <Router history={createHistory()}>
        <Route path="/" component={App}>
            <IndexRoute component={HopitauxPage} />
            <Route path="/hopital/:id" component={HopitalPage} />
            <Route path="*" component={NotFoundPage} />
        </Route>
    </Router>
)

export function init() {
    ReactDOM.render(
        router,
        document.getElementById('app')
    )
}
