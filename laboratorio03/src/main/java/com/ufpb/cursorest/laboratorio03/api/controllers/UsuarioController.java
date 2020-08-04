package com.ufpb.cursorest.laboratorio03.api.controllers;

import javax.servlet.ServletException;

import com.ufpb.cursorest.laboratorio03.api.dto.response.UsuarioResponseDTO;
import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;
import com.ufpb.cursorest.laboratorio03.domain.services.UsuarioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

  @GetMapping("/{email}")
	public ResponseEntity<Usuario> adicionaUsuario(@PathVariable String email) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.getUsuario(email), HttpStatus.OK);
		} catch(IllegalArgumentException iae) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);	
		}
	}

  @DeleteMapping("/{email}")
  public ResponseEntity<Usuario> deletar(@PathVariable String email, @RequestHeader("Authorization") String header)
      throws ServletException {
    
    try {
			return new ResponseEntity<Usuario>(usuarioService.deletar(email, header), HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		} catch (ServletException e) {
			return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
		}
  }

  // Converte Usuario para UsuarioResponseDTO
  private UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
    ModelMapper modelMapper = new ModelMapper();

    return modelMapper.map(usuario, UsuarioResponseDTO.class);
  }

}
