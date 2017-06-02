## Building and running the sample using the command line

### Clone Git Repo
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#clone-git-repo)

```bash

$ git clone https://github.com/WASdev/sample.swagger.git

```

### Building the sample
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#building-the-sample-in-eclipse)

This sample can be built using [Maven](#apache-maven-commands).

###### [Apache Maven](http://maven.apache.org/) commands

Running the following command will build the application, download the WAS Liberty application server, and execute integration tests:

```bash
$ mvn install
```

You can skip tests with the following command:

```bash
$ mvn install -DskipTests=true
```

In addition to publishing the war to the local maven repository, the built war file is copied into the apps directory of the server configuration located in the swagger-sample-wlpcfg directory:

```text
target
 +- servers
    +- liberty
        +- wlp
            +- usr
                +- server
                    +- swaggerSample                            <-- specific server configuration
                    +- server.xml                          <-- server configuration
                    +- apps                                <- directory for applications
                        +- swagger-sample-application.war      <- sample application
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

