#!/bin/bash
set -e

# Wait for the primary to be ready
sleep 30

# Connect to the primary and initiate the replica set
mongosh -h mongo_primary -u root -p example --authenticationDatabase admin <<EOF
rs.initiate({
    _id: "rs0",
    members: [
        { _id: 0, host: "mongo_primary:27017" },
        { _id: 1, host: "mongo_secondary1:27017" },
        { _id: 2, host: "mongo_secondary2:27017" }
    ]
})
EOF