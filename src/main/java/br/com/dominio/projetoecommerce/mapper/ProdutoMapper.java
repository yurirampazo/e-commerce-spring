package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.model.dto.ProdutoDto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
 Produto toModel(ProdutoDto produtoDto);
 ProdutoDto toDto(Produto produto);

}
