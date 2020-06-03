
export const cargoesService = {
    getOwnerCargoesById,
    addCargo,
    deleteCargo,
    changeCargo,
    getCargo,
};

function getOwnerCargoesById(ownerId) {
    const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };

    return fetch(`api/${ownerId}/cargo`, requestOptions)
        .then(response => {
            return response.json();
        });
}

function getCargo(ownerId, cargoId) {
    const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };

    return fetch(`api/${ownerId}/cargo/${cargoId}`, requestOptions)
        .then(response => {
            return response.json();
        });
}

function addCargo(cargo, ownerId) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(cargo)
    };

    return fetch(`api/${ownerId}/cargo`, requestOptions)
        .then(res => {
            // console.log(res);
        });
}

function changeCargo(cargo, ownerId) {
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(cargo)
    };

    return fetch(`api/${ownerId}/cargo`, requestOptions)
        .then(res => {
            // console.log(res);
        });
}

function deleteCargo(cargo, ownerId) {
    const requestOptions = {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(cargo)
    };

    return fetch(`api/${ownerId}/cargo`, requestOptions)
        .then(res => {
            console.log(res);
        });
}
