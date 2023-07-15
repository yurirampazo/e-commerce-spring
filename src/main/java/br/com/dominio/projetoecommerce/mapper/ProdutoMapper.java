package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.model.dto.ProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

 ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
 Produto toModel(ProdutoDto produtoDto);
 ProdutoDto toDto(Produto produto);

}
