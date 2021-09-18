const express = require('express');
const mongoClient = require('./db_modules/mongodb.js')

const app = express();
app.use(express.json());

app.get("/", (req, res) => {
    res.send("Hello there");
});

app.post("/", (req, res) => {
    
});

app.listen(3000, () => {
    console.log("Server started at port 3000");
});