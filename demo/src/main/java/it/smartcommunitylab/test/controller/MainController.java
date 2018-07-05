package it.smartcommunitylab.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.smartcommunitylab.test.model.Persona;

@Controller
public class MainController {
	private Map<String, Persona> dataMap = new HashMap<>();
	
	@RequestMapping("/hello")
	public @ResponseBody String hello() {
		return "Hello World!!!";
	}
	
	@RequestMapping("/")
	public String index() {
		return "hello.html";
	}
	
	@GetMapping(value = "/persone")
	public @ResponseBody List<Persona> list() {
		return new ArrayList<>(dataMap.values());
	}

	@GetMapping(value = "/persona/{id}")
	public @ResponseBody Persona get(@PathVariable String id) {
		return dataMap.get(id);
	}

	@PostMapping(value = "/persona")
	public @ResponseBody Persona add(@RequestBody Persona persona) {
		persona.setId(UUID.randomUUID().toString());
		dataMap.put(persona.getId(), persona);
		return persona;
	}

	@PutMapping(value = "/persona/{id}")
	public ResponseEntity<Persona> update(@PathVariable String id,
			@RequestBody Persona persona) {
		Persona personaMap = dataMap.get(id); 
		if(personaMap != null) {
			persona.setId(id);
			dataMap.put(persona.getId(), persona);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		} else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND); 
		}
	}

	@DeleteMapping(value = "/persona/{id}")
	public ResponseEntity<Persona> delete(@PathVariable String id) {
		Persona personaMap = dataMap.get(id);
		if(personaMap != null) {
			dataMap.remove(id);
			return new ResponseEntity<Persona>(personaMap, HttpStatus.OK);
		} else {
			return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		}
	}
}
