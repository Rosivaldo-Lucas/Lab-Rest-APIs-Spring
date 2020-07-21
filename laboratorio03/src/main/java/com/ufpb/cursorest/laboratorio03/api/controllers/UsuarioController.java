package com.ufpb.cursorest.laboratorio03.api.controllers;

import com.ufpb.cursorest.laboratorio03.api.dto.response.UsuarioResponseDTO;
import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;
import com.ufpb.cursorest.laboratorio03.domain.services.UsuarioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody Usuario usuario) {
    var usuarioSalvo = usuarioService.add(usuario);

    var usuarioResponseDTO = toUsuarioResponseDTO(usuarioSalvo);

    return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
  }

  // Converte Usuario para UsuarioResponseDTO
  private UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(usuario, UsuarioResponseDTO.class);
  }

}
