package br.com.carros.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.carros.DTO.CarroDTO;
import br.com.carros.models.Carro;
import br.com.carros.services.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    private CarroService carroService;

    public CarrosController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public ResponseEntity<List<CarroDTO>> listar() {
        // return new ResponseEntity<>(carroService.getCarros(),HttpStatus.OK);
        return ResponseEntity.ok(carroService.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") int id) {
        Optional<CarroDTO> carro = carroService.getCarrosById(id);
        // if (carro.isPresent()) {
        // return ResponseEntity.ok(carro.get());
        // } else {
        // return ResponseEntity.notFound().build();
        // }
        return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getOne(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carros = carroService.getByTipo(tipo);

        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
        // if(carros.isEmpty()) {
        // return ResponseEntity.noContent().build();
        // } else {
        // return ResponseEntity.ok(carros);
        // }

    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Carro carro) {
        if (carro.getId() == 0) {
            CarroDTO c = carroService.salvar(carro);
            URI location = getUri(c.getId());
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(int id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") int id, @RequestBody Carro carro) {
        CarroDTO c = carroService.atualizar(carro, id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") int id) {
        return carroService.deletar(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
