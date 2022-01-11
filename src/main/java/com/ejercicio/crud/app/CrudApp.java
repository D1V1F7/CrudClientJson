package com.ejercicio.crud.app;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ejercicio.crud.entity.Client;

public class CrudApp {

	private static final String FILE_NAME = "jsonData.json";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String EMAIL = "email";
	private static final String MSG_OK = "Operación Correcta";
	private static final String MSG_ERROR = "No se encontro el registro";
	private static final String MSG_FILE = "No existe el archivo de clientes";
	
	private File jsonData = new File(FILE_NAME);
	
	public Object getClient() throws Exception {
		if(!jsonData.exists()) {
			return MSG_FILE;
		} else {
		    return dataClient();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String createClient(Client client) throws Exception {
		client.setId(1);
		List<String> json = new ArrayList<>();
		JSONObject clientJson = new JSONObject();
		if(!jsonData.exists()) {
			jsonData.createNewFile();
		} else {
			List<Client> dataClient = dataClient();
			client.setId(dataClient.get(dataClient.size()- 1).getId() + 1);
			json = dataClientString(dataClient);
		}
		clientJson.put(ID, client.getId());
		clientJson.put(NAME, client.getName());
		clientJson.put(EMAIL, client.getEmail());
		json.add(clientJson.toJSONString());
		Files.write(Paths.get(FILE_NAME), json.toString().getBytes());
		
		return MSG_OK;
	}
	
	public String updateClient(Client client) throws Exception {
		if(!jsonData.exists()) {
			return MSG_FILE;
		} else {
			return setClient(client.getId(), 1, client);
		}
	}
	
	public String deleteClient(int id) throws Exception {
		if(!jsonData.exists()) {
			return MSG_FILE;
		} else {
			return setClient(id, 0, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Client> dataClient() throws Exception {
		List<Client> json = new ArrayList<>();
		Client clientJson = new Client();
		FileReader reader = new FileReader(FILE_NAME);
	    JSONParser jsonParser = new JSONParser();
	    List<Object> jsonList = (ArrayList<Object>) jsonParser.parse(reader);
	    for (Object item : jsonList) {
			clientJson = new Client();
			HashMap<Object, Object> map = (HashMap<Object, Object>) item;
			clientJson.setId(Integer.valueOf(map.get(ID).toString()));
			clientJson.setName(map.get(NAME).toString());
			clientJson.setEmail(map.get(EMAIL).toString());
			json.add(clientJson);
		}

	    return json;
	}
	
	@SuppressWarnings("unchecked")
	private List<String> dataClientString(List<Client> dataClient) {
		List<String> json = new ArrayList<>();
		JSONObject clientJson = new JSONObject();
		for (Client item : dataClient) {
			clientJson = new JSONObject();
			clientJson.put(ID, item.getId());
			clientJson.put(NAME, item.getName());
			clientJson.put(EMAIL, item.getEmail());
			json.add(clientJson.toJSONString());
		}
		return json;
	}
	
	private String setClient(int id, int opc, Client client) throws Exception {
		List<Client> dataClient = dataClient();
		List<String> json = new ArrayList<>();
		for (int i = 0; i < dataClient.size(); i++) {
			if(dataClient.get(i).getId() == id) {
				if (opc == 0) {
					dataClient.remove(i);
				} else {
					dataClient.set(i, client);
				}
				json = dataClientString(dataClient);
				Files.write(Paths.get(FILE_NAME), json.toString().getBytes());
				return MSG_OK;
			}
		}
		return MSG_ERROR;
	}
}
