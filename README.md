# Building Project
Before proceed please make sure Java SDK 8, Maven, node, npm and Angular CLI are installed in your machine.
Go to src/main/webapp/emailweb directory:
```sh
$ cd src/main/webapp/emailweb
```
Install the front end libraries with npm:
```sh
$ npm install
```
Build the JS package for front end:
```sh
$ ng build
```
Go back to project root where pom.xml is located and start the application:
```sh
$ mvn spring-boot:run
```
After Spring boot started up, open a browser and go to this URL - http://localhost:8080/emailweb/dist/index.html
From here you should see the email client form.
