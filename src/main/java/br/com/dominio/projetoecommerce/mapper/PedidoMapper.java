package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
 Pedido toModel(PedidoDto pedidoDto);
 PedidoDto toDto(Pedido pedido);
}
