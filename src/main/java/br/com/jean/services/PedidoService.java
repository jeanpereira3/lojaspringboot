package br.com.jean.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jean.domain.ItemPedido;
import br.com.jean.domain.PagamentoComBoleto;
import br.com.jean.domain.Pedido;
import br.com.jean.domain.enuns.EstadoPagamento;
import br.com.jean.repositories.ItemPedidoRepository;
import br.com.jean.repositories.PagamentoRepository;
import br.com.jean.repositories.PedidoRepository;
import br.com.jean.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;


	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setIntante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagemento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagemento().setPedido(obj);
		if (obj.getPagemento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagemento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getIntante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagemento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

}
