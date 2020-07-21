package com.ufpb.cursorest.laboratorio02.domain.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufpb.cursorest.laboratorio02.domain.exception.DisciplinaException;
import com.ufpb.cursorest.laboratorio02.domain.models.Disciplina;
import com.ufpb.cursorest.laboratorio02.domain.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository<Disciplina, Long> disciplinaRepository;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");

        try {
            List<Disciplina> nomesDisciplina = mapper.readValue(inputStream, typeReference);

            if (disciplinaRepository.count() == 0) {
                disciplinaRepository.saveAll(nomesDisciplina);
            }

        } catch (IOException e) {
            System.out.println("Deu ruim: " + e.getCause());
        }
    }

    // Get
    public List<Disciplina> listar() {
        return disciplinaRepository.findAll();
    }

    // Get
    public Disciplina buscar(Long id) {
        return buscarDisciplinaId(id);
    }

    // Get Ranking Notas
    public List<Disciplina> rankingNotas() {
        return disciplinaRepository.findByOrderByNotaDesc();
    }

    // Get Ranking Likes
    public List<Disciplina> rankingLikes() {
        return disciplinaRepository.findByOrderByLikesDesc();
    }

    // Put Likes
    public Disciplina editarLike(Long id) {
        Disciplina disciplina = buscarDisciplinaId(id);

        disciplina.setLikes(disciplina.getLikes() + 1);

        return disciplinaRepository.save(disciplina);
    }

    // Put Nota
    public Disciplina editarNota(Disciplina disciplinaParam, Long id) {
        Disciplina disciplina = buscarDisciplinaId(id);

        if (disciplina.getNota() == 0.0) {
            disciplina.setNota(disciplinaParam.getNota());
        } else {
            disciplina.setNota((disciplina.getNota() + disciplinaParam.getNota()) / 2);
        }

        return disciplinaRepository.save(disciplina);
    }

    // Put Comentario
    public Disciplina adicionarComentario(Disciplina disciplinaParam, Long id) {
        Disciplina disciplina = buscarDisciplinaId(id);

        if (disciplina.getComentario().equals("")) {
            disciplina.setComentario(disciplinaParam.getComentario());
        } else {
            disciplina.setComentario(disciplina.getComentario().concat(", ").concat(disciplinaParam.getComentario()));
        }

        return disciplinaRepository.save(disciplina);
    }

    // Auxiliar
    private Disciplina buscarDisciplinaId(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        if (disciplina.isEmpty()) {
            throw new DisciplinaException("Disciplina não encontrada.");
        }

        return disciplina.get();
    }
}
