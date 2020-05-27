import React from 'react';
import {cargoesService} from "./cargoes.service";
import Button from "@material-ui/core/Button";

let Cargo = function statelessFunctionComponentClass(props) {

    let cargo = props.cargo;
    let editMode = false;

    const cardStyle = {
        width: '440px',
        height: '300px',
        margin: '30px 30px 30px 30px',
        background: '#CCE3DE',
        borderRadius: '5px',
    };

    const cardHeaderStyle = {
        width: '100%',
        height: '130px',
        background: '#A4C3B2',
        borderRadius: '5px 5px 0 0',
    };

    const cardDataSectionStyle = {
        borderBottom: '2px solid #000000',
        marginLeft: '10%',
        paddingBottom: '10px',
        width: '80%',
        flex: 1,
        display: 'flex',
        flexDirection: 'row'
    };

    const cardDataStyle = {
        padding: '10px 10px  0 0',
        fontSize: '20px',
        display: 'flex',

    };

    function deleteCurrentCargo(e) {
        cargoesService.deleteCargo(cargo, cargo.ownerId);
        window.location.reload(false);
    }


    function changeCurrentCargo(e) {
        editMode = !editMode;

        if (document.getElementById(cargo.id + 'name-input')) {
            document.getElementById(cargo.id + 'name-input').hidden = !editMode;
        }

        if (document.getElementById(cargo.id + 'source-input')) {
            document.getElementById(cargo.id + 'source-input').hidden = !editMode;
        }

        if (document.getElementById(cargo.id + 'destination-input')) {
            document.getElementById(cargo.id + 'destination-input').hidden = !editMode;
        }

        if (document.getElementById(cargo.id + 'transportation_cost-input')) {
            document.getElementById(cargo.id + 'transportation_cost-input').hidden = !editMode;
        }
    }


    function changeName(event) {
        if (document.getElementById(cargo.id + 'name')) {
            document.getElementById(cargo.id + 'name').innerText = event.target.value;
        }
    }

    function changeSource(event) {
        if (document.getElementById(cargo.id + 'source')) {
            document.getElementById(cargo.id + 'source').innerText = event.target.value;
        }
    }

    function changeDestination(event) {
        if (document.getElementById(cargo.id + 'destination')) {
            document.getElementById(cargo.id + 'destination').innerText = event.target.value;
        }
    }

    function changeTransportationCost(event) {
        if (document.getElementById(cargo.id + 'transportation_cost')) {
            document.getElementById(cargo.id + 'transportation_cost').innerText = event.target.value;
        }
    }

    return (
        <div style={cardStyle}>
            <div style={cardHeaderStyle}>
                <Button onClick={deleteCurrentCargo}> delete </Button>

                <Button style={{
                    width: '60px',
                    position: 'inherit',
                    background: '#6B9080',
                    alignItems: 'center',
                    justifyContent: 'center',
                    color: 'white',
                    fontSize: '10px'
                }}
                        onClick={changeCurrentCargo}
                > change
                </Button>

                <div style={{flex: 1, display: 'flex', flexDirection: 'row'}}>
                    <h1 id={cargo.id + 'name'} style={{
                        margin: '0 0 0 40px',
                        padding: '10px 0 0 0',
                        width: '200px',
                        display: 'flex'
                    }}>{cargo.name}</h1>
                    <input hidden={true} style={{width: '150px'}} id={cargo.id + 'name-input'} type="text"
                           onChange={changeName}/>
                </div>

                <div style={{flex: 1, display: 'flex', flexDirection: 'row'}}>
                    <h2 id={cargo.id + 'source'}
                        style={{margin: '10px 10px 0 40px', display: 'flex'}}> {cargo.source_location}</h2>
                    <input hidden={true} style={{width: '80px'}} id={cargo.id + 'source-input'} type="text"
                           onChange={changeSource}/>

                    <h2 style={{margin: '10px 0 0 10px', display: 'flex'}}> - </h2>

                    <h2 id={cargo.id + 'destination'}
                        style={{margin: '10px 20px 0 20px', display: 'flex'}}> {cargo.destination}</h2>
                    <input hidden={true} style={{width: '80px'}} id={cargo.id + 'destination-input'} type="text"
                           onChange={changeDestination}/>
                </div>

            </div>
            <div style={cardDataSectionStyle}>
                <div style={cardDataStyle}>{'Transportation cost: '}</div>
                <div id={cargo.id + 'transportation_cost'} style={cardDataStyle}>{cargo.transportation_cost}</div>
                <input hidden={true} style={{width: '80px', margin: '0 10px'}} id={cargo.id + 'transportation_cost-input'} type="text"
                       onChange={changeTransportationCost}/>
            </div>
            <div style={cardDataSectionStyle}>
                <div
                    style={cardDataStyle}>{'Parameters: '} {cargo.width} {'x'} {cargo.height} {'x'} {cargo.length} </div>
            </div>
            <div style={cardDataSectionStyle}>
                <div style={cardDataStyle}>{'Weight: '} {cargo.weight} </div>
            </div>

        </div>
    );
};

export default Cargo;