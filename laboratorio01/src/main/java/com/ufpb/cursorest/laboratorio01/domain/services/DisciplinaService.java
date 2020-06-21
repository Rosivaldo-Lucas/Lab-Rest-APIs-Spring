package com.ufpb.cursorest.laboratorio01.domain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufpb.cursorest.laboratorio01.domain.models.Disciplina;

@Service
public class DisciplinaService {
	private static int id = 0;
	
	private List<Disciplina> disciplinaList = new ArrayList<>();
	
	public List<Disciplina> findAll() {
		return this.getDisciplinaList();
	}
	
	public Disciplina findById(int disciplinaId) {
		if (disciplinaList.isEmpty() || disciplinaId <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Disciplina disciplina = null;
		
		for (Disciplina d : disciplinaList) {
			if (d.getId() == disciplinaId) {
				disciplina = d;
			}
		}
		
		return disciplina;
	}
	
	public Disciplina save(Disciplina disciplina) {
		id += 1;
		
		disciplina.setId(id);
		
		disciplinaList.add(disciplina);
		
		return disciplina;
	}

	public Disciplina edit(int disciplinaId, double nota) {
		Disciplina disciplina = this.findById(disciplinaId);
		
		disciplina.setNota(nota);
		
		return disciplina;
	}
	
	public Disciplina delete(int disciplinaId) {
		Disciplina disciplina = this.findById(disciplinaId);
		
		disciplinaList.remove(disciplina);
		
		return disciplina;
	}
	
	public List<Disciplina> ranking() {
		List<Disciplina> disciplinaListOrdenada = new ArrayList<>();
		
		for (int i = 0; i < disciplinaList.size(); i++) {
			disciplinaListOrdenada.add(disciplinaList.get(i));
		}
		
		Collections.sort(disciplinaListOrdenada);
		return disciplinaListOrdenada;
	}
	
	public List<Disciplina> getDisciplinaList() {
		return disciplinaList;
	}
	public void setDisciplinaList(List<Disciplina> disciplinaList) {
		this.disciplinaList = disciplinaList;
	}
}
