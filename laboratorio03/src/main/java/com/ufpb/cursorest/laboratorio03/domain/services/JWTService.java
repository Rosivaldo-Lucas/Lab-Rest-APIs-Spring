package com.ufpb.cursorest.laboratorio03.domain.services;

import java.util.Date;

import com.ufpb.cursorest.laboratorio03.api.dto.response.ResponseLoginDTO;
import com.ufpb.cursorest.laboratorio03.domain.filters.TokenFilter;
import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JWTService {
  
  public static final String SECRECT_TOKEN = "1f2ba707193a951cf844d0debaee0fd6";

  @Autowired
  private UsuarioService usuarioService;

  public ResponseLoginDTO auth(Usuario usuarioRequestDTO) {
    if (!usuarioService.validaUsuarioSenha(usuarioRequestDTO)) {
      return new ResponseLoginDTO("Usuário ou senha inválidos.");
    }

    String token = geraToken(usuarioRequestDTO.getEmail());

    return new ResponseLoginDTO(token);
  }

  private String geraToken(String email) {
    return Jwts.builder().setHeaderParam("typ", "JWT")
      .setSubject(email)
      .signWith(SignatureAlgorithm.HS512, SECRECT_TOKEN)
      .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
      .compact();
  }

  public String getToken(String authorizationHeader) {
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      throw new SecurityException("Token inexistente ou mal formatado!");
    }

    String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

    String subject = null;

    try {
      subject = Jwts.parser().setSigningKey(SECRECT_TOKEN)
        .parseClaimsJws(token).getBody().getSubject();
    } catch (SignatureException e) {
      throw new SecurityException("Token invalido ou expirado!");
    }

    return subject;

  }

}
