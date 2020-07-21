package com.ufpb.cursorest.laboratorio02.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.ufpb.cursorest.laboratorio02.api.dto.response.DisciplinaDTO;
import com.ufpb.cursorest.laboratorio02.api.dto.response.DisciplinaListaDTO;
import com.ufpb.cursorest.laboratorio02.domain.models.Disciplina;
import com.ufpb.cursorest.laboratorio02.domain.services.DisciplinaService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

  @Autowired
  private DisciplinaService disciplinaService;

  @GetMapping
  public ResponseEntity<List<DisciplinaListaDTO>> listar() {
    List<Disciplina> disciplinas = disciplinaService.listar();

    var disciplinaListaDTO = toDisciplinaListaDTO(disciplinas);

    return new ResponseEntity<>(disciplinaListaDTO, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DisciplinaDTO> buscar(@PathVariable Long id) {
    Disciplina disciplina = disciplinaService.buscar(id);

    var disciplinaDTO = toDisciplinaDTO(disciplina);

    return new ResponseEntity<>(disciplinaDTO, HttpStatus.OK);
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

  // Converte Disciplina para DisciplinaDTO
  private DisciplinaDTO toDisciplinaDTO(Disciplina disciplina) {
    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(disciplina, DisciplinaDTO.class);
  }

  // Converte uma lista de Disciplina para DisciplinaListaDTO
  private List<DisciplinaListaDTO> toDisciplinaListaDTO(List<Disciplina> disciplinas) {
    ModelMapper modelMapper = new ModelMapper();

    return disciplinas.stream().map((disciplina) -> modelMapper.map(disciplina, DisciplinaListaDTO.class))
        .collect(Collectors.toList());

  }
}
