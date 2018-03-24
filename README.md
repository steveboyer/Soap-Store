# Soap-Site

## Running Locally With Spring Boot
```sh
$ git clone https://github.com/steveboyer/Soap-Store
$ cd Soap-Store
$ mvn install
$ java -jar target/soapstore-1.0.jar
```

## Running Locally With Heroku CLI

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/steveboyer/Soap-Store
$ cd Soap-Store
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```