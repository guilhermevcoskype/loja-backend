package com.gui.infra.conversor;

import java.util.stream.Stream;

import com.gui.domain.model.TipoProduto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoProdutoConversor implements AttributeConverter<TipoProduto, String> {

    @Override
    public String convertToDatabaseColumn(TipoProduto tipoProduto) {
        if (tipoProduto == null) {
            return null;
        }
        return tipoProduto.getTipo();

    }

    @Override
    public TipoProduto convertToEntityAttribute(String tipoProduto) {
        if (tipoProduto == null) {
            return null;
        }
        return Stream.of(TipoProduto.values()).filter(retorno -> retorno.getTipo().equals(tipoProduto))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
