import React from 'react';
import {userService} from "../_services/user.service";
import User from "./User";
import {authenticationService} from "../_services/authentication.service";
import Button from "@material-ui/core/Button";
import Icon from "@material-ui/core/Icon";
import Dialog from "@material-ui/core/Dialog/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import TextField from "@material-ui/core/TextField/TextField";
import Input from "@material-ui/core/Input/Input";

export default class AdminPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isDialogOpen: false,
            currentUser: authenticationService.currentUserValue,
            users: []
        };
    }

    componentDidMount() {
        userService.getAll(this.state.currentUser.id).then(users => {
            this.setState({ users: users });
        });
    }

    createUser(user) {
        return <User user={user} key={user.id}/>;
    }

    render() {

        const handleClickOpen = () => {
            this.setState({isDialogOpen: true})
        };

        const handleClose = () => {
            this.setState({isDialogOpen: false})
        };

        return (
            <div>
                <h1 style={{marginLeft: '50%'}}>Users</h1>
                <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'center'}}>
                    {this.state.users.length > 0 ? this.state.users.map(user => {
                            if (this.state.currentUser.id !== user.id)
                                return <User adminId={this.state.currentUser.id} user={user} key={user.id}/>
                        })
                        : <span> Loading...</span>}
                </div>

                <Button style={{
                    width: '60px',
                    height: '60px',
                    float: 'right',
                    background: '#6B9080',
                    borderRadius: '50%',
                    alignItems: 'center',
                    justifyContent: 'center',
                    color: 'white',
                    fontSize: '45px'
                }}
                        onClick={handleClickOpen}
                >
                    <Icon style={{fontSize: '45px'}}> add </Icon>
                </Button>

                <Dialog open={this.state.isDialogOpen} onClose={handleClose}
                        aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">New cargo</DialogTitle>
                    <form action="/" method="POST" onSubmit={(e) => {
                        e.preventDefault();

                        const newUser = {
                            firstName: document.getElementById('first_name').value,
                            lastName: document.getElementById('last_name').value,
                            username: document.getElementById('username').value,

                            password: document.getElementById('password').value,
                            role: document.getElementById('role').value,

                            banned: false,
                        };
                        userService.addUser(this.state.currentUser.id, newUser);
                        this.setState({users: [...this.state.users, newUser]});

                        handleClose();
                    }}>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="first_name"
                            label="User First Name"
                            type="text"
                            required
                            fullWidth/>
                        <TextField
                            margin="dense"
                            id="last_name"
                            label="User Last Name"
                            type="text"
                            required
                            fullWidth/>
                        <TextField
                            margin="dense"
                            id="username"
                            label="Username"
                            type="text"
                            required
                            fullWidth/>
                        <TextField
                            margin="dense"
                            id="password"
                            label="Password"
                            type="text"
                            required
                            fullWidth/>
                        <TextField
                            margin="dense"
                            id="role"
                            label="Role"
                            type="text"
                            required
                            fullWidth/>
                        <Button onClick={handleClose} color="secondary">
                            Cancel
                        </Button>
                        <Input color="primary" type="submit">
                            Create
                        </Input>
                    </form>
                </Dialog>
            </div>
        );
    }
}
