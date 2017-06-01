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

```bash
$ mvn install
```

If you want to also run the functional tests then you need to [Download WAS Liberty](/docs/Downloading-WAS-Liberty.md) and pass in the location of your install as the system property libertyRoot:

```bash
$ mvn -DlibertyRoot=<LibertyInstallLocation> install
```

In addition to publishing the war to the local maven repository, the built war file is copied into the apps directory of the server configuration located in the swagger-sample-wlpcfg directory:

```text
swagger-sample-wlpcfg
 +- servers
     +- swaggerSample                            <-- specific server configuration
        +- server.xml                          <-- server configuration
        +- apps                                <- directory for applications
           +- swagger-sample-application.war      <- sample application
        +- logs                                <- created by running the server locally
        +- workarea                            <- created by running the server locally
```

### Running the application locally
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#running-the-application-locally)

Pre-requisite: [Download WAS Liberty](/docs/Downloading-WAS-Liberty.md)

Use the following to start the server and run the application:

background:

```bash
$ mvn liberty:start-server
```

foreground:

```bash
$ mvn liberty:run-server
```

