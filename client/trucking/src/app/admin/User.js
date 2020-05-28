import React from 'react';

let User = function statelessFunctionComponentClass(props) {

    let user = props.user;

    const cardStyle = {
        width: '500px',
        height: '120px',
        margin: '30px 30px 30px 30px',
        background: '#CCE3DE',
        borderRadius: '5px',
    };

    const cardHeaderStyle = {
        height: '20px',
        background: '#A4C3B2',
        borderRadius: '5px 5px 0 0',
        fontSize: '20px',
        fontWeight: '600',
        padding: '20px'
    };

    const cardDataSectionStyle = {
        borderBottom: '2px solid #000000',
        marginLeft: '10%',
        paddingBottom: '10px',
        width: '80%',
        flex: 1,
        display: 'flex',
    };

    const cardDataStyle = {
        padding: '10px 10px 0 20px',
        fontSize: '20px',
        display: 'flex',

    };

    return (
        <div style={cardStyle}>
            <div style={cardHeaderStyle}>
                {user.firstName} {user.lastName}
            </div>

            <div style={cardDataStyle}>
               Role: {user.role}
            </div>
        </div>
    );
};

export default User;