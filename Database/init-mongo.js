db = db.getSiblingDB('HockeyDB');  // Erstellt oder wechselt zur 'HockeyDB'

// Collections erstellen
db.createCollection("team");
db.createCollection("spieler");
db.createCollection("spiel");
db.createCollection("arena");

// Beispiel-Daten einfügen
db.arena.insertOne({
  "name": "Raiffeisen Arena",
  "kapazitaet": 5178,
  "ort": "Pruntrut",
  "baujahr": 1973
});

var arenaId = db.arena.findOne({"name": "Raiffeisen Arena"})._id;

var teamId = db.team.insertOne({
  "name": "HC Ajoie",
  "gruendungsjahr": 1973,
  "erfolge": ["Aufstieg in National League"],
  "punkte": 40,
  "arena_id": arenaId
}).insertedId;

db.spieler.insertOne({
  "vorname": "Benjamin",
  "nachname": "Conz",
  "position": "Torhüter",
  "geburtsdatum": "1991-09-13",
  "Nationalität": "Schweiz",
  "unterVertragBis": "2025-04-25",
  "saves": 100,
  "save%": 92,
  "nummer": 1,
  "marktwert": 60000,
  "team_id": teamId
});

db.spiel.insertOne({
  "datum": "2024-05-10",
  "austragungsort": arenaId,
  "team1_id": teamId,
  "team2_id": teamId,
  "toreTeam1": 2,
  "toreTeam2": 1
});
