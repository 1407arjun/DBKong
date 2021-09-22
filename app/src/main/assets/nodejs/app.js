const express = require('express');
const bodyParser = require('body-parser')
const { MongoClient } = require('mongodb');

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.post("/", (req, res) => {
    data = req.body;

    var client, result;
    try {
        client = new MongoClient(data.uri, { useNewUrlParser: true, useUnifiedTopology: true });

        client.connect(async err => {
            if (err) {
                result = {error : true, response : err};
            } else {
                const collection = await client.db(data.db).collection(data.collection);

                try {
                    switch(data.cmd) {
                        //Insert
                        case 0:
                            result = await collection.insertOne(data.query);
                            result = {error : false, response : result};
                            break;
                        case 1:
                            result = await collection.insertMany(data.query);
                            result = {error : false, response : result};
                            break;
                        //Update
                        case 2:
                            result = await collection.updateOne(data.filter, data.updateDocument, data.options);
                            result = {error : false, response : result};
                            break;
                        case 3:
                            result = await collection.updateMany(data.filter, data.updateDocument, data.options);
                            result = {error : false, response : result};
                            break;
                        case 4:
                            result = await collection.replaceOne(data.filter, data.replacementDocument, data.options);
                            result = {error : false, response : result};
                            break;
                        //Read sort, skip, limit, projection
                        case 5:
                            result = await collection.findOne(data.query, data.options);
                            result = {error : false, count : result.length, response : result};
                            break;
                        case 6:
                            result = await collection.find(data.query, data.options).toArray();
                            result = {error : false, count : result.length, response : result};
                            break;     
                        //Delete
                        case 7:
                            result = await collection.deleteOne(data.query);
                            result = {error : false, response : result};
                            break;
                        case 8:
                            result = await collection.deleteMany(data.query);
                            result = {error : false, response : result};
                            break;
                        //Default
                        case 9:
                            result = await collection.countDocuments(data.filter, data.options);
                            result = {error : false, response : result};
                            break;
                        //Aggregate    
                        case 10:
                            result = await collection.aggregate(data.pipeline, data.options).toArray();
                            result = {error : false, response : result};
                            break;  
                        //Compound
                        case 11:
                            result = await collection.findOneAndDelete(data.query, data.options);
                            result = {error : false, response : result};
                            break;
                        case 12:
                            result = await collection.findOneAndUpdate(data.filter, data.updateDocument, data.options);
                            result = {error : false, response : result};
                            break; 
                        case 13:
                            result = await collection.findOneAndReplace(data.filter, data.replacementDocument, data.options);
                            result = {error : false, response : result};
                            break;   
                        //Bulk Write JSONArray (operations)    
                        case 14:
                            result = await collection.bulkWrite(data.operations, data.options);
                            result = {error : false, response : result};
                            break;                
                        default:
                            result = {error : true, response : "Command not found"}
                    }
                } catch (e) {
                    result = await {error : true, response : e}; 
                }
            }
        });
    } catch (e) {
        result = {error : true, response : {name: e.name, message: e.message}};    
    } finally {
        if (client !== undefined)
            client.close();
        res.json(result);
    }
});

app.get("/", (req, res) => {
    res.json({init: true});
});

app.listen(3000, () => {
    console.log("Server started at port 3000");
});