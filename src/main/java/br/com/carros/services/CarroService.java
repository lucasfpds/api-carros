package br.com.carros.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.carros.DAO.ICarro;
import br.com.carros.DTO.CarroDTO;
import br.com.carros.models.Carro;

@Service
public class CarroService {

    @Autowired
    private ICarro dao;

    public List<CarroDTO> getCarros() {
        List<Carro> carros = dao.findAll();

        // List<CarroDTO> carrosDTO = new ArrayList<>();
        // for (Carro c : carros) {
        // carrosDTO.add(new CarroDTO(c));
        // }
        // return carrosDTO;

        // List<CarroDTO> list = carros.stream().map(c -> new
        // CarroDTO(c)).collect(Collectors.toList());
        // return list;

        return carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarrosById(int id) {
        return dao.findById(id).map(c -> CarroDTO.create(c));
    }

    public List<CarroDTO> getByTipo(String tipo) {
        List<Carro> carros = dao.findByTipo(tipo);
        return carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
    }

    public CarroDTO salvar(Carro carro) {
        return CarroDTO.create(dao.save(carro));
    }

    public CarroDTO atualizar(Carro carro, int id) {
        Assert.notNull(id, "Não foi possível atualizar o carro");

        // Busca o carro pelo id
        Optional<Carro> carroOptional = dao.findById(id);
        if (carroOptional.isPresent()) {
            Carro carroAtualizado = carroOptional.get();
            carroAtualizado.setNome(carro.getNome());
            carroAtualizado.setTipo(carro.getTipo());
            dao.save(carroAtualizado);
            return CarroDTO.create(carroAtualizado);
        }
        return null;
    }

    public boolean deletar(int id) {
        Optional<Carro> carro = dao.findById(id);
        if (carro.isPresent()) {
            dao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
