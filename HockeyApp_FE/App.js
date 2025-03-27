import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import IndexPage from "./pages/index";
import TeamPage from "./pages/team";
import Navbar from "./components/Navbar";  // Importiere die Navbar
import "./css/styles.css";

function App() {
    return (
        <Router>
            <Navbar /> {/* Hier wird die Navbar gerendert */}
            <Routes>
                <Route path="/" element={<IndexPage />} />
                <Route path="/team" element={<TeamPage />} />
            </Routes>
        </Router>
    );
}

export default App;
