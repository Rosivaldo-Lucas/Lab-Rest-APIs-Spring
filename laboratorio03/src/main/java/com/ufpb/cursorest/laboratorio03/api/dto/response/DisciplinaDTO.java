package com.ufpb.cursorest.laboratorio03.api.dto.response;

import lombok.Data;

@Data
public class DisciplinaDTO {
  private Long id;
  private String nome;
  private Double nota;
  private String comentario;
  private Integer likes;
}
