import React from 'react';
import ReactDOM from 'react-dom';

import { Router, Route, IndexRoute } from 'react-router';
import createHistory from 'history/lib/createHashHistory';

import HopitauxPage from './pages/hopitaux';
import HopitalPage from './pages/hopital';
import PostesMedicauxAvances from './pages/postes-medicaux-avances';
import NotFoundPage from './pages/not-found';

import AppBar from 'material-ui/lib/app-bar';
import LeftNav from 'material-ui/lib/left-nav';
import Menu from 'material-ui/lib/menus/menu';
import MenuItem from 'material-ui/lib/menus/menu-item';
import FontIcon from 'material-ui/lib/font-icon';

import ThemeManager from 'material-ui/lib/styles/theme-manager';
import CustomTheme from './theme/theme'

import CurrentUser from './components/security/current-user'

// Needed for onTouchTap
// Can go away when react 1.0 release
// Check this repo:
// https://github.com/zilverline/react-tap-event-plugin
import injectTapEventPlugin from 'react-tap-event-plugin';
injectTapEventPlugin();

require('babel-polyfill');

const App = React.createClass({
    childContextTypes : {
        muiTheme: React.PropTypes.object,
    },

    getChildContext() {
        return {
            muiTheme: ThemeManager.getMuiTheme(CustomTheme),
        };
    },

    getInitialState() {
        return {
            leftNavigationPaneOpened: false
        };
    },
    toggleLeftNavigationPane: function() {
        this.setState({leftNavigationPaneOpened: !this.state.leftNavigationPaneOpened});
    },

    handleLeftNavigationPaneStateChange: function(open) {
        this.setState({leftNavigationPaneOpened: open});
    },

    handleLeftNavigationItemClick: function(event, value) {
        this.props.history.push(value);
        this.setState({leftNavigationPaneOpened: false});
    },

    render () {
        return (
            <div>
                <AppBar
                    title="Nec Mergitur - Défi 672"
                    iconElementRight={<CurrentUser />}
                    onLeftIconButtonTouchTap={this.toggleLeftNavigationPane} />

                <LeftNav
                    open={this.state.leftNavigationPaneOpened}
                    onRequestChange={this.handleLeftNavigationPaneStateChange}
                    docked={false}>
                    <Menu valueLink={{requestChange: this.handleLeftNavigationItemClick}}>
                        <MenuItem
                            value="/"
                            leftIcon={<FontIcon className="material-icons">local_hospital</FontIcon>}>
                            Hôpitaux
                        </MenuItem>
                        <MenuItem
                            value="pma"
                            leftIcon={<FontIcon className="material-icons">healing</FontIcon>}>
                            P.M.A
                        </MenuItem>
                    </Menu>
                </LeftNav>

                <div id="main">
                    {this.props.children}
                </div>
            </div>
        )
    }
})

const router = (
    <Router history={createHistory()}>
        <Route path="/" component={App}>
            <IndexRoute component={HopitauxPage} />
            <Route path="/hopital/:id" component={HopitalPage} />
            <Route path="/pma" component={PostesMedicauxAvances} />
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
