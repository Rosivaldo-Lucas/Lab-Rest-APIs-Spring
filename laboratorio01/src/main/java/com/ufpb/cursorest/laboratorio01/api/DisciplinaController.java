package com.ufpb.cursorest.laboratorio01.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> adicionar(@RequestBody Disciplina disciplina) {
		return new ResponseEntity<Disciplina>(disciplinaService.save(disciplina), HttpStatus.OK);
	}
}
