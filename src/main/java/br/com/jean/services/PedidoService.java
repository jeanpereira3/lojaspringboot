package br.com.jean.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jean.domain.Pedido;
import br.com.jean.repositories.PedidoRepository;
import br.com.jean.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

//	public Pedido buscar(Integer id) {
//		Optional<Pedido> obj = repository.findById(id);
//		return obj.orElse(null);
//	}

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
