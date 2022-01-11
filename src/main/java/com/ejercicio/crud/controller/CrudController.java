package com.ejercicio.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.crud.app.CrudApp;
import com.ejercicio.crud.entity.Client;

@RestController
@RequestMapping("/crud")
public class CrudController {

	private CrudApp crud = new CrudApp();
	
	@GetMapping
	public ResponseEntity<Object> getClient() throws Exception{
		return ResponseEntity.ok(crud.getClient());
	}
	
	@PostMapping
	public ResponseEntity<Object> createClient(@RequestBody Client client) throws Exception {
		return ResponseEntity.ok(crud.createClient(client));
	}
	
	@PutMapping
	public ResponseEntity<Object> updateClient(@RequestBody Client client) throws Exception {
		return ResponseEntity.ok(crud.updateClient(client));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable("id") int id) throws Exception {
		return ResponseEntity.ok(crud.deleteClient(id));
	}
}
