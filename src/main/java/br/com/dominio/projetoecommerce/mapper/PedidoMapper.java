package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Pedido;
import br.com.dominio.projetoecommerce.domain.dto.PedidoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

 PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);
 Pedido toModel(PedidoDto pedidoDto);
 PedidoDto toDto(Pedido pedido);
}
