import React, { useState, useEffect } from "react";
import axios from "axios";

export default function IndexPage() {
    const [spieler, setSpieler] = useState([]);
    const [ausgewaehlterSpieler, setAusgewaehlterSpieler] = useState(null);

    useEffect(() => {
        // Hole die Daten vom Backend
        axios.get("http://localhost:8080/api/spieler")
            .then(response => setSpieler(response.data)) // Speichern der Spieler in state
            .catch(error => {
                console.error("Fehler beim Laden der Spieler:", error);
                alert("Fehler beim Laden der Spieler-Daten.");
            });
    }, []);

    const handleAuswahl = (event) => {
        const spielerId = event.target.value;
        const gewaehlterSpieler = spieler.find(s => s.id === spielerId);
        setAusgewaehlterSpieler(gewaehlterSpieler); // Setze den ausgewählten Spieler
    };

    return (
        <div className="p-4">
            <h1 className="text-xl font-bold mb-4">Spieler Auswahl</h1>
            {/* Dropdown-Menü für Spieler-Auswahl */}
            <select onChange={handleAuswahl} className="border p-2 rounded">
                <option value="">Spieler wählen...</option>
                {spieler.map(s => (
                    <option key={s.id} value={s.id}>{s.vorname} {s.nachname}</option>
                ))}
            </select>

            {/* Anzeige der Details des ausgewählten Spielers */}
            {ausgewaehlterSpieler && (
                <div className="mt-4 border p-4 rounded">
                    <h2 className="text-lg font-semibold">{ausgewaehlterSpieler.vorname} {ausgewaehlterSpieler.nachname}</h2>
                    <p>Position: {ausgewaehlterSpieler.position}</p>
                    <p>Geburtsdatum: {ausgewaehlterSpieler.geburtsdatum}</p>
                    <p>Nationalität: {ausgewaehlterSpieler.nationalitaet}</p>
                    <p>Nummer: {ausgewaehlterSpieler.nummer}</p>
                    <p>Marktwert: {ausgewaehlterSpieler.marktwert} €</p>
                </div>
            )}
        </div>
    );
}
