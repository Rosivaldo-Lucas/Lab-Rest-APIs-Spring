package com.ufpb.cursorest.laboratorio03.domain.services;

import java.util.Optional;

import com.ufpb.cursorest.laboratorio03.domain.exception.DisciplinaException;
import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;
import com.ufpb.cursorest.laboratorio03.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private JWTService jwtService;

  public Usuario add(Usuario usuario) {
    Usuario usuarioSalvo = usuarioRepository.save(usuario);

    return usuarioSalvo;
  }

  private Boolean usuarioTemPermisao(String authorizationHeader, String email) {
    String subject = jwtService.getToken(authorizationHeader);
    Optional<Usuario> optUsuario = usuarioRepository.findByEmail(subject);

    return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
  }

  public Boolean validaUsuarioSenha(Usuario usuarioRequestDTO) {
    Usuario usuario = buscaUsuarioEmail(usuarioRequestDTO.getEmail());

    if (usuario.getSenha().equals(usuarioRequestDTO.getSenha())) {
      return true;
    }

    return false;
  }

  // Procura Usuario pelo seu email
  private Usuario buscaUsuarioEmail(String email) {
    Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

    if (usuario.isEmpty()) {
      throw new DisciplinaException("Usuário não encontrado.");
    }

    return usuario.get();
  }

}
