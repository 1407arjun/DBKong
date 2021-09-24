## DB Kong - Database connections simplified
[![Android CI](https://github.com/1407arjun/DBKong/actions/workflows/android.yml/badge.svg)](https://github.com/1407arjun/DBKong/actions/workflows/android.yml)
[![Gradle Package](https://github.com/1407arjun/DBKong/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/1407arjun/DBKong/actions/workflows/gradle-publish.yml)

DB Kong is an Android library that allows you to connect, interact and test any type of cloud database on Android apps without the need to setup a dedicated backend. 
The library simplifies the process of connecting to a database and performing operations on it to just a few lines of code. 

As of the current release DB Kong supports the following databases (future releases are aimed at including many more):
- `MongoDB`

I really can't wait for y'all to try it out, so let's dive into the setup and features!

- [Getting started](#getting-started)
- [Using the library](#using-the-library)
- [Contributing](#contributing)
- [Acknowledgements](#acknowledgements)

### Getting started
1. Firstly add the dependencies to your `app/build.gradle` (module level gradle file):
```
...

dependencies {

    ...

    implementation 'com.1407arjun.libs:dbkong:1.1.0'

    //Volley dependencies (required by the library as of the current version)
    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.android.volley:volley:1.2.0'
    
    ...
}
```
2. Create a `github.properties` file in the root of your project and add in the code as follows (skip this step if you want to use system environment variables as in step 2):
``` 
gpr.usr = < your github username >
gpr.key = < your personal access token >
```
**The personal access token should have read packages scope.**

3. In the `app/build.gradle` (module level gradle file) include the following code to access the credentials from the `github.properties` file:
```
...

android {

  ...
  
  repositories {
        maven {
            url = "https://maven.pkg.github.com/1407arjun/DBKong"
            credentials {
                def githubProperties = new Properties()
                githubProperties.load(new FileInputStream(rootProject.file("github.properties")))

                username = githubProperties['gpr.usr'] ?: System.getenv("GPR_USER")
                password = githubProperties['gpr.key'] ?: System.getenv("GPR_API_KEY")
            }
        }
    }
    
    ...
}
```

Steps 2 and 3 are required by github to access any package published on **Github Packages**.

4. Finally the repositories to your project level `build.gradle` file (depends on the Gradle version, **do not add these to the** `buildscript`):
```
...

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

...
```
5. Sync the project with the build files.

6. Add the permission to access the internet and allow clear-text traffic in the `AndroidManifest.xml`:
```XML
...

  <uses-permission android:name="android.permission.INTERNET"/>

  ...

  <application
          android:usesCleartextTraffic="true"

          ...

  </application>   

...
```

### Using the library
1. Firstly initialize the library in the `onCreate()` of your `MainActivity.java` or any other Java file:
```Java
        new DBKong(getApplicationContext(), timeout, new OnInitListener() {
            @Override
            public void onInit(boolean init, Error error) {
                if (init && error == null) {
                
                    ...
    
                } else {
                    if (error != null)
                    
                        ...
                }
            }
        });
```
Here, `timeout` is the time in **milliseconds** within which the connection should be established. You can set any timeout value depending on your use-case. On initialization, this
method invokes a callback giving a `boolean` for the initialization status and an `Error` object for any error encountered (if any).

2. If there is no error and the initialization status `init == true` then the intermediate server connection has been established and now all the connections to the database can be 
executed. Here, an example is shown for the MongoDB database:
```Java
  ...
  
        new DBKong(getApplicationContext(), timeout, new OnInitListener() {
            @Override
            public void onInit(boolean init, Error error) {
                if (init && error == null) {
                    String uri = "mongodb+srv://admin:<password>@<cluster_name>.1oqwt.mongodb.net/<database_name>?retryWrites=true&w=majority";
                    MongoDBConnect.getInstance(uri, db, collection)
                            .insertOne(query)
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(JSONObject response) {
                                    
                                    ...
                                }

                                @Override
                                public void onFailure(Error error) {
                                    
                                    ...
                                }
                            });
                }
                
                ...
            }
            
        });
        
  ...         
```
Here, `uri` is the connection string of MongoDB, `db` and `collection` is the name of the database (string) and collection (string) you want to connect to. This instance has several 
methods which you can use to perform CRUD or interact with the collection (for eg. `insertOne()`, `find()`, `deleteMany()`, etc.). Also you can add an `OnSuccessListener` callback to know if the operation
is a success or whether it encountered an error, since all these tasks are carried out asynchronously.

### Contributing
Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are greatly appreciated.
1. Fork the project. `git clone https://github.com/1407arjun/DBKong.git`
2. Create your feature branch. `git checkout -b feature/AmazingFeature`
3. Commit your changes. `git commit -m 'Add some AmazingFeature'`
4. Push to the branch. `git push origin feature/AmazingFeature`
5. Open a **Pull Request**.

### Acknowledgements
1. https://github.com/JaneaSystems/nodejs-mobile
2. https://github.com/google/volley
