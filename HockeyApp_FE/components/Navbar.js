import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav className="navbar">
            <ul>
                <li>
                    <Link to="/" className="nav-link">Startseite</Link>
                </li>
                <li>
                    <Link to="/team" className="nav-link">Teams</Link>
                </li>
            </ul>
        </nav>
    );
}
