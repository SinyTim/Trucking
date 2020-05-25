
export const cargoesService = {
    getOwnerCargoesById,
    addCargo,
};

function getOwnerCargoesById(ownerId) {
    const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };

    return fetch(`/users/${ownerId}/cargo`, requestOptions)
        .then(response => {
            return response.json();
        });
}

function addCargo(cargo, ownerId) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: {'cargo' : cargo, 'id': ownerId}
    };

    return fetch(`/users/${ownerId}/cargo`, requestOptions)
        .then(res => {
            console.log(res);
        });
}
