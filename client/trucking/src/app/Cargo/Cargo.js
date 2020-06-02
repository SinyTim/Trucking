import React from 'react';
import {cargoesService} from "./cargoes.service";
import Button from "@material-ui/core/Button";

export default class Cargo extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            editMode: false,
            cargo: props.cargo,
            ownerId: props.ownerId,
            ownerRole: props.ownerRole,
        };
    }

    cardStyle = {
        width: '440px',
        height: '420px',
        margin: '30px 30px 30px 30px',
        background: '#CCE3DE',
        borderRadius: '5px',
    };

    cardHeaderStyle = {
        width: '100%',
        height: '130px',
        background: '#A4C3B2',
        borderRadius: '5px 5px 0 0',
    };

    cardDataSectionStyle = {
        borderBottom: '2px solid #000000',
        marginLeft: '10%',
        paddingBottom: '10px',
        width: '80%',
        flex: 1,
        display: 'flex',
    };

    cardDataStyle = {
        padding: '10px 10px  0 0',
        fontSize: '20px',
        display: 'flex',

    };

    deleteCurrentCargo() {
        cargoesService.deleteCargo(this.state.cargo, this.state.cargo.ownerId);

    }

    changeCurrentCargo() {
        this.setState({editMode: true});
        if (document.getElementById(this.state.cargo.id + 'ok')) {
            document.getElementById(this.state.cargo.id + 'ok').style.display = 'initial';
        }
    }

    saveChangesInCurrentCargo() {
        this.setState({editMode: false});
        if (document.getElementById(this.state.cargo.id + 'ok')) {
            document.getElementById(this.state.cargo.id + 'ok').style.display = 'none';
        }
        cargoesService.changeCargo(this.state.cargo, this.state.ownerId);
    }

    changeName(event) {
        if (document.getElementById(this.state.cargo.id + 'name')) {
            document.getElementById(this.state.cargo.id + 'name').innerText = event.target.value;
        }
        this.state.cargo.name = event.target.value;
    }

    changeSource(event) {
        if (document.getElementById(this.state.cargo.id + 'source')) {
            document.getElementById(this.state.cargo.id + 'source').innerText = event.target.value;
        }
        this.state.cargo.source_location = event.target.value;
    }

    changeDestination(event) {
        if (document.getElementById(this.state.cargo.id + 'destination')) {
            document.getElementById(this.state.cargo.id + 'destination').innerText = event.target.value;
        }
        this.state.cargo.destination = event.target.value;
    }

    changeTransportationCost(event) {
        if (document.getElementById(this.state.cargo.id + 'transportation_cost')) {
            document.getElementById(this.state.cargo.id + 'transportation_cost').innerText = event.target.value;
        }
        this.state.cargo.transportation_cost = event.target.value;
    }

    changeWidth(event) {
        if (document.getElementById(this.state.cargo.id + 'width')) {
            document.getElementById(this.state.cargo.id + 'width').innerText = event.target.value;
        }
        this.state.cargo.width = event.target.value;
    }

    changeHeight(event) {
        if (document.getElementById(this.state.cargo.id + 'height')) {
            document.getElementById(this.state.cargo.id + 'height').innerText = event.target.value;
        }
        this.state.cargo.height = event.target.value;
    }

    changeLength(event) {
        if (document.getElementById(this.state.cargo.id + 'length')) {
            document.getElementById(this.state.cargo.id + 'length').innerText = event.target.value;
        }
        this.state.cargo.length = event.target.value;
    }

    changeWeight(event) {
        if (document.getElementById(this.state.cargo.id + 'weight')) {
            document.getElementById(this.state.cargo.id + 'weight').innerText = event.target.value;
        }
        this.state.cargo.weight = event.target.value;
    }

    render() {

        const editMode = this.state.editMode;

        return (
            <div style={this.cardStyle}>
                <div style={this.cardHeaderStyle}>
                    <Button style={{
                        width: '60px',
                        position: 'inherit',
                        background: '#6B9080',
                        alignItems: 'center',
                        justifyContent: 'center',
                        color: 'white',
                        fontSize: '10px'
                    }}
                            onClick={this.deleteCurrentCargo.bind(this)}> delete </Button>

                    <Button style={{
                        width: '60px',
                        position: 'inherit',
                        background: '#6B9080',
                        alignItems: 'center',
                        justifyContent: 'center',
                        color: 'white',
                        fontSize: '10px'
                    }}
                            onClick={this.changeCurrentCargo.bind(this)}> change </Button>

                    <Button id={this.state.cargo.id + 'ok'}
                            style={{
                                width: '60px',
                                position: 'inherit',
                                background: '#6B9080',
                                alignItems: 'center',
                                justifyContent: 'center',
                                color: 'white',
                                fontSize: '10px',
                                display: 'none'
                            }}
                            onClick={this.saveChangesInCurrentCargo.bind(this)}> OK </Button>

                    { this.state.ownerRole === 'Carrier' && <input type="checkbox"/> }

                    <div style={{flex: 1, display: 'flex', flexDirection: 'row'}}>
                        <h1 id={this.state.cargo.id + 'name'} style={{
                            margin: '0 0 0 40px',
                            padding: '10px 0 0 0',
                            width: '200px',
                            display: 'flex'
                        }}>{this.state.cargo.name}</h1>
                        <input hidden={!editMode} style={{width: '150px'}} id={this.state.cargo.id + 'name-input'}
                               type="text"
                               onChange={this.changeName.bind(this)}/>
                    </div>

                    <div style={{flex: 1, display: 'flex', flexDirection: 'row'}}>
                        <h2 id={this.state.cargo.id + 'source'}
                            style={{
                                margin: '10px 10px 0 40px',
                                display: 'flex'
                            }}> {this.state.cargo.source_location}</h2>
                        <input hidden={!editMode} style={{width: '80px'}} id={this.state.cargo.id + 'source-input'}
                               type="text"
                               onChange={this.changeSource.bind(this)}/>

                        <h2 style={{margin: '10px 0 0 10px', display: 'flex'}}> - </h2>

                        <h2 id={this.state.cargo.id + 'destination'}
                            style={{margin: '10px 20px 0 20px', display: 'flex'}}> {this.state.cargo.destination}</h2>
                        <input hidden={!editMode} style={{width: '80px'}} id={this.state.cargo.id + 'destination-input'}
                               type="text"
                               onChange={this.changeDestination.bind(this)}/>
                    </div>

                </div>

                <div style={Object.assign({}, this.cardDataSectionStyle, {flexDirection: 'row'})}>
                    <div style={this.cardDataStyle}>{'Transportation cost: '}</div>
                    <div id={this.state.cargo.id + 'transportation_cost'}
                         style={this.cardDataStyle}>{this.state.cargo.transportation_cost}</div>
                    <input hidden={!editMode} style={{width: '80px', margin: '0 10px'}}
                           id={this.state.cargo.id + 'transportation_cost-input'} type="text"
                           onChange={this.changeTransportationCost.bind(this)}/>
                </div>

                <div style={Object.assign({}, this.cardDataSectionStyle, {flexDirection: 'column'})}>
                    <div style={this.cardDataStyle}>{'Parameters: '} </div>
                    <div style={{display: 'flex', flexDirection: 'row'}}>
                        <div style={Object.assign({}, this.cardDataStyle, {marginLeft: '80px'})}> {'width: '} </div>
                        <div id={this.state.cargo.id + 'width'}
                             style={Object.assign({}, this.cardDataStyle, {marginLeft: '10px'})}> {this.state.cargo.width} </div>

                        <input hidden={!editMode} style={{width: '80px', margin: '0 10px'}}
                               id={this.state.cargo.id + 'width-input'}
                               type="text"
                               onChange={this.changeWidth.bind(this)}/>
                    </div>
                    <div style={{display: 'flex', flexDirection: 'row'}}>
                        <div style={Object.assign({}, this.cardDataStyle, {marginLeft: '80px'})}> {'height: '} </div>
                        <div id={this.state.cargo.id + 'height'}
                             style={Object.assign({}, this.cardDataStyle, {marginLeft: '10px'})}>{this.state.cargo.height}  </div>

                        <input hidden={!editMode} style={{width: '80px', margin: '0 10px'}}
                               id={this.state.cargo.id + 'height-input'}
                               type="text"
                               onChange={this.changeHeight.bind(this)}/>
                    </div>
                    <div style={{display: 'flex', flexDirection: 'row'}}>
                        <div style={Object.assign({}, this.cardDataStyle, {marginLeft: '80px'})}> {'length: '} </div>
                        <div id={this.state.cargo.id + 'length'}
                             style={Object.assign({}, this.cardDataStyle, {marginLeft: '10px'})}> {this.state.cargo.length} </div>

                        <input hidden={!editMode} style={{width: '80px', margin: '0 10px'}}
                               id={this.state.cargo.id + 'length-input'}
                               type="text"
                               onChange={this.changeLength.bind(this)}/>
                    </div>

                    <div style={{display: 'flex', flexDirection: 'row'}}>
                        <div style={this.cardDataStyle}> {'Weight: '} </div>
                        <div id={this.state.cargo.id + 'weight'}
                             style={Object.assign({}, this.cardDataStyle, {marginLeft: '10px'})}> {this.state.cargo.weight} </div>
                        <input hidden={!editMode} style={{width: '80px', margin: '0 10px'}}
                               id={this.state.cargo.id + 'weight-input'}
                               type="text"
                               onChange={this.changeWeight.bind(this)}/>
                    </div>
                </div>

                <div style={this.cardDataSectionStyle}>
                    <div style={{display: 'flex', flexDirection: 'row'}}>
                        <div style={this.cardDataStyle}> {'Status: '} </div>
                        <div style={Object.assign({}, this.cardDataStyle, {marginLeft: '10px'})}> {this.state.cargo.status} </div>
                    </div>
                </div>

            </div>
        );
    }
};