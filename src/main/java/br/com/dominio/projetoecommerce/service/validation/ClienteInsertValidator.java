package br.com.dominio.projetoecommerce.service.validation;

import br.com.dominio.projetoecommerce.exception.model.FieldMessage;
import br.com.dominio.projetoecommerce.model.dto.ClienteDto;
import br.com.dominio.projetoecommerce.service.validation.utils.BR;
import br.com.dominio.projetoecommerce.util.TipoCliente;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDto> {

  @Override
  public void initialize(ClienteInsert ann) {

  }

  @Override
  public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
    List<FieldMessage> list = new ArrayList<>();

    if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA) && !BR.isValidCpf(objDto.getCpfCnpj())) {
      list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
    }

    if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCnpj(objDto.getCpfCnpj())) {
      list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
    }

    for (FieldMessage x : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(x.getMessage())
            .addPropertyNode(x.getFieldName()).addConstraintViolation();
    }
    return list.isEmpty();
  }
}
