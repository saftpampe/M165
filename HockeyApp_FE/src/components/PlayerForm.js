import React, {useState, useEffect} from 'react';
import {validatePlayer} from '../utils/validation';

export const PlayerForm = ({
                               player,
                               onSubmit,
                               onCancel,
                               mode = 'create'
                           }) => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        teamId: '',
        position: '',
        jerseyNumber: '',
        dateOfBirth: '',
        nationality: '',
        contractExpiration: '',
        worth: '',
        points: '',
        saves: '',
        savePercentage: ''

    });
    const [errors, setErrors] = useState({});

    useEffect(() => {
        if (mode === 'edit' && player) {
            setFormData({
                firstName: player.vorname || '',
                lastName: player.nachname || '',
                teamId: player.teamId || '',
                position: player.position || '',
                jerseyNumber: player.nummer || '',
                dateOfBirth: player.geburtsdatum || '',
                nationality: player.nationalitaet || '',
                contractExpiration: player.unterVertragBis || '',
                worth: player.marktwert || '',
                points: player.punkte || '',
                saves: player.saves || '',
                savePercentage: player.savePercentage || '',
            });
        }
    }, [player, mode]);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));

        // Clear error when user starts typing
        if (errors[name]) {
            setErrors(prev => {
                const newErrors = {...prev};
                delete newErrors[name];
                return newErrors;
            });
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = validatePlayer(formData);

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4 max-w-xl mx-auto">
            <div className="grid grid-cols-1 gap-4">
                <div>
                    <label className="block mb-2">Vorname</label>
                    <input
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        className={`w-full border rounded px-3 py-2 ${errors.firstName ? 'border-red-500' : ''}`}
                    />
                    {errors.firstName && (
                        <p className="text-red-500 text-sm mt-1">{errors.firstName}</p>
                    )}
                </div>
                <div>
                    <label className="block mb-2">Nachname</label>
                    <input
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        className={`w-full border rounded px-3 py-2 ${errors.lastName ? 'border-red-500' : ''}`}
                    />
                    {errors.lastName && (
                        <p className="text-red-500 text-sm mt-1">{errors.lastName}</p>
                    )}
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                    <label className="block mb-2">Nummer</label>
                    <input
                        type="number"
                        name="jerseyNumber"
                        value={formData.jerseyNumber}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                    />
                </div>
                <div>
                    <label className="block mb-2">Position</label>
                    <select
                        name="position"
                        value={formData.position}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                        required
                    >
                        <option value="">Select Position</option>
                        <option value="Center">Center</option>
                        <option value="Linker Flügel">Linker Flügel</option>
                        <option value="Rechter Flügel">Rechter Flügel</option>
                        <option value="Verteidiger">Verteidiger</option>
                        <option value="Torhüter">Torhüter</option>
                    </select>
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                    <label className="block mb-2">Geburtsdatum</label>
                    <input
                        type="date"
                        name="dateOfBirth"
                        value={formData.dateOfBirth}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                    />
                </div>
                <div>
                    <label className="block mb-2">Nationalität</label>
                    <input
                        type="text"
                        name="nationality"
                        value={formData.nationality}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                    />
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                    <label className="block mb-2">Marktwert</label>
                    <input
                        type="number"
                        name="worth"
                        value={formData.worth}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                    />
                </div>
                <div>
                    <label className="block mb-2">Vertrag bis</label>
                    <input
                        type="date"
                        name="contractExpiration"
                        value={formData.contractExpiration}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                    />
                </div>
            </div>

            <div className="grid grid-cols-1 gap-4">
                <div>
                    <label className="block mb-2">Team</label>
                    <select
                        name="teamId"
                        value={formData.teamId}
                        onChange={handleChange}
                        className="w-full border rounded px-3 py-2"
                        required
                    >
                        <option value="">Select Team</option>
                        <option value="66045f8c2a6b3d5f9c4e7a12">Fribourg Gotteron</option>
                        <option value="66045f8c2a6b3d5f9c4e7a13">SC Bern</option>
                        <option value="66045f8c2a6b3d5f9c4e7a14">ZSC Lions</option>
                        <option value="66045f8c2a6b3d5f9c4e7a15">HC Ajoje</option>
                        <option value="66045f8c2a6b3d5f9c4e7a16">HC Lausanne</option>
                    </select>
                    {errors.teamId && (
                        <p className="text-red-500 text-sm mt-1">{errors.teamId}</p>
                    )}
                </div>
                {formData.position !== 'Torhüter' && (
                    <div>
                        <label className="block mb-2">Punkte</label>
                        <input
                            type="number"
                            name="points"
                            value={formData.points}
                            onChange={handleChange}
                            className="w-full border rounded px-3 py-2"
                        />
                    </div>
                )}
            </div>

            {formData.position === 'Torhüter' && (
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                        <label className="block mb-2">Saves</label>
                        <input
                            type="number"
                            name="saves"
                            value={formData.saves}
                            onChange={handleChange}
                            className="w-full border rounded px-3 py-2"
                        />
                    </div>
                    <div>
                        <label className="block mb-2">Save %</label>
                        <input
                            type="number"
                            name="savePercentage"
                            value={formData.savePercentage}
                            onChange={handleChange}
                            className="w-full border rounded px-3 py-2"
                        />
                    </div>
                </div>
            )}

            <div className="flex justify-end space-x-2">
                <button
                    type="button"
                    onClick={onCancel}
                    className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300"
                >
                    Cancel
                </button>
                <button
                    type="submit"
                    className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                >
                    {mode === 'create' ? 'Create' : 'Update'}
                </button>
            </div>
        </form>
    );
};