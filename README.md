# Using Swagger in Liberty [![Build Status](https://travis-ci.org/WASdev/sample.swagger.svg?branch=master)](https://travis-ci.org/WASdev/sample.swagger)

This sample contains a few ways of how to use Swagger documentation within Liberty. This application contains
two individual servlets:  the JAX-RS servlet is used to handle requests to a Tasks API, which allows user to create, view and delete Task objects, while the second servlet is an extension of a HTTPServlet and is used to resond to requests for a Contacts API.
Contacts API lets you create, view and update Contact objects.

[TasksResource](/swagger-sample/src/main/java/net/wasdev/swaggersample/jaxrs/TasksResource.java) is a
JAX-RS resouce classs that uses both JAX-RS and Swagger annotations to document the API.

Documentation for HTTPServlet based API [ContactsServlet](/swagger-sample/src/main/java/net/wasdev/swaggersample/servlet/ContactsServlet.java)
is provided using Swagger stub file [swagger.json](/swagger-sample/src/main/webapp/META-INF/stub/swagger.json)

## Getting Started

Browse the code to see what it does, or build and run it yourself:
* [Building and running on the command line](/docs/Using-cmd-line.md)
* [Building and running using Eclipse and WebSphere Development Tools (WDT)](/docs/Using-WDT.md)

Once the server has been started, go to [REST API Discovery](https://developer.ibm.com/wasdev/blog/2016/02/17/exposing-liberty-rest-apis-swagger/) Explorer at [https://localhost:9444/ibm/api/explorer/](https://localhost:9444/ibm/api/explorer/)
to interact with the sample. Default credentials are login: user, password: demo

## Notice

© Copyright IBM Corporation 2016, 2017.

## License

```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````
