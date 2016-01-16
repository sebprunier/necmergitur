import React, { PropTypes } from 'react'

import FontIcon from 'material-ui/lib/font-icon';
import CircularProgress from 'material-ui/lib/circular-progress';

import HopitauxMap from '../components/hopitaux/hopitaux-map';
import HopitauxList from '../components/hopitaux/hopitaux-list';
import HopitauxFilters from '../components/hopitaux/hopitaux-filters';

import axios from 'axios';

const HopitauxPage = React.createClass({

    getInitialState() {
        return {
            loading: true,
            hopitaux: [],
            filteredHopitaux: [],
            filters : {
                actives : true,
                nonActives : false,
                name: null
            }
        };
    },

    filterHopitaux(hopitaux, filters) {
        let filteredHopitaux = hopitaux.filter(hopital => (hopital.active && filters.actives) || (!hopital.active && filters.nonActives));
        if (filters.name === null) {
            return filteredHopitaux;
        } else {
            return filteredHopitaux.filter(hopital => new RegExp(filters.name, 'i').exec(hopital.name));
        }
    },

    onActiveFilterChange(activeFilters) {
        let filters = {
            actives : activeFilters.actives,
            nonActives : activeFilters.nonActives,
            name: this.state.filters.name
        }
        this.setState({
            filters: filters,
            filteredHopitaux: this.filterHopitaux(this.state.hopitaux, filters)
        })
    },

    onNameFilterChange(nameFilter) {
        let filters = {
            actives : this.state.filters.actives,
            nonActives : this.state.filters.nonActives,
            name: nameFilter.name
        }
        this.setState({
            filters: filters,
            filteredHopitaux: this.filterHopitaux(this.state.hopitaux, filters)
        })
    },

    componentDidMount() {
        let url = 'http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:8080/LATEST/resources/hopitaux'
        // let url = 'https://stub-backend-672.herokuapp.com/api/hopitaux'
        axios.get(url)
          .then(response => {
              this.setState({
                  loading: false,
                  hopitaux: response.data,
                  filteredHopitaux: this.filterHopitaux(response.data, this.state.filters)
              });
          })
          .catch(response => {
              // TODO gérer une erreur dans le state
              console.log(response);
          });
    },

    renderTitle() {
        return (
            <h1 style={{textAlign: 'center', marginBottom: 0}}>
                <FontIcon className="material-icons">local_hospital</FontIcon> Hôpitaux de Paris
            </h1>
        )
    },

    render () {
        if (this.state.loading) {
            return (
                <div style={{textAlign: 'center'}}>
                    {this.renderTitle()}
                    <CircularProgress mode="indeterminate" />
                </div>
            )
        } else {
            return (
                <div>
                    {this.renderTitle()}
                    <HopitauxFilters
                        onActiveFilterChange={this.onActiveFilterChange}
                        onNameFilterChange={this.onNameFilterChange} />
                    <div className="grid">
                        <div className="1/2 grid__cell">
                            <HopitauxMap hopitaux={this.state.filteredHopitaux} />
                        </div>
                        <div className="1/2 grid__cell">
                            <HopitauxList hopitaux={this.state.filteredHopitaux} />
                        </div>
                    </div>
                </div>
            )
        }
    }
})

export default HopitauxPage
