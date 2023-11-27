package kz.timka.tacocloudspringdatajdbc.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TacoDTO {
    private String name;
    private List<String> ingredients;
}
