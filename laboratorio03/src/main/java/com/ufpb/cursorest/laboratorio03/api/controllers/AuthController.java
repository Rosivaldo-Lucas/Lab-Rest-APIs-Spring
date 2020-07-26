package com.ufpb.cursorest.laboratorio03.api.controllers;

import javax.servlet.ServletException;

import com.ufpb.cursorest.laboratorio03.api.dto.response.ResponseLoginDTO;
import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;
import com.ufpb.cursorest.laboratorio03.domain.services.JWTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
	private JWTService jwtService;

	@PostMapping
	public ResponseEntity<ResponseLoginDTO> autentica(@RequestBody Usuario usuario) throws ServletException {
    System.out.println("foiii" + usuario.getEmail());
    return new ResponseEntity<>(jwtService.auth(usuario), HttpStatus.OK);
	}

}
