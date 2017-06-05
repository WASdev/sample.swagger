## Building and running the sample using the command line

### Clone Git Repo
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#clone-git-repo)

```bash

$ git clone https://github.com/WASdev/sample.swagger.git

```

### Building the sample
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#building-the-sample-in-eclipse)

This sample can be built using Maven.

###### [Apache Maven](http://maven.apache.org/) commands

Running the following command will build the application, download the WAS Liberty application server, and execute integration tests:

```bash
$ mvn install
```

You can skip tests with the following command:

```bash
$ mvn install -DskipTests=true
```

In addition to publishing the WAR to the local maven repository, the built WAR file is copied into the apps directory of the server configuration located in the swagger-sample directory:

```text
target
 +- swagger-sample-1.0.zip                                 <-- packaged server file containing the server, application, and configuration
 +- liberty
    +- wlp
        +- usr
            +- server
                +- swaggerSample
                    +- server.xml                          <-- server configuration
                    +- apps                                <- directory for applications
                        +- swagger-sample.war.xml          <- loose sample application
                    +- logs                                <- created by running the server locally
                    +- workarea                            <- created by running the server locally
```

### Running the application locally
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#running-the-application-locally)

Use the following to start the server and run the application:

background:

```bash
$ mvn liberty:start-server
```

foreground:

```bash
$ mvn liberty:run-server
```

