import React, { useState, useEffect } from 'react';
import { PlayerTable } from '../components/PlayerTable';
import { PlayerForm } from '../components/PlayerForm';
import { PlayerService } from '../services/PlayerService';
import { Book, Plus } from 'lucide-react';

export const PlayerManagementPage = () => {
    const [players, setPlayers] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalMode, setModalMode] = useState('create');

    useEffect(() => {
        fetchPlayers();
    }, []);

    const fetchPlayers = async () => {
        try {
            const data = await PlayerService.fetchAllPlayers();
            setPlayers(data);
        } catch (error) {
            console.error('Error fetching players:', error);
            alert('Failed to fetch players');
        }
    };

    const handleSearchByTeam = async (teamId) => {
        try {
            const data = await PlayerService.fetchPlayersByTeam(teamId);
            setPlayers(data);
        } catch (error) {
            console.error('Error searching players:', error);
            alert('Failed to search players');
        }
    };

    const handleCreatePlayer = async (playerData) => {
        try {
            {playerData.position === "Torhüter" ? playerData.type = "GOALIE" : playerData.type = "FIELD"}
            const newPlayer = await PlayerService.createPlayer(playerData);
            setPlayers([...players, newPlayer]);
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error creating player:', error);
            alert('Failed to create player');
        }
    };

    const handleUpdatePlayer = async (playerData) => {
        try {
            {playerData.position === "Torhüter" ? playerData.type = "GOALIE" : playerData.type = "FIELD"}
            const updatedPlayer = await PlayerService.updatePlayer(selectedPlayer.id, playerData);
            setPlayers(players.map(p => p.id === updatedPlayer.id ? updatedPlayer : p));
            setIsModalOpen(false);
            setSelectedPlayer(null);
        } catch (error) {
            console.error('Error updating player:', error);
            alert('Failed to update player');
        }
    };

    const handleDeletePlayer = async (playerId) => {
        try {
            await PlayerService.deletePlayer(playerId);
            setPlayers(players.filter(p => p.id !== playerId));
        } catch (error) {
            console.error('Error deleting player:', error);
            alert('Failed to delete player');
        }
    };

    const openCreateModal = () => {
        setModalMode('create');
        setSelectedPlayer(null);
        setIsModalOpen(true);
    };

    const openEditModal = (player) => {
        setModalMode('edit');
        setSelectedPlayer(player);
        setIsModalOpen(true);
    };

    return (
        <div className="container mx-auto p-6 bg-gray-50 min-h-screen">
            <div className="flex items-center justify-between mb-6">
                <div className="flex items-center space-x-4">
                    <Book className="h-8 w-8 text-blue-600" />
                    <h1 className="text-3xl font-bold text-gray-800">Hockey Player Management</h1>
                </div>
                <button
                    onClick={openCreateModal}
                    className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 flex items-center"
                >
                    <Plus className="mr-2" /> Add Player
                </button>
            </div>

            <PlayerTable
                players={players}
                onEdit={openEditModal}
                onDelete={handleDeletePlayer}
                onSearchByTeam={handleSearchByTeam}
            />

            {isModalOpen && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-6 rounded-lg w-96">
                        <h2 className="text-xl font-semibold mb-4">
                            {modalMode === 'create' ? 'Add New Player' : 'Edit Player'}
                        </h2>
                        <PlayerForm
                            player={selectedPlayer}
                            mode={modalMode}
                            onSubmit={modalMode === 'create' ? handleCreatePlayer : handleUpdatePlayer}
                            onCancel={() => setIsModalOpen(false)}
                        />
                    </div>
                </div>
            )}
        </div>
    );
};