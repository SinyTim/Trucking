import React from 'react';
import Button from "@material-ui/core/Button";
import {transportationsService} from "./transportations.service";
import {cargoesService} from "../Cargo/cargoes.service";
import {userService} from "../_services/user.service";

export default class Transportation extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            transportation: props.transportation,
            ownerId: props.ownerId,
            ownerRole: props.ownerRole,
            editMode: false,
            cargoNames: [],
            carrier: ''
        };
    }

    componentDidMount() {
        const cargoNamesPromises = this.state.transportation.cargoes.map(
            cargoId => cargoesService.getCargo(this.state.ownerId, cargoId)
                .then(res => res.name)
        );

        Promise.all(cargoNamesPromises).then((newCargoNames)=> this.setState({cargoNames: [ ...this.state.cargoNames, ...newCargoNames ]}));

        userService.getById(this.state.transportation.carrierId).then(res =>  this.setState({carrier: res.username}));
    }

    cardStyle = {
        width: '500px',
        height: '250px',
        margin: '30px 30px 30px 30px',
        background: '#CCE3DE',
        borderRadius: '5px',
    };

    cardHeaderStyle = {
        height: '30px',
        background: '#A4C3B2',
        borderRadius: '5px 5px 0 0',
        fontSize: '20px',
        fontWeight: '600',
        padding: '20px'
    };

    cardDataStyle = {
        padding: '15px 10px 0 20px',
        fontSize: '20px',
    };

    changeCurrentTransportation() {
        this.setState({editMode: true});
        if (document.getElementById(this.state.transportation.id + 'ok')) {
            document.getElementById(this.state.transportation.id + 'ok').style.display = 'initial';
        }
    }

    changeCurrentLocation(event) {
        if (document.getElementById(this.state.transportation.id + 'input')) {
            document.getElementById(this.state.transportation.id + 'currentLocation').innerText = event.target.value;
        }
        this.state.transportation.currentLocation = event.target.value;
    }

    saveChanges() {
        this.setState({editMode: false});
        if (document.getElementById(this.state.transportation.id + 'ok')) {
            document.getElementById(this.state.transportation.id + 'ok').style.display = 'none';
        }
        transportationsService.changeTransportation(this.state.transportation, this.state.ownerId)
    }

    render() {
        const editMode = this.state.editMode;

        return (
            <div style={this.cardStyle}>
                <div style={this.cardHeaderStyle}>
                    <div style={{fontSize: '30px'}}>
                        {this.state.transportation.route[0]} - {this.state.transportation.route[this.state.transportation.route.length - 1]}

                        {this.state.ownerRole === 'Carrier' &&
                        <Button id={this.state.transportation.id + 'ok'}
                                style={{
                                    width: '60px',
                                    position: 'inherit',
                                    background: '#6B9080',
                                    alignItems: 'center',
                                    justifyContent: 'center',
                                    color: 'white',
                                    fontSize: '10px',
                                    display: 'none',
                                    float: 'right'
                                }}
                                onClick={this.saveChanges.bind(this)}> OK </Button>
                        }

                        {this.state.ownerRole === 'Carrier' &&
                        <Button style={{
                            width: '60px',
                            position: 'inherit',
                            background: '#6B9080',
                            alignItems: 'center',
                            justifyContent: 'center',
                            color: 'white',
                            fontSize: '10px',
                            float: 'right'
                        }}
                                onClick={this.changeCurrentTransportation.bind(this)}> change </Button>}
                    </div>
                </div>

                <div style={this.cardDataStyle}>
                    <b> Route: </b>
                    {this.state.transportation.route.length ? this.state.transportation.route.map(city =>
                            <span style={{marginRight: '10px'}}> {city} </span>)
                        : <span> Loading...</span>}
                </div>

                <div style={Object.assign({}, this.cardDataStyle, {display: 'flex', flexDirection: 'row'})}>
                    <b> Current location: </b>
                    <span id={this.state.transportation.id + 'currentLocation'}
                         style={{
                             margin: '0 0 0 20px',
                             display: 'flex'
                         }}>                     {this.state.transportation.currentLocation}
                    </span>
                    <input hidden={!editMode} style={{width: '80px'}} id={this.state.transportation.id + 'input'}
                           type="text"
                           onChange={this.changeCurrentLocation.bind(this)}/>
                </div>

                <div style={this.cardDataStyle}>
                    <b>  Cargoes: </b>
                    {this.state.cargoNames.length ? <span style={{marginRight: '20px'}}> {this.state.cargoNames.map(cargoName => <span> {cargoName} </span>)} </span> : <span> Loading...</span>}
                </div>

                <div style={this.cardDataStyle}>
                    <b>  Carrier: </b>
                    <span> {this.state.carrier} </span>
                </div>
            </div>
        );
    }
}
