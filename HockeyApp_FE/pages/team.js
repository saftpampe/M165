import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Team() {
    const [teams, setTeams] = useState([]);
    const [ausgewaehltesTeam, setAusgewaehltesTeam] = useState(null);

    useEffect(() => {
        // Hole die Teams vom Backend
        axios.get("http://localhost:8080/api/team")
            .then(response => setTeams(response.data)) // Speichern der Teams im State
            .catch(error => {
                console.error("Fehler beim Laden der Teams:", error);
                alert("Fehler beim Laden der Team-Daten.");
            });
    }, []);

    const handleAuswahl = (event) => {
        const teamId = event.target.value;
        const gewaehltesTeam = teams.find(t => t.id === teamId);
        setAusgewaehltesTeam(gewaehltesTeam); // Setze das ausgewählte Team
    };

    return (
        <div className="p-4">
            <h1 className="text-xl font-bold mb-4">Team Auswahl</h1>
            {/* Dropdown-Menü für Team-Auswahl */}
            <select onChange={handleAuswahl} className="border p-2 rounded">
                <option value="">Team wählen...</option>
                {teams.map(t => (
                    <option key={t.id} value={t.id}>{t.name}</option>
                ))}
            </select>

            {/* Anzeige der Details des ausgewählten Teams */}
            {ausgewaehltesTeam && (
                <div className="mt-4 border p-4 rounded">
                    <h2 className="text-lg font-semibold">{ausgewaehltesTeam.name}</h2>
                    <p>Gründungsjahr: {ausgewaehltesTeam.gruendungsjahr}</p>
                    <p>Erfolge:</p>
                    <ul>
                        {ausgewaehltesTeam.erfolge.map((erfolg, index) => (
                            <li key={index}>{erfolg}</li>
                        ))}
                    </ul>
                    <p>Punkte: {ausgewaehltesTeam.punkte}</p>
                    <p>Arena-ID: {ausgewaehltesTeam.arena_id}</p>
                </div>
            )}
        </div>
    );
}
