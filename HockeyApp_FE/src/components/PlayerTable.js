import React from 'react';
import { Trash2, Edit } from 'lucide-react';

export const PlayerTable = ({
                                players,
                                onEdit,
                                onDelete,
                                onSearchByTeam
                            }) => {
    return (
        <div className="bg-white shadow-md rounded-lg">
            <div className="flex items-center justify-between p-6 border-b">
                <h2 className="text-2xl font-bold text-gray-800">Players</h2>
                <input
                    type="text"
                    placeholder="Search by Team ID"
                    className="border rounded px-3 py-2 w-64"
                    onChange={(e) => onSearchByTeam(e.target.value)}
                />
            </div>
            <table className="w-full">
                <thead>
                <tr className="bg-gray-100">
                    <th className="p-3 text-left">Name</th>
                    <th className="p-3 text-left">Position</th>
                    <th className="p-3 text-left">Nummer</th>
                    <th className="p-3 text-right">Actions</th>
                </tr>
                </thead>
                <tbody>
                {players.map(player => (
                    <tr key={player.id} className="border-b hover:bg-gray-50">
                        <td className="p-3">{player.vorname} {player.nachname}</td>
                        <td className="p-3">{player.position || 'N/A'}</td>
                        <td className="p-3">{player.nummer || 'N/A'}</td>
                        <td className="p-3 text-right space-x-2">
                            <button
                                onClick={() => onEdit(player)}
                                className="text-blue-500 hover:text-blue-700 mr-2"
                            >
                                <Edit className="h-5 w-5" />
                            </button>
                            <button
                                onClick={() => onDelete(player.id)}
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
    );
};