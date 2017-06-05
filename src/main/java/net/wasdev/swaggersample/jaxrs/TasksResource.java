/**
* (C) Copyright IBM Corporation 2016.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package net.wasdev.swaggersample.jaxrs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.wasdev.swaggersample.jaxrs.model.Task;
import net.wasdev.swaggersample.jaxrs.model.TaskWithID;

@Path("/tasks")
@Api("Tasks API")

public class TasksResource {
	private Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();
	private volatile int currentId = 0;

	@GET
	@ApiOperation(value = "Retrieve all tasks", responseContainer = "array", response = TaskWithID.class)
	@Produces("application/json")
	public Response getTasks() {
		List<TaskWithID> result = new ArrayList<TaskWithID>(tasks.size());
		for (Entry<Integer, Task> taskEntry : tasks.entrySet()) {
			result.add(new TaskWithID(taskEntry.getValue(), taskEntry.getKey()));
		}
		return Response.ok().entity(result).build();
	}

	@POST
	@ApiOperation("Create a task")
	@Consumes("application/json")
	@Produces("application/json")
	@ApiResponses({ @ApiResponse(code = 201, message = "Task created", response = String.class) })
	public Response createTask(@ApiParam(required = true) Task task) {
		tasks.put(currentId, task);
		return Response.status(Status.CREATED).entity("{\"id\":" + currentId++ + "}").build();
	}

	@GET
	@Path("{id}")
	@ApiOperation(value = "Get a task with ID")
	@Produces("application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Task retrieved", response = Task.class),
			@ApiResponse(code = 404, message = "Task not found") })
	public Response getTask(@PathParam("id") int id) {
		Task task = tasks.get(id);
		if (task != null) {
			return Response.ok().entity(task).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@ApiOperation(value = "Update a task with ID")
	@Consumes("application/json")
	@Produces("text/plain")
	@ApiResponses({ @ApiResponse(code = 200, message = "Task updated"),
			@ApiResponse(code = 404, message = "Task not found") })
	public Response updateTask(@PathParam("id") int id, @ApiParam(required = true) Task task) {
		if (tasks.get(id) != null) {
			tasks.put(id, task);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Delete a task with ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Task deleted"),
			@ApiResponse(code = 404, message = "Task not found") })
	@Produces("text/plain")
	public Response deleteTask(@PathParam("id") int id) {
		if (tasks.get(id) != null) {
			tasks.remove(id);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
