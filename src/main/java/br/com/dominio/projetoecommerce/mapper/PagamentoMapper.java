package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.PagamentoComBoleto;
import br.com.dominio.projetoecommerce.model.PagamentoComCartao;
import br.com.dominio.projetoecommerce.model.dto.PagamentoDto;
import org.springframework.dao.TypeMismatchDataAccessException;

public class PagamentoMapper {
  public static Pagamento toModel(PagamentoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    if (dto.getDataPagamento() != null || dto.getDataVencimento() != null) {
      PagamentoComBoleto model = new PagamentoComBoleto();
      model.setId(dto.getId());
      model.setPedido(dto.getPedido());
      model.setEstadoPagamento(dto.getEstadoPagamento());
      model.setDataVencimento(dto.getDataVencimento());
      model.setDataPagamento(dto.getDataPagamento());
      return model;
    } else if (dto.getNumeroDeparcelas() != null) {
      PagamentoComCartao model = new PagamentoComCartao();
      model.setId(dto.getId());
      model.setPedido(dto.getPedido());
      model.setEstadoPagamento(dto.getEstadoPagamento());
      model.setNumeroDeparcelas(dto.getNumeroDeparcelas());
      return model;
    }
    throw new TypeMismatchDataAccessException("Não foi possível reconhcer o tipo de pagamento, " +
          "verifique se foram preenchidos dados ambíguos quanto ao tipo de pagamento." +
          "Exemplo: Número de parcelas para pagamanto com boleto!");
  }


  public static PagamentoDto toDto(Pagamento model) {
    if (model == null) {
      throw new MapToDtoException();
    }
    PagamentoDto dto = new PagamentoDto();
    dto.setId(model.getId());
    dto.setEstadoPagamento(model.getEstadoPagamento());
    dto.setPedido(model.getPedido());

    if(model instanceof PagamentoComCartao) {
      dto.setNumeroDeparcelas(((PagamentoComCartao) model).getNumeroDeparcelas());

    } else if (model instanceof PagamentoComBoleto) {
      dto.setDataPagamento(((PagamentoComBoleto) model).getDataPagamento());
      dto.setDataVencimento(((PagamentoComBoleto) model).getDataVencimento());
    }
    return dto;
  }
}
