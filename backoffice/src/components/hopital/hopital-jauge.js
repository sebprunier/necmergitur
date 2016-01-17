import React, { PropTypes } from 'react'

import Colors from 'material-ui/lib/styles/colors';

const TensionColors = {
    "Vert": Colors.green500,
    "Jaune": Colors.amber500,
    "Orange": Colors.orange500,
    "Rouge": Colors.red500
}

const JaugeStyle = {
    barColors: {
        capacity : Colors.grey400,
        occupied : Colors.grey900,
        coming : Colors.grey600
    },
    barSize: {
        height: 300,
        width: 64
    }
}

const HopitalJauge = React.createClass({
    render () {
        let data = this.props.data;
        let barItemsHeight = {
            occupied: JaugeStyle.barSize.height * data.nombreLitsOccupes / data.nombreLitsDisponibles,
            coming: JaugeStyle.barSize.height * data.nombrePatientsEnRoute / data.nombreLitsDisponibles
        };
        return (
            <div className="grid">
                <div className="1/2 grid__cell">
                    <div style={{color: TensionColors[data.tension], marginTop: 16}}>
                        <div>Tension</div>
                        <div>
                            <span style={{fontSize: '2em'}}>{data.tension}</span>
                        </div>
                    </div>
                    <div style={{color: JaugeStyle.barColors.capacity, marginTop: 32}}>
                        <div>Capacité</div>
                        <div style={{fontSize: '2em'}}>
                            <span>{data.nombreLitsDisponibles}</span>
                        </div>
                    </div>
                    <div style={{color: JaugeStyle.barColors.coming, marginTop: 16}}>
                        <div>Patients en route</div>
                        <div style={{fontSize: '2em'}}>
                            <span>{data.nombrePatientsEnRoute}</span>
                        </div>
                    </div>
                    <div style={{color: JaugeStyle.barColors.occupied, marginTop: 16}}>
                        <div>Lits occupés</div>
                        <div style={{fontSize: '2em'}}>
                            <span>{data.nombreLitsOccupes}</span>
                        </div>
                    </div>
                </div>
                <div className="1/2 grid__cell">
                    <svg height={JaugeStyle.barSize.height} width={JaugeStyle.barSize.width + 30}>
                        <rect
                            width={JaugeStyle.barSize.width}
                            height={JaugeStyle.barSize.height}
                            stroke="none"
                            fill={JaugeStyle.barColors.capacity} />
                        <polygon
                            points={`64,${JaugeStyle.barSize.height - barItemsHeight.occupied} 80,${JaugeStyle.barSize.height - barItemsHeight.occupied + 8} 80,${JaugeStyle.barSize.height - barItemsHeight.occupied - 8}`}
                            stroke={JaugeStyle.barColors.occupied}
                            fill={JaugeStyle.barColors.occupied} />
                        <rect
                            y={JaugeStyle.barSize.height - barItemsHeight.occupied}
                            width={JaugeStyle.barSize.width}
                            height={barItemsHeight.occupied}
                            stroke="none"
                            fill={JaugeStyle.barColors.occupied} />
                        <polygon
                            points={`64,${JaugeStyle.barSize.height - barItemsHeight.occupied - barItemsHeight.coming} 80,${JaugeStyle.barSize.height - barItemsHeight.occupied  - barItemsHeight.coming + 8} 80,${JaugeStyle.barSize.height - barItemsHeight.occupied  - barItemsHeight.coming - 8}`}
                            stroke={JaugeStyle.barColors.coming}
                            fill={JaugeStyle.barColors.coming} />
                        <rect
                            y={JaugeStyle.barSize.height - barItemsHeight.occupied - barItemsHeight.coming}
                            width={JaugeStyle.barSize.width}
                            height={barItemsHeight.coming}
                            stroke="none"
                            fill={JaugeStyle.barColors.coming} />
                    </svg>
                </div>
            </div>
        )
    }
})

export default HopitalJauge
