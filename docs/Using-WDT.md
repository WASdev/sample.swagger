## Eclipse / WDT

The WebSphere Development Tools (WDT) for Eclipse can be used to control the server (start/stop/dump/etc.), it also supports incremental publishing with minimal restarts, working with a debugger to step through your applications, etc.

WDT also provides:

* content-assist for server configuration (a nice to have: server configuration is minimal, but the tools can help you find what you need and identify finger-checks, etc.)
* automatic incremental publish of applications so that you can write and test your changes locally without having to go through a build/publish cycle or restart the server (which is not that big a deal given the server restarts lickety-split, but less is more!).
*  improved Maven integration for web projects starting with WDT 17.0.0.2 including support for loose applications.

Installing WDT on Eclipse is as simple as a drag-and-drop, but the process is explained [on wasdev.net] [wasdev-wdt].

[wasdev-wdt]: https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/

### Clone Git Repo
:pushpin: [Switch to cmd line example](/docs/Using-cmd-line.md/#clone-git-repo)

If the sample git repository hasn't been cloned yet, WDT has git tools integrated into the IDE:

1.  Open the Git repositories view
    * *Window -> Show View -> Other*
    * Type "git" in the filter box, and select *Git Repositories*
2.  Copy Git repo url by finding the textbox under "HTTPS clone URL" at the top of this page, and select *Copy to clipboard*
3.  In the Git repositories view, select the hyperlink `Clone a Git repository`
4.  The git repo url should already be filled in.  Select *Next -> Next -> Finish*
5.  The "sample.swagger [master]" repo should appear in the view

### Building the sample in Eclipse
:pushpin: [Switch to cmd line example](/docs/Using-cmd-line.md/#building-the-sample)

This sample can be built using Maven.

#### Building with [Maven](http://maven.apache.org/)

###### Import Maven projects into WDT

These instructions are written for WDT 17.0.0.2 or later that has improved Maven project integration.

1.  In the Git Repository view, expand the sample repo to see the "Working Tree" folder
2.  Right-click on this folder, and select *Copy path to Clipboard*
3.  Select menu *File -> Import -> Maven -> Existing Maven Projects*
4.  In the Root Directory textbox, Paste in the repository directory.
5.  Select *Browse...* button and select *Finish* (confirm it finds 1 pom.xml files)
6.  Click *Yes* to the WebSphere Liberty dialog to automatically create server in the Servers view for this project.
7. This will create 1 project in Eclipse: swagger-sample

:star: *Note:* If you did not use Eclipse/WDT to clone the git repository, follow from step 3, but navigate to the cloned repository directory rather than pasting its name in step 4.

###### Run Maven install

1. Right-click on swagger-sample/pom.xml
2. *Run As > Maven build...*
3. In the *Goals* section enter "install"
4. Click *Run*

#### Running the application locally in Maven
:pushpin: [Switch to cmd line example](/docs/Using-cmd-line.md/#running-the-application-locally)

###### Running Liberty and the sample application from WDT

1.  Select the `swagger-sample` project
2.  Right-click -> *Run As... -> Run On Server*
3.  Select the appropriate server (as created above) and select *Finish*

#### Building with [Gradle](https://gradle.org/)

###### Download the Eclipse Buildship Grab to support Gradle
1. Select menu *Help -> Eclipse Marketplace...*
2. Search for "Buildship Integration 2.0" and install


###### Import Gradle projects into WDT

1. In the Git Repository view, expand the sample repo to see the "Working Tree" folder.
2. Right-click on this folder, and select *Copy path to Clipboard*
3. Select menu *File -> Import -> Gradle -> Existing Gradle Projects*
4. In the Root Directory textbox, Paste in the repository directory.
5. To apply a specific Gradle distribution, click *Next* and configure. Click *Finish* when ready to import the project. By default, Eclipse will use the included Gradle wrapper.

:star: *Note:* If you did not use Eclipse/WDT to clone the git repository, follow from step 3, but navigate to the cloned repository directory rather than pasting its name in step 4. 

###### Running Gradle tasks in Eclipse

:pushpin: [Switch to cmd line example](/docs/Using-cmd-line.md/#building-the-sample-with-gradle)

1. Everything can be done in the Gradle Tasks view. If you have this view, skip ahead to step 4.
2. Select menu *Window -> Show View -> Other...*
3. Navigate to *Gradle Tasks* in the Gradle folder, or type it into the text filter.
4. Click on *sample.swagger* to expand its grouped tasks.
5. To run a `gradle build`, click on the `build` task located inside the build group.
6. To start a server with a `gradle libertyStart`, click on the `libertyStart` task inside the liberty group. 

#### Tips

* When importing the existing maven project into Eclipse, Eclipse will (by default) "helpfully" add this project to an (extraneous) ear. To turn this off, go to Preferences -> Java EE -> Project, and uncheck "Add project to an EAR" before you import the project. If you forgot to do this, just delete the ear project; no harm.

