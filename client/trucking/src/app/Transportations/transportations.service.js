
export const transportationsService = {
    getTransportations,
    addTransportation,
    changeTransportation
};

function getTransportations(ownerId) {
    const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };

    return fetch(`api/${ownerId}/transportation`, requestOptions)
        .then(response => {
            return response.json();
        });
}

function addTransportation(transportation, ownerId) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(transportation)
    };

    return fetch(`api/${ownerId}/transportation`, requestOptions)
        .then(res => {
            // console.log(res);
        });
}

function changeTransportation(transportation, ownerId) {
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(transportation)
    };

    return fetch(`api/${ownerId}/transportation`, requestOptions)
        .then(res => {
            // console.log(res);
        });
}
