import {authHeader} from "../_helpers/auth-header";

export const userService = {
    getAll,
    getById,
    changeBanStatus,
    addUser,
};

function getAll(adminId) {
    const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };

    return fetch(`api/${adminId}/users`, requestOptions)
        .then(response => {
            return response.json();
        });
}

function changeBanStatus(adminId, id, banned) {
    const requestOptions = {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({id: id, banned: banned})
    };

    return fetch(`api/${adminId}/users`, requestOptions)
        .then(response => {
            // return response.json();
        });
}

function addUser(adminId, user) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    };

    return fetch(`api/${adminId}/users`, requestOptions)
        .then(response => {
            // return response.json();
        });
}

function getById(id) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    // return fetch(`/users/${id}`, requestOptions).then(handleResponse);
}