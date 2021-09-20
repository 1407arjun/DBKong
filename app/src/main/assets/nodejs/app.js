const express = require('express');
const bodyParser = require('body-parser')
//const mongoClient = require('./db_modules/mongodb.js')
const { MongoClient } = require('mongodb');
const os = require('os');

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.post("/", (req, res) => {
    console.log(req.body);
    data = req.body;

    const client = new MongoClient(data.uri, { useNewUrlParser: true, useUnifiedTopology: true });

    var result;
    client.connect(async err => {

        if (err) {
            result = {error : error};
        } else {
            const collection = client.db(data.db).collection(data.collection);

            switch(data.cmd) {
                //Insert
                case 0:
                    result = await collection.insertOne(data.filter);
                    break;
                case 1:
                    result = await collection.insertMany(data.filter);
                    break;
                //Update
                case 2:
                    result = await collection.updateOne(data.filter);
                    break;
                case 3:
                    result = await collection.updateMany(data.filter);
                    break;
                case 4:
                    result = await collection.replaceOne(data.filter);
                    break;
                //Read
                case 5:
                    result = await collection.find(data.filter).toArray(); //.project()
                    break;
                //Delete
                case 6:
                    result = await collection.deleteOne(data.filter);
                    break;
                case 7:
                    result = await collection.deleteMany(data.filter);
                    break;
                //Default
                case 8:
                    result = await collection.countDocuments(data.filter);
                    break;
                default:
                    result = {error : "Command not found"}
            }
        }
        client.close();
        res.send(req.body);
    });
});

app.get("/", async (req, res) => {
    /*const result = await mongoClient("mongodb+srv://admin:Arjun1407@cluster0.1oqwt.mongodb.net/FFCS?retryWrites=true&w=majority",
    "FFCS", "CourseData", 5, {courseCode: "CSE3013"});*/
    res.send(os.networkInterfaces());
});

app.listen(3000, () => {
    console.log("Server started at port 3000");
});