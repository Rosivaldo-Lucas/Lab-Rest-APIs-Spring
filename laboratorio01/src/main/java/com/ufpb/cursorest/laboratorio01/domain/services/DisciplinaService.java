package com.ufpb.cursorest.laboratorio01.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufpb.cursorest.laboratorio01.domain.models.Disciplina;

@Service
public class DisciplinaService {
	private static int id = 0;
	
	private List<Disciplina> disciplinaList = new ArrayList<>();
	
	public Disciplina save(Disciplina disciplina) {
		id += 1;
		
		disciplina.setId(id);
		
		disciplinaList.add(disciplina);
		
		return disciplina;
	}

	public List<Disciplina> getDisciplinaList() {
		return disciplinaList;
	}
	public void setDisciplinaList(List<Disciplina> disciplinaList) {
		this.disciplinaList = disciplinaList;
	}
}
