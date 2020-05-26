import React from 'react';
import {authenticationService} from "../_services/authentication.service";
import Cargo from "./Cargo";
import Button from "@material-ui/core/Button";
import Icon from "@material-ui/core/Icon";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import TextField from "@material-ui/core/TextField";
import Input from "@material-ui/core/Input";
import {cargoesService} from "./cargoes.service";

export class CargoPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isDialogOpen: false,
            currentUser: authenticationService.currentUserValue,
            userFromApi: null,
            cargoes: []
        };
    }

    componentDidMount() {
        const {currentUser} = this.state;
        cargoesService.getOwnerCargoesById(this.state.currentUser.id).then(res => this.setState({cargoes: res}));
        // userService.getById(currentUser.id).then(userFromApi => this.setState({userFromApi}));
    }


    createCargo(cargo) {
        return <Cargo cargo={cargo} key={cargo.id}/>;
    }

    createCargoes(cargoes) {
        return cargoes.map(cargo => this.createCargo(cargo));
    }

    render() {

        const cargo1 = {
            id: '228',
            ownerId: this.state.currentUser.id,

            name: 'Cargo 1',

            weight: '1',
            width: '2',
            height: '3',
            length: '4',

            source_location: 'Minsk',
            destination: 'Paris',

            transportation_cost: '700',
        };

        // const createdCargoes = this.createCargoes([cargo1]);
        // cargoesService.addCargo(cargo1, this.state.currentUser.id);
        // cargoesService.getOwnerCargoesById(this.state.currentUser.id).then(console.log);

        const handleClickOpen = () => {
            this.setState({isDialogOpen: true})
        };

        const handleClose = () => {
            this.setState({isDialogOpen: false})
        };

        const cargoes = this.state.cargoes;
        return (
            <div>
                <h1 style={{textAlign: 'center'}}> My Cargoes</h1>
                <div>
                    <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'center'}}>
                        {this.state.cargoes.length ? this.state.cargoes.map(cargo => this.createCargo(cargo))
                            : <span> Loading...</span>}
                    </div>
                    <div style={{position: 'sticky', bottom: '100px'}}>

                        <div style={{
                            display: 'flex',
                            left: '90%',
                            width: '60px',
                            height: '60px',
                            position: 'inherit',
                        }}>

                            <Button style={{
                                width: '60px',
                                height: '60px',
                                position: 'inherit',
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
                                    const newCargo = {
                                        id: (new Date()).getTime(),
                                        ownerId: this.state.currentUser.id,
                                        name: document.getElementById('name').value,

                                        weight: document.getElementById('weight').value,
                                        width: document.getElementById('width').value,
                                        height: document.getElementById('height').value,
                                        length: document.getElementById('length').value,

                                        source_location: document.getElementById('source_location').value,
                                        destination: document.getElementById('destination').value,

                                        transportation_cost: document.getElementById('transportation_cost').value,
                                    };
                                    cargoesService.addCargo(newCargo, newCargo.ownerId);
                                    this.setState({cargoes: [...cargoes, newCargo]});
                                    handleClose();
                                }}>
                                    <TextField
                                        autoFocus
                                        margin="dense"
                                        id="name"
                                        label="Cargo Name"
                                        type="text"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="source_location"
                                        label="Origin City"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="destination"
                                        label="Destination City"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="height"
                                        label="Height"
                                        type="number"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="width"
                                        label="Width"
                                        type="number"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="length"
                                        label="Length"
                                        type="number"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="weight"
                                        label="Weight"
                                        type="number"
                                        required
                                        fullWidth/>
                                    <TextField
                                        margin="dense"
                                        id="transportation_cost"
                                        label="Cost"
                                        type="number"
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
                    </div>
                </div>
            </div>
        );
    }
}