package com.pragma.powerup;

import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatoEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoriaRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrdenPlatoRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrdenRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IPlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Main implements CommandLineRunner {


	@Autowired
	private IPlatoRepository platoRepository;

	@Autowired
	private IOrdenPlatoRepository ordenPlatoRepository;

	@Autowired
	private IOrdenRepository orderRepository;

	@Autowired
	private ICategoriaRepository categoriaRepository;


	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<PlatoEntity> platoEntityList= platoRepository.findAll();
		for (PlatoEntity valor:platoEntityList) {
			System.out.println(valor.getCategoriaId().getId());
		}

		Optional<PlatoEntity> platoEntity = platoRepository.findById(2L);
		PlatoEntity platoEntityOptional= platoEntity.orElse(null);
		//System.out.println(platoEntityOptional.getCategoriaId());

		Optional<CategoriaEntity> categoryEntity = categoriaRepository.findById(1L);
		CategoriaEntity categoryEntityOptional = categoryEntity.orElse(null);

		Optional<OrdenEntity> orderEntity = orderRepository.findById(4L);
		OrdenEntity orderEntityOptional= orderEntity.orElse(null);
	}
}
