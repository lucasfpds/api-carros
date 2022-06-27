package br.com.carros.DTO;

import org.modelmapper.ModelMapper;

import br.com.carros.models.Carro;
import lombok.Data;

@Data
public class CarroDTO {

    private int id;
    private String nome;
    private String tipo;
    private String descricao;

    // public CarroDTO(Carro c) {
    // this.id = c.getId();
    // this.nome = c.getNome();
    // this.tipo = c.getTipo();
    // }

    public static CarroDTO create(Carro c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarroDTO.class);
    }

}
