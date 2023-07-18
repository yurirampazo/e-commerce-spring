package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.domain.dto.NewClienteDto;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomerGroupSequenceProvider implements DefaultGroupSequenceProvider<NewClienteDto> {
  @Override
  public List<Class<?>> getValidationGroups(NewClienteDto customer) {
    List<Class<?>> groups = new ArrayList<>();
    groups.add(NewClienteDto.class);

    if (isCustomerValid(customer)) groups.add(customer.getTipo().getGroup());
    return groups;
  }

  protected boolean isCustomerValid(NewClienteDto customer) {
    return customer != null && customer.getTipo() != null;
  }
}
