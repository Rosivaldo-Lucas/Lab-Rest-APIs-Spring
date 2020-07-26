package com.ufpb.cursorest.laboratorio03.api.dto.request;

public class UsuarioRequestDTO {
  private String email;
  private String senha;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
