const { MongoClient } = require('mongodb');


async function input(uri, db, collection, cmd, filter) {
    const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true });

    var result;
    client.connect(async err => {
        const collections = client.db(db).collection(collection);

        switch(cmd) {
            //Insert
            case 0:
                result = await collections.insertOne();
                break;
            case 1:
                result = await collections.insertMany();
                break;
            //Update
            case 2:
                result = await collections.updateOne();
                break;
            case 3:
                result = await collections.updateMany();
                break;
            case 4:
                result = await collections.replaceOne();
                break;
            //Read
            case 5:
                result = await collections.find(filter).toArray(); //.project()
                break;
            //Delete
            case 6:
                result = await collections.deleteOne();
                break;
            case 7:
                result = await collections.deleteMany();
                break;
            //Default
            case 8:
                result = await collections.count();
                break;
            default:
                result = "Command not found"
        }
        client.close();
      });
    return result;
}

module.exports = input;