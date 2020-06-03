import React from 'react';
import {authenticationService} from "../_services/authentication.service";
import {transportationsService} from "./transportations.service";
import Transportation from "./Transportation";

export class TransportationsPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isDialogOpen: false,
            currentUser: authenticationService.currentUserValue,
            transportations: []
        };
    }

    componentDidMount() {
        transportationsService.getTransportations(this.state.currentUser.id).then(res => this.setState({transportations: res}));
    }

    createTransportation(transportation) {
        return <Transportation transportation={transportation} key={transportation.id}
                               ownerRole={this.state.currentUser.role} ownerId={this.state.currentUser.id}/>;
    }


    render() {
        const transportations = this.state.transportations;

        return (
            <div>
                <h1 style={{textAlign: 'center'}}> Transportations </h1>
                <div>
                    <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'center'}}>
                        {transportations.length ? transportations.map(transportation =>
                                this.createTransportation(transportation))
                            : <span> Loading...</span>}
                    </div>
                </div>
            </div>
        );
    }
}