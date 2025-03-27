import React, { useState, useEffect } from 'react';
import { Trash2, Edit, PlusCircle } from 'lucide-react';

const PlayerManagement = () => {
    const [players, setPlayers] = useState([]);
    const [currentPlayer, setCurrentPlayer] = useState({
        vorname: '',
        nachname: '',
        position: '',
        geburtsdatum: '',
        nationalitaet: '',
        unterVertragBis: '',
        saves: 0,
        savePercent: 0,
        nummer: 0,
        marktwert: 0,
        teamId: ''
    });
    const [isEditing, setIsEditing] = useState(false);
    const [isModalOpen, setIsModalOpen] = useState(false);

    // Fetch players
    const fetchPlayers = async () => {
        try {
            const response = await fetch('http://localhost:8080/players');
            const data = await response.json();
            setPlayers(data);
        } catch (error) {
            console.error('Error fetching players:', error);
        }
    };

    useEffect(() => {
        fetchPlayers();
    }, []);

    // Create/Update Player
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const url = isEditing
                ? `http://localhost:8080/players/${currentPlayer.id}`
                : 'http://localhost:8080/players';

            const method = isEditing ? 'PUT' : 'POST';

            const response = await fetch(url, {
                method,
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(currentPlayer)
            });

            if (response.ok) {
                fetchPlayers();
                resetForm();
                setIsModalOpen(false);
            }
        } catch (error) {
            console.error('Error saving player:', error);
        }
    };

    // Delete Player
    const handleDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/players/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                fetchPlayers();
            }
        } catch (error) {
            console.error('Error deleting player:', error);
        }
    };

    // Edit Player
    const handleEdit = (player) => {
        setCurrentPlayer(player);
        setIsEditing(true);
        setIsModalOpen(true);
    };

    // Reset Form
    const resetForm = () => {
        setCurrentPlayer({
            vorname: '',
            nachname: '',
            position: '',
            geburtsdatum: '',
            nationalitaet: '',
            unterVertragBis: '',
            saves: 0,
            savePercent: 0,
            nummer: 0,
            marktwert: 0,
            teamId: ''
        });
        setIsEditing(false);
    };

    return (
        <div className="container mx-auto p-4">
            <div className="bg-white shadow-md rounded-lg overflow-hidden">
                <div className="p-4 bg-gray-100 border-b flex justify-between items-center">
                    <h2 className="text-xl font-semibold text-gray-800">Hockey Players Management</h2>
                    <button
                        onClick={() => {
                            resetForm();
                            setIsModalOpen(true);
                        }}
                        className="flex items-center bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition"
                    >
                        <PlusCircle className="mr-2 h-4 w-4" /> Add New Player
                    </button>
                </div>

                {/* Player Table */}
                <div className="overflow-x-auto">
                    <table className="w-full">
                        <thead className="bg-gray-100 border-b">
                        <tr>
                            <th className="p-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                            <th className="p-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Position</th>
                            <th className="p-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Number</th>
                            <th className="p-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Team</th>
                            <th className="p-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                        </tr>
                        </thead>
                        <tbody className="bg-white divide-y divide-gray-200">
                        {players.map((player) => (
                            <tr key={player.id} className="hover:bg-gray-50">
                                <td className="p-3">{player.vorname} {player.nachname}</td>
                                <td className="p-3">{player.position}</td>
                                <td className="p-3">{player.nummer}</td>
                                <td className="p-3">{player.teamId}</td>
                                <td className="p-3 flex space-x-2">
                                    <button
                                        onClick={() => handleEdit(player)}
                                        className="text-blue-500 hover:text-blue-700"
                                    >
                                        <Edit className="h-5 w-5" />
                                    </button>
                                    <button
                                        onClick={() => handleDelete(player.id)}
                                        className="text-red-500 hover:text-red-700"
                                    >
                                        <Trash2 className="h-5 w-5" />
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>

            {/* Modal for Add/Edit Player */}
            {isModalOpen && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                    <div className="bg-white rounded-lg shadow-xl p-6 w-full max-w-md">
                        <h2 className="text-xl font-semibold mb-4">
                            {isEditing ? 'Edit Player' : 'Add New Player'}
                        </h2>
                        <form onSubmit={handleSubmit} className="space-y-4">
                            <div className="grid grid-cols-2 gap-4">
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="First Name"
                                    value={currentPlayer.vorname}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, vorname: e.target.value})}
                                    required
                                />
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Last Name"
                                    value={currentPlayer.nachname}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, nachname: e.target.value})}
                                    required
                                />
                            </div>
                            <input
                                className="w-full p-2 border rounded"
                                placeholder="Position"
                                value={currentPlayer.position}
                                onChange={(e) => setCurrentPlayer({...currentPlayer, position: e.target.value})}
                            />
                            <div className="grid grid-cols-2 gap-4">
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Birth Date"
                                    type="date"
                                    value={currentPlayer.geburtsdatum}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, geburtsdatum: e.target.value})}
                                />
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Nationality"
                                    value={currentPlayer.nationalitaet}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, nationalitaet: e.target.value})}
                                />
                            </div>
                            <div className="grid grid-cols-2 gap-4">
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Contract Until"
                                    type="date"
                                    value={currentPlayer.unterVertragBis}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, unterVertragBis: e.target.value})}
                                />
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Player Number"
                                    type="number"
                                    value={currentPlayer.nummer}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, nummer: Number(e.target.value)})}
                                />
                            </div>
                            <div className="grid grid-cols-2 gap-4">
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Saves"
                                    type="number"
                                    value={currentPlayer.saves}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, saves: Number(e.target.value)})}
                                />
                                <input
                                    className="w-full p-2 border rounded"
                                    placeholder="Save Percentage"
                                    type="number"
                                    step="0.01"
                                    value={currentPlayer.savePercent}
                                    onChange={(e) => setCurrentPlayer({...currentPlayer, savePercent: Number(e.target.value)})}
                                />
                            </div>
                            <input
                                className="w-full p-2 border rounded"
                                placeholder="Team ID"
                                value={currentPlayer.teamId}
                                onChange={(e) => setCurrentPlayer({...currentPlayer, teamId: e.target.value})}
                            />
                            <div className="flex justify-end space-x-2">
                                <button
                                    type="button"
                                    onClick={() => setIsModalOpen(false)}
                                    className="px-4 py-2 bg-gray-200 text-gray-800 rounded hover:bg-gray-300"
                                >
                                    Cancel
                                </button>
                                <button
                                    type="submit"
                                    className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                                >
                                    {isEditing ? 'Update Player' : 'Create Player'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
};

export default PlayerManagement;