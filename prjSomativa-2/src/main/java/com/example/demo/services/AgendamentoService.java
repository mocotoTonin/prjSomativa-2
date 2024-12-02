package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Agendamento;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {
	private final AgendamentoRepository agendamentoRepository;
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository) {
		this.agendamentoRepository = agendamentoRepository;
		this.clienteRepository = clienteRepository;
	}
	
	public Agendamento saveAgendamento(Agendamento agendamento) {
		if(agendamento.getCliente() != null && agendamento.getCliente().getId() != null) {
			Optional<Cliente> cliente = clienteRepository.findById(agendamento.getCliente().getId());
			if(cliente.isPresent()) {
				agendamento.setCliente(cliente.get());
				return agendamentoRepository.save(agendamento);
			}else {
				throw new RuntimeException("Cliente não encontrado");
			}
		}else {
			throw new RuntimeException("O ID do Cliente é obrigatório");
		}
	}
	
	public Agendamento getAgendamentoById(Long id) {
		return agendamentoRepository.findById(id).orElse(null);
	}
	
	public List<Agendamento> getAllAgendamentos(){
		return agendamentoRepository.findAll();
	}
}