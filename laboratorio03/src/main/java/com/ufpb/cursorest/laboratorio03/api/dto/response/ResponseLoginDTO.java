package com.ufpb.cursorest.laboratorio03.api.dto.response;

public class ResponseLoginDTO {
  private String token;

  public ResponseLoginDTO(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
