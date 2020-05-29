import React from 'react';
import {userService} from "../_services/user.service";

export default class User extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            reload: false,
            user: props.user,
            adminId: props.adminId
        };
    }

    componentDidMount() {
    }

    cardStyle = {
        width: '500px',
        height: '120px',
        margin: '30px 30px 30px 30px',
        background: '#CCE3DE',
        borderRadius: '5px',
    };

    cardHeaderStyle = {
        height: '20px',
        background: '#A4C3B2',
        borderRadius: '5px 5px 0 0',
        fontSize: '20px',
        fontWeight: '600',
        padding: '20px'
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
        padding: '15px 10px 0 20px',
        fontSize: '20px',
    };

    changeUserBlockStatus() {
        userService.changeBanStatus(this.state.adminId, this.state.user.id, !this.state.user.banned);
        this.setState({user: {...this.state.user, banned: !this.state.user.banned}})
    }

    render() {
        return (
            <div style={this.cardStyle}>
                <div style={this.cardHeaderStyle}>
                    {this.state.user.firstName} {this.state.user.lastName}

                    <div style={{float: 'right', marginTop: '-10px'}}>
                        <button onClick={this.changeUserBlockStatus.bind(this)}>
                            {this.state.user.banned ? 'unblock' : 'block'}
                        </button>
                    </div>
                </div>

                <div>
                    <div style={Object.assign({}, this.cardDataStyle, {float: 'left',})}>
                        Role: {this.state.user.role}
                    </div>

                    <div style={Object.assign({}, this.cardDataStyle, {float: 'right', color: '#F2766B'})}>
                        {this.state.user.banned ? 'blocked' : 'not blocked'}
                    </div>
                </div>
            </div>
        );
    }
}
