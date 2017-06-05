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

package net.wasdev.swaggersample.servlet;

import java.io.IOException;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(value = "/demo/contacts/*", name = "Contacts Servlet")
public class ContactsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Contact> contacts = new ConcurrentHashMap<String, Contact>();
	private static int currentID;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		if (uri.equals(req.getServletPath())) {
			getContacts(req, resp);
			return;
		}

		Pattern p = Pattern.compile(req.getServletPath() + "/.*");
		if (p.matcher(uri).matches()) {
			String id = uri.substring(uri.lastIndexOf('/') + 1);
			getContact(id, resp);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		if (uri.equals(req.getServletPath())) {
			createContact(req, resp);
			return;
		}
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		if (uri.equals(req.getServletPath())) {
			super.doPut(req, resp);
			;
		}

		Pattern p = Pattern.compile(req.getServletPath() + "/.*");
		if (p.matcher(uri).matches()) {
			String id = uri.substring(uri.lastIndexOf('/') + 1);
			updateContact(req, id, resp);
			return;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		if (uri.equals(req.getServletPath())) {
			super.doDelete(req, resp);
		}

		Pattern p = Pattern.compile(req.getServletPath() + "/.*");
		if (p.matcher(uri).matches()) {
			String id = uri.substring(uri.lastIndexOf('/') + 1);
			deleteContact(id, resp);
			return;
		}
	}

	private void getContacts(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");
		try {
			JsonArrayBuilder jarray = Json.createArrayBuilder();
			for (Entry<String, Contact> contactEntry : contacts.entrySet()) {
				JsonObjectBuilder jobject = contactEntry.getValue().getJsonObjectBuilder();
				jobject.add("id", Integer.parseInt(contactEntry.getKey()));
				jarray.add(jobject);

			}
			StringWriter sw = new StringWriter();
			Json.createWriter(sw).writeArray(jarray.build());
			String json = sw.toString();
			resp.setContentLength(json.length());
			resp.getWriter().write(json);
		} catch (IOException e) {
		}

	}

	private void getContact(String id, HttpServletResponse resp) {
		Contact contact = contacts.get(id);
		if (contact != null) {
			resp.setStatus(200);
			resp.setContentType("application/json");
			JsonObject jcontact = contact.toJsonObject();
			if (jcontact != null) {
				try {
					StringWriter sw = new StringWriter();
					Json.createWriter(sw).writeObject(jcontact);
					String json = sw.toString();
					resp.setContentLength(json.length());
					resp.getWriter().write(json);
				} catch (IOException e) {
				}
			}
		} else {
			resp.setStatus(404);
			resp.setContentLength(0);
			resp.setContentType("text/plain");
		}
	}

	private void createContact(HttpServletRequest req, HttpServletResponse resp) {
		try {
			JsonObject jcontact = Json.createReader(req.getReader()).readObject();
			Contact contact = new Contact(jcontact);
			resp.setContentType("application/json");
			contacts.put(String.valueOf(currentID), contact);
			resp.setStatus(201);
			resp.getWriter().write(String.valueOf("{\"id\":" + currentID + "}"));
		} catch (IOException e) {
		} finally {
			currentID++;
		}
	}

	private void updateContact(HttpServletRequest req, String id, HttpServletResponse resp) {
		try {
			resp.setContentType("text/plain");
			if (contacts.get(id) == null) {
				resp.setStatus(404);
				return;
			}
			JsonObject jcontact = Json.createReader(req.getReader()).readObject();
			Contact contact = new Contact(jcontact);
			contacts.put(id, contact);
			resp.setStatus(200);
		} catch (IOException e) {
		}
	}

	private void deleteContact(String id, HttpServletResponse resp) {
		System.out.println(id);

		if (contacts.get(id) != null) {
			contacts.remove(id);
			resp.setStatus(200);
			resp.setContentLength(0);
			resp.setContentType("text/plain");
		} else {
			resp.setStatus(404);
			resp.setContentLength(0);
			resp.setContentType("text/plain");
		}
	}
}
