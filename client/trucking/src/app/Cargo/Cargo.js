import React from 'react';

let Cargo = function statelessFunctionComponentClass(props) {

    let cargo = props.cargo;

    const cardStyle = {
        width: '340px',
        height: '270px',
        margin: '30px 30px 30px 30px',
        background: '#CCE3DE',
        borderRadius: '5px',
    };

    const cardHeaderStyle = {
        width: '100%',
        height: '100px',
        background: '#A4C3B2',
        borderRadius: '5px 5px 0 0',
    };

    const cardDataSectionStyle = {
        borderBottom: '2px solid #000000',
        marginLeft: '10%',
        paddingBottom: '10px',
        width: '80%'
    };

    const cardDataStyle = {
        padding: '10px 0 0 0',
        fontSize: '20px',
    };

    return (
        <div style={cardStyle}>
            <div style={cardHeaderStyle}>
                <h1 style={{margin: '0 0 0 40px', padding: '10px 0 0 0'}}>{cargo.name}</h1>
                <h2 style={{margin: '10px 0 0 40px'}}> {cargo.source_location} {'-'} {cargo.destination}</h2>
            </div>
            <div style={cardDataSectionStyle}>
                {/*<div style={cardDataStyle}>{'Curent location: '} {cargo.location}</div>*/}
                {/*<div style={cardDataStyle}>{'Carrier: '} {cargo.carrier}</div>*/}
                <div style={cardDataStyle}> {'Transportation cost: '}{cargo.transportation_cost}</div>
            </div>
            <div style={cardDataSectionStyle}>
                <div style={cardDataStyle}>{'Parameters: '} {cargo.width} {'x'} {cargo.height} {'x'} {cargo.length} </div>
                <div style={cardDataStyle}>{'Weight: '} {cargo.weight} </div>
            </div>
        </div>
    );
};

export default Cargo;