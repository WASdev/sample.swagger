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

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Contact {
	private String name;
	private String phone;
	private String email;
	private String notes;

	public Contact(JsonObject jcontact) {
		setName((String) jcontact.getString(("name")));
		setPhone((String) jcontact.getString("phone"));
		setEmail((String) jcontact.getString("email"));
		setNotes((String) jcontact.getString("notes"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public JsonObject toJsonObject() {
		return getJsonObjectBuilder().build();
	}

	public JsonObjectBuilder getJsonObjectBuilder() {
		JsonObjectBuilder jcontact = Json.createObjectBuilder();
		if (name != null)
			jcontact.add("name", name);
		if (phone != null)
			jcontact.add("phone", phone);
		if (email != null)
			jcontact.add("email", email);
		if (notes != null)
			jcontact.add("notes", notes);
		return jcontact;
	}

}
