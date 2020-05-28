import {authHeader} from "../_helpers/auth-header";

export const userService = {
    getAll,
    getById
};

function getAll() {
    const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };

    return fetch(`api/users`, requestOptions)
        .then(response => {
            return response.json();
        });
}

function getById(id) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    // return fetch(`/users/${id}`, requestOptions).then(handleResponse);
}