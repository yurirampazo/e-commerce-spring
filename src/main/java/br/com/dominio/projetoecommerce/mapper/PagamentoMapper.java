package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Pagamento;
import br.com.dominio.projetoecommerce.domain.dto.PagamentoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
  Pagamento toModel(PagamentoDto pagamentoDto);
  PagamentoDto toDto(Pagamento pagamentoDto);
}
