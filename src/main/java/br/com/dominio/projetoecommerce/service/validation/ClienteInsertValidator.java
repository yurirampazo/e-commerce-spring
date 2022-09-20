package br.com.dominio.projetoecommerce.service.validation;

import br.com.dominio.projetoecommerce.exception.model.FieldMessage;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.service.validation.utils.BR;
import br.com.dominio.projetoecommerce.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDto> {

  @Autowired
  private ClienteRepository repository;

  @Override
  public void initialize(ClienteInsert ann) {

  }

  @Override
  public boolean isValid(NewClienteDto objDto, ConstraintValidatorContext context) {
    List<FieldMessage> list = new ArrayList<>();

    if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA) && !BR.isValidCpf(objDto.getCpfCnpj())) {
      list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
    }

    if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCnpj(objDto.getCpfCnpj())) {
      list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
    }

    Cliente aux = repository.findClienteByEmailContainingIgnoreCase(objDto.getEmail());
    Cliente aux2 = repository.findClienteByCpfCnpjContainingIgnoreCase(objDto.getCpfCnpj());

    if(aux != null) {
      list.add(new FieldMessage("email", "email já cadastradao!"));
    }

    if (aux2 != null) {
      list.add(new FieldMessage("cpfCnpj", "Documento já cadastrado!"));
    }

    for (FieldMessage x : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(x.getMessage())
            .addPropertyNode(x.getFieldName()).addConstraintViolation();
    }
    return list.isEmpty();
  }
}
