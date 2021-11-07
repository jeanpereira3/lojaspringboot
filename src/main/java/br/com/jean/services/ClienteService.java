package br.com.jean.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jean.domain.Cliente;
import br.com.jean.repositories.ClienteRepository;
import br.com.jean.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

//	public Categoria buscar(Integer id) {
//		Optional<Categoria> obj = repository.findById(id);
//		return obj.orElse(null);
//	}

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
