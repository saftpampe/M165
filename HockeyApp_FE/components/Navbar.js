import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav className="p-4 bg-blue-600 text-white">
            <ul className="flex space-x-4">
                <li>
                    <Link to="/" className="hover:underline">Startseite</Link>
                </li>
                <li>
                    <Link to="/team" className="hover:underline">Teams</Link>
                </li>
            </ul>
        </nav>
    );
}
