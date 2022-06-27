package br.com.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.carros.DTO.CarroDTO;
import br.com.carros.models.Carro;
import br.com.carros.services.CarroService;
import lombok.AllArgsConstructor;

@SpringBootTest
// @AllArgsConstructor
class CarrosApplicationTests {

	@Autowired
	private CarroService carroService;

	@Test
	public void save() {
		Carro carro = new Carro();
		carro.setNome("Fusca");
		carro.setTipo("Esportivo");

		CarroDTO carroDTO = carroService.salvar(carro);
		assertNotNull(carroDTO);
		int id = carroDTO.getId();
		assertNotNull(id);

		// Busca o objeto
		Optional<CarroDTO> op = carroService.getCarrosById(id);
		assertTrue(op.isPresent());

		carroDTO = op.get();
		assertEquals("Fusca", carroDTO.getNome());
		assertEquals("Esportivo", carroDTO.getTipo());

		// Deletar o objeto
		carroService.deletar(id);

		// Verifica se o objeto foi deletado
		assertFalse(carroService.getCarrosById(id).isPresent());
	}

	@Test
	public void listarCarros() {
		List<CarroDTO> carros = carroService.getCarros();
		assertNotNull(carros);
		assertFalse(carros.isEmpty());
		System.out.println(carros.size());
		assertEquals(30, carros.size());
	}

	@Test
	public void listarCarrosById() {
		Optional<CarroDTO> op = carroService.getCarrosById(1);
		assertTrue(op.isPresent());
		CarroDTO carro = op.get();
		assertEquals("Tucker 1948", carro.getNome());
		assertEquals("classicos", carro.getTipo());
	}

	@Test
	public void listarCarrosByTipo() {
		List<CarroDTO> carros = carroService.getByTipo("classicos");
		assertNotNull(carros);
		assertFalse(carros.isEmpty());
		assertEquals(10, carros.size());
	}

	@Test
	public void listarCarrosByTipo2() {
		List<CarroDTO> carros = carroService.getByTipo("esportivos");
		assertNotNull(carros);
		assertFalse(carros.isEmpty());
		assertEquals(10, carros.size());
	}
}
