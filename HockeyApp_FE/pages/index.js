import React, { useState, useEffect } from "react";
import axios from "axios";

export default function IndexPage() {
    const [spieler, setSpieler] = useState([]);
    const [ausgewaehlterSpieler, setAusgewaehlterSpieler] = useState(null);

    useEffect(() => {
        // Hole die Daten vom Backend
        axios.get("http://localhost:8080/api/spieler")
            .then(response => setSpieler(response.data)) // Speichern der Spieler im state
            .catch(error => {
                console.error("Fehler beim Laden der Spieler:", error);
                alert("Fehler beim Laden der Spieler-Daten.");
            });
    }, []); // Leeres Array bedeutet, dass dieser Effekt nur einmal beim Laden ausgeführt wird

    const handleAuswahl = (event) => {
        const spielerId = event.target.value;
        const gewaehlterSpieler = spieler.find(s => s.id === spielerId);
        setAusgewaehlterSpieler(gewaehlterSpieler); // Setze den ausgewählten Spieler
    };

    return (
        <div className="container">
            <h1 className="title">Spieler Auswahl</h1>
            {/* Dropdown-Menü für Spieler-Auswahl */}
            <select onChange={handleAuswahl} className="select-box">
                <option value="">Spieler wählen...</option>
                {spieler.map(s => (
                    <option key={s.id} value={s.id}>{s.vorname} {s.nachname}</option>
                ))}
            </select>

            {/* Anzeige der Details des ausgewählten Spielers */}
            {ausgewaehlterSpieler && (
                <div className="player-details">
                    <h2>{ausgewaehlterSpieler.vorname} {ausgewaehlterSpieler.nachname}</h2>
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
