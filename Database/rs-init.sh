#!/bin/bash
echo "Warte auf MongoDB, um verfügbar zu sein..."
sleep 10  # Wartezeit, um sicherzustellen, dass MongoDB gestartet ist

echo "Initialisiere Replikationsset..."
mongosh --host mongo_primary:27017 <<EOF
rs.initiate({
  _id: "rs0",
  members: [
    { _id: 0, host: "mongo_primary:27017" },
    { _id: 1, host: "mongo_secondary1:27017" },
    { _id: 2, host: "mongo_secondary2:27017" }
  ]
});
EOF

echo "Replikationsset konfiguriert."
