import React from 'react';
import {BrowserRouter, Link, Route} from 'react-router-dom';

import {LoginPage} from "./app/login/LoginPage";
import {authenticationService} from "./app/_services/authentication.service";
import {HomePage} from "./app/home/HomePage";
import {Role} from "./app/_helpers/role";
import {PrivateRoute} from "./app/PrivateRoute";
import AdminPage from "./app/admin/AdminPage";
import './App.css'
import {CargoPage} from "./app/Cargo/CargoPage";

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: null,
            isAdmin: false
        };
    }

    componentDidMount() {
        authenticationService.currentUser.subscribe(x => this.setState({
            currentUser: x,
            isAdmin: x && x.role === Role.Admin
        }));
    }

    logout() {
        authenticationService.logout();
    }

    render() {
        const {currentUser, isAdmin} = this.state;
        return (
            <BrowserRouter>
                <div style={{height: '100%', background: '#eaf4f4'}}>
                    <div className="navigation" style={{display: 'flex', flexDirection: 'row-reverse'}}>
                        {currentUser &&
                            <div style={{display: 'flex', flexDirection: 'row'}}>
                                <div className="nav-item-div">
                                    <Link to="/" className="nav-item">Home</Link>
                                </div>
                                <div className="nav-item-div">
                                    <Link to="/cargoes" className="nav-item">Cargoes</Link>
                                </div>
                                {isAdmin && <div className="nav-item-div">
                                    {isAdmin && <Link to="/admin" className="nav-item">Users</Link>}
                                </div>}
                                <div className="nav-item-div">
                                    <a onClick={this.logout} className="nav-item" href={'/'}>Logout</a>
                                </div>
                            </div>
                        }
                    </div>
                    <div className="main-div">
                        <PrivateRoute exact path="/" component={HomePage}/>
                        <PrivateRoute exact path="/cargoes" component={CargoPage}/>
                        <PrivateRoute path="/admin" roles={[Role.Admin]} component={AdminPage}/>
                        <Route path="/login" component={LoginPage}/>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}

export default App