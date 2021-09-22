const express = require('express');
const bodyParser = require('body-parser')
const { MongoClient } = require('mongodb');

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.post("/", (req, res) => {
    data = req.body;

    const client = new MongoClient(data.uri, { useNewUrlParser: true, useUnifiedTopology: true });

    var result;
    client.connect(async err => {
        if (err) {
            result = {error : true, response : err};
        } else {
            const collection = await client.db(data.db).collection(data.collection);

            switch(data.cmd) {
                //Insert
                case 0:
                    result = await collection.insertOne(data.filter);
                    result = {error : false, response : result};
                    break;
                case 1:
                    result = await collection.insertMany(data.filter);
                    result = {error : false, response : result};
                    break;
                //Update
                case 2:
                    result = await collection.updateOne(data.filter);
                    result = {error : false, response : result};
                    break;
                case 3:
                    result = await collection.updateMany(data.filter);
                    result = {error : false, response : result};
                    break;
                case 4:
                    result = await collection.replaceOne(data.filter);
                    result = {error : false, response : result};
                    break;
                //Read
                case 5:
                    result = await collection.find(data.filter).toArray(); //.project() limit
                    result = {error : false, count : result.length, response : result};
                    break;
                //Delete
                case 6:
                    result = await collection.deleteOne(data.filter);
                    result = {error : false, response : result};
                    break;
                case 7:
                    result = await collection.deleteMany(data.filter);
                    result = {error : false, response : result};
                    break;
                //Default
                case 8:
                    result = await collection.countDocuments(data.filter);
                    result = {error : true, response : result};
                    break;
                default:
                    result = {response : "Command not found"}
            }
        }
        client.close();
        res.json(result);
    });
});

app.get("/", (req, res) => {
    res.json({init: true});
});

app.listen(3000, () => {
    console.log("Server started at port 3000");
});