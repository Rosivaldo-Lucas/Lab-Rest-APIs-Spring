package com.ufpb.cursorest.laboratorio02.api.controllers;

import com.ufpb.cursorest.laboratorio02.domain.models.Disciplina;
import com.ufpb.cursorest.laboratorio02.domain.repositories.DisciplinaRepository;
import com.ufpb.cursorest.laboratorio02.domain.services.DisciplinaService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> listar() {
        List<Disciplina> disciplinas = disciplinaService.listar();

        return new ResponseEntity<List<Disciplina>>(disciplinas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscar(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.buscar(id);

        return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);

    }

    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Disciplina>> rankingNotas() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.rankingNotas(), HttpStatus.OK);
    }

    @GetMapping("/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingLikes() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.rankingLikes(), HttpStatus.OK);
    }

    @PutMapping("/likes/{id}")
    public ResponseEntity<Disciplina> adicionarLike(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.editarLike(id);

        return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
    }

    @PutMapping("/nota/{id}")
    public ResponseEntity<Disciplina> adicionarNota(@RequestBody Disciplina disciplina, @PathVariable Long id) {
        Disciplina disciplinaEditada = disciplinaService.editarNota(disciplina, id);

        return new ResponseEntity<Disciplina>(disciplinaEditada, HttpStatus.OK);
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<Disciplina> adicionarComentario(@RequestBody Disciplina disciplina, @PathVariable Long id) {
        Disciplina disciplinaEditada = disciplinaService.adicionarComentario(disciplina, id);

        return new ResponseEntity<Disciplina>(disciplinaEditada, HttpStatus.OK);
    }
}
