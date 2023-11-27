package kz.timka.tacocloudspringdatajdbc.converters;

import kz.timka.tacocloudspringdatajdbc.data.IngredientRef;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientRefConverter implements Converter<String, IngredientRef> {

    @Override
    public IngredientRef convert(String source) {
        IngredientRef ref = new IngredientRef();
        ref.setIngredient(source);
        return ref;
    }
}
