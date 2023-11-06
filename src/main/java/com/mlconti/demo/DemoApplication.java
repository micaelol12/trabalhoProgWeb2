package com.mlconti.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.domain.Cliente;
import com.mlconti.demo.domain.Endereco;
import com.mlconti.demo.domain.Item_pedido;
import com.mlconti.demo.domain.Municipio;
import com.mlconti.demo.domain.Pedido;
import com.mlconti.demo.domain.Produto;
import com.mlconti.demo.repository.CategoriaRepository;
import com.mlconti.demo.repository.ClienteRepository;
import com.mlconti.demo.repository.EnderecoRepository;
import com.mlconti.demo.repository.ItemPedidoRepository;
import com.mlconti.demo.repository.MunicipioRepository;
import com.mlconti.demo.repository.PedidoRespository;
import com.mlconti.demo.repository.ProdutoRespository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			CategoriaRepository categoriaRepository, ProdutoRespository produtoRespository,
			MunicipioRepository municipioRepository, EnderecoRepository enderecoRepository,
			ClienteRepository clienteRepository, PedidoRespository pedidoRespository,ItemPedidoRepository itemPedidoRepository) {
		return (args) -> {

			Categoria cate1 = new Categoria(null, "esporte");
			Categoria cate2 = new Categoria(null, "lazer");

			Produto produto1 = new Produto(null, "bolinha de tênis", 5, 4.00, cate1);
			Produto produto2 = new Produto(null, "sofá", 2, 250.99, cate2);

			Municipio municipio1 = new Municipio(null, "Rodeio", "SC");
			Municipio municipio2 = new Municipio(null, "Blumenau", "SC");

			Endereco endereco1 = new Endereco(null, "Faustino Pasquali", "", "Rodeio doze", 30, "89136-000",
					municipio1);
			Endereco endereco2 = new Endereco(null, "R. 7 de Setembro", "", "Velha", 2713, "89012-401", municipio2);

			Cliente cliente1 = new Cliente(null, "Micael Conti", 3200.40, "micael.conti@gmail.com", "47996532896",
					endereco1);
			Cliente cliente2 = new Cliente(null, "Geórgia Conti", 3100.40, "georgia.conti@gmail.com", "475644857",
					endereco1);

			Pedido pedido1 = new Pedido(null, LocalDate.of(2023, 11, 6), 300.5, cliente1);
			Pedido pedido2 = new Pedido(null, LocalDate.of(2023, 11, 1), 250.99, cliente1);

			Item_pedido itemPedido1 = new Item_pedido(null,3,4.00,pedido2,produto1);
			Item_pedido itemPedido2 = new Item_pedido(null,1,250.99,pedido2,produto2);

			categoriaRepository.save(cate1);
			categoriaRepository.save(cate2);

			produtoRespository.save(produto1);
			produtoRespository.save(produto2);

			municipioRepository.save(municipio1);
			municipioRepository.save(municipio2);

			enderecoRepository.save(endereco1);
			enderecoRepository.save(endereco2);

			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);

			pedidoRespository.save(pedido1);
			pedidoRespository.save(pedido2);

			itemPedidoRepository.save(itemPedido1);
			itemPedidoRepository.save(itemPedido2);



		};
	}
}
