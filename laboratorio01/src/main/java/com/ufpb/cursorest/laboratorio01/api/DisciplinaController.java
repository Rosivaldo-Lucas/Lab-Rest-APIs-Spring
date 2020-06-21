package com.ufpb.cursorest.laboratorio01.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ufpb.cursorest.laboratorio01.domain.models.Disciplina;
import com.ufpb.cursorest.laboratorio01.domain.services.DisciplinaService;

@RestController
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@GetMapping("/v1/api/disciplinas")
	public ResponseEntity<List<Disciplina>> buscar() {
		return new ResponseEntity<List<Disciplina>>(disciplinaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/api/disciplinas/{disciplinaId}")
	public ResponseEntity<Disciplina> show(@PathVariable int disciplinaId) {
		try {
			Disciplina disciplina = disciplinaService.findById(disciplinaId);
			
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} catch(ArrayIndexOutOfBoundsException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> adicionar(@RequestBody Disciplina disciplina) {
		return new ResponseEntity<Disciplina>(disciplinaService.save(disciplina), HttpStatus.OK);
	}
	
	@PutMapping("/v1/api/disciplinas/{disciplinaId}/nota")
	public ResponseEntity<Disciplina> editar(@PathVariable int disciplinaId, @RequestBody Disciplina disciplina) {
		try {
			Disciplina disciplinaEditada = disciplinaService.edit(disciplinaId, disciplina.getNota());
			
			return new ResponseEntity<Disciplina>(disciplinaEditada, HttpStatus.OK);
		} catch(ArrayIndexOutOfBoundsException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/v1/api/disciplinas/{disciplinaId}")
	public ResponseEntity<Disciplina> deletar(@PathVariable int disciplinaId) {
		try {
			Disciplina disciplina = disciplinaService.delete(disciplinaId);
			
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} catch(ArrayIndexOutOfBoundsException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
