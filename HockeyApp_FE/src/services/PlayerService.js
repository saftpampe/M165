const API_BASE_URL = 'http://localhost:8080/players';

export const PlayerService = {
    async fetchAllPlayers() {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) {
            throw new Error('Failed to fetch players');
        }
        return response.json();
    },

    async fetchPlayersByTeam(teamId) {
        const response = await fetch(`${API_BASE_URL}/team`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ teamId })
        });
        if (!response.ok) {
            throw new Error('Failed to fetch players by team');
        }
        return response.json();
    },

    async createPlayer(playerData) {
        console.log(playerData)
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "vorname": playerData.firstName,
                "nachname": playerData.lastName,
                "teamId": playerData.teamId,
                "position": playerData.position,
                "nummer": playerData.jerseyNumber,
                "geburtsdatum": playerData.dateOfBirth,
                "nationalitaet": playerData.nationality,
                "punkte": playerData.points,
                "saves": playerData.saves,
                "savePercentage": playerData.savePercentage,
                "unterVertragBis": playerData.contractExpiration,
                "marktwert": playerData.worth,
                "type": playerData.type,
            })
        });
        if (!response.ok) {
            throw new Error('Failed to create player');
        }
        return response.json();
    },

    async updatePlayer(playerId, playerData) {
        console.log(playerData, playerId);
        console.log(`${API_BASE_URL}/${playerId}`)

        const response = await fetch(`${API_BASE_URL}/${playerId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "vorname": playerData.firstName,
                "nachname": playerData.lastName,
                "teamId": playerData.teamId,
                "position": playerData.position,
                "nummer": playerData.jerseyNumber,
                "geburtsdatum": playerData.dateOfBirth,
                "nationalitaet": playerData.nationality,
                "punkte": playerData.points,
                "saves": playerData.saves,
                "savePercentage": playerData.savePercentage,
                "unterVertragBis": playerData.contractExpiration,
                "marktwert": playerData.worth,
                "type": playerData.type
            })
        });
        if (!response.ok) {
            throw new Error('Failed to update player');
        }
        return response.json();
    },

    async deletePlayer(playerId) {
        const response = await fetch(`${API_BASE_URL}/${playerId}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Failed to delete player');
        }
    }
};