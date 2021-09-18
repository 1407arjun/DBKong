const { MongoClient } = require('mongodb');


async function input(uri, cmd = -1, data = {}) {
    const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true });

    client.connect(err => {
        const collection = client.db(dbName).collection(collectionName);

        const result;
        switch(cmd) {
            //Insert
            case 0:
                result = await collection.insertOne();
                break;
            case 1:
                result = await collection.insertMany();
                break;
            //Update    
            case 2:
                result = await collection.updateOne();
                break;
            case 3:
                result = await collection.updateMany();
                break;
            case 4:
                result = await collection.replaceOne();
                break;
            //Read    
            case 5:
                result = await collection.find().project();
                break;
            //Delete    
            case 6:
                result = await collection.deleteOne();
                break;
            case 7:
                result = await collection.deleteMany();
                break;
            //Default    
            case 8:
                result = await collection.count();
                break;   
            default:
                result = "Command not found"     
        }
        client.close();
      });
}

module.exports = input;