## Building and running the sample using the command line

### Clone Git Repo
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#clone-git-repo)

```bash

$ git clone https://github.com/WASdev/sample.swagger.git

```

### Building the sample with Maven
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
                    +- apps                                <-- directory for applications
                        +- swagger-sample.war.xml          <-- loose sample application
                    +- logs                                <-- created by running the server locally
                    +- workarea                            <-- created by running the server locally
```

### Running the application locally with Maven
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

### Building the sample with Gradle
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#building-with-gradle)

This project can also be built and run with [Gradle]. The provided `build.gradle` file applies the [Liberty Gradle Plug-in] and is configured to automatically download and install the Liberty Java EE Web Profile 7 runtime from Maven Central. The Liberty Gradle Plug-in has built-in tasks that can be used to create, configure, and run the application on the Liberty server.

Use the following steps to build the application with Gradle:

1. Execute the full Gradle build. The Liberty Gradle Plug-in will download and install the Liberty server.
    ```bash
    $ ./gradlew clean build
    ```
```text
build
+- wlp
    +- usr
        +- servers
            +- swaggerSample
                +- server.xml                          <-- server configuration
                +- apps                                <-- directory for applications
                    +- swagger-sample.war.xml          <-- loose sample application
                +- logs                                <-- created by running the server locally
                +- workarea                            <-- created by running the server locally
```
### Running the application locally with Gradle

2. To start the server with the Servlet sample execute:
    ```bash
    $ ./gradlew libertyStart
    ```

    Alternatively, execute the run command:
    ```bash
    $ ./gradlew libertyRun --no-daemon
    ```
3. To stop the server, execute:
    ```bash
    $ ./gradlew libertyStop
    ```  

[Liberty Gradle Plug-in]: https://github.com/WASdev/ci.gradle
[Gradle]: https://gradle.org
