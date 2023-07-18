package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Produto;
import br.com.dominio.projetoecommerce.domain.dto.ProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

 ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
 Produto toModel(ProdutoDto produtoDto);
 ProdutoDto toDto(Produto produto);

}
