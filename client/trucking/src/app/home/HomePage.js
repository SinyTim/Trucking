import React from 'react';
import {authenticationService} from "../_services/authentication.service";

export class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            userFromApi: null
        };
    }

    componentDidMount() {
        const { currentUser } = this.state;
        // userService.getById(currentUser.id).then(userFromApi => this.setState({ userFromApi }));
    }

    render() {
        const { currentUser, userFromApi } = this.state;
        return (
            <div style={{height: '100%'}}>
                <h1>Hello, {currentUser.firstName}</h1>
                <p>Your role is: <strong>{currentUser.role}</strong>.</p>
            </div>
        );
    }
}