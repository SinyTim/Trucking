import React from 'react';
import {userService} from "../_services/user.service";
import User from "./User";

export default class AdminPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            users: []
        };
    }

    componentDidMount() {
        userService.getAll().then(users => {
            this.setState({ users: users });
        });
    }

    createUser(user) {
        return <User user={user} key={user.id}/>;
    }

    createUsers(users) {
        return users.map(user => this.createUser(user));
    }

    render() {
        return (
            <div>
                <h1 style={{marginLeft: '50%'}}>Users</h1>
                <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'center'}}>
                    {this.state.users.length > 0 ? this.state.users.map(user => <User user={user} key={user.id}/>)
                        : <span> Loading...</span>}
                </div>
            </div>
        );
    }
}
