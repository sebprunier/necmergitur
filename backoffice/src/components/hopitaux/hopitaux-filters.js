import React, { PropTypes } from 'react'

import TextField from 'material-ui/lib/text-field';

import RadioButton from 'material-ui/lib/radio-button';
import RadioButtonGroup from 'material-ui/lib/radio-button-group';

const HopitauxFilters = React.createClass({

    handleRadioButtonChange(event, selected) {
        this.props.onActiveFilterChange({
            actives : selected === 'all' || selected === 'actives',
            nonActives : selected === 'all' || selected === 'non-actives'
        });
    },

    handleTextChange(event) {
        let name = event.target.value;
        if (name && name === '') {
            name = null;
        }
        this.props.onNameFilterChange({
            name: name
        });
    },

    render () {
        return (
            <div className="grid">
                <div className="1/2 grid__cell" style={{verticalAlign: 'bottom', marginBottom: 16}}>
                    <RadioButtonGroup name="activeFilter"
                        defaultSelected="actives"
                        style={{display: 'inline-flex', width:'25%'}}
                        onChange={this.handleRadioButtonChange}>
                        <RadioButton
                          value="all"
                          label="Tous les hôpitaux"
                          style={{marginBottom:16}} />
                        <RadioButton
                          value="actives"
                          label="Hôpitaux actifs"
                          style={{marginBottom:16}}/>
                        <RadioButton
                          value="non-actives"
                          label="Hôpitaux inactifs"
                          style={{marginBottom:16}}/>
                    </RadioButtonGroup>
                </div>
                <div className="1/2 grid__cell" style={{marginBottom: 16}}>
                    <TextField
                        hintText="Nom de l'hôpital"
                        floatingLabelText="Rechercher"
                        style={{width: '100%'}}
                        onChange={this.handleTextChange}/>
                </div>
            </div>
        )
    }
})

export default HopitauxFilters
