import React from 'react';
import {authenticationService} from "../_services/authentication.service";
import {userService} from "../_services/user.service";

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
        userService.getById(currentUser.id).then(userFromApi => this.setState({ userFromApi }));
    }

    render() {
        const { currentUser, userFromApi } = this.state;
        return (
            <div style={{height: '100%'}}>
                <h1>Home</h1>
                <p>Your role is: <strong>{currentUser.role}</strong>.</p>
                <p>This page can be accessed by all authenticated users.</p>
                <div>
                    Current user from secure api end point:
                    {userFromApi &&
                    <ul>
                        <li>{userFromApi.firstName} {userFromApi.lastName}</li>
                    </ul>
                    }
                </div>
            </div>
        );
    }
}