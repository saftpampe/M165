# Konzept für horizontale Skalierung

cd weg/zum/Repository/Database
docker-compose up -d

## Konfigurierung Replica Set
docker exec -it mongo-primary mongo --eval "rs.initiate({_id: 'rs0', members: [{_id: 0, host: 'mongo-primary:27017'}, {_id: 1, host: 'mongo-secondary1:27017'}, {_id: 2, host: 'mongo-secondary2:27017'}]})"

## Primary Node

### Enter mongosh from primary node
docker exec -it mongo-primary mongosh

### Create Database or select
use HockeyDB

### Create Collection
db.createCollection("arena")

### Arena insert
db.arena.insertOne({
"name": "Raiffeisen Arena",
"kapazitaet": 5178,
"ort": "Pruntrut",
"baujahr": 1973
})


## Secondary Node 1

### Enter mongosh from secondary node 1
docker exec -it mongo-secondary1 mongo

### Create Database or select
use HockeyDB

### Collections anzeigen
show collections

### Select Arena
db.arena.find()


## Secondary Node 2

### Enter mongosh from secondary node 2
docker exec -it mongo-secondary2 mongo

### Create Database or select
use HockeyDB

### Collections anzeigen
show collections

### Select Arena
db.arena.find()