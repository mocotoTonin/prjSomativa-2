package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Agendamento;
import com.example.demo.entities.Cliente;
import com.example.demo.services.AgendamentoService;

@RestController
@RequestMapping("/Agendamento")
public class AgendamentoController {
	private final AgendamentoService agendamentoService;
	
	@Autowired
	public AgendamentoController(AgendamentoService agendamentoService) {
		this.agendamentoService = agendamentoService;	
	}
	
	@PostMapping("/create")
	public Agendamento createAgendamento(@RequestBody Agendamento agendamento) {
		return agendamentoService.saveAgendamento(agendamento);
	}
	
	@GetMapping
	public List<Agendamento> getAll(){
		return agendamentoService.getAllAgendamentos();
	}
	
	@GetMapping("/{id}")
	public Agendamento getById(@PathVariable Long id) {
		return agendamentoService.getAgendamentoById(id);
	}
}