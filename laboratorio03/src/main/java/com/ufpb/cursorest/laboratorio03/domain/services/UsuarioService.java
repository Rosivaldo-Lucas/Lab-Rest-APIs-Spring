package com.ufpb.cursorest.laboratorio03.domain.services;

import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;
import com.ufpb.cursorest.laboratorio03.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Usuario add(Usuario usuario) {
    Usuario usuarioSalvo = usuarioRepository.save(usuario);

    return usuarioSalvo;
  }

}
