package com.ufpb.cursorest.laboratorio03.domain.repositories;

import com.ufpb.cursorest.laboratorio03.domain.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
