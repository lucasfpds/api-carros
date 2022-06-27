package br.com.carros.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.carros.models.Carro;

public interface ICarro extends JpaRepository<Carro, Integer> {

    List<Carro> findByTipo(String tipo);

}
