package br.com.dominio.projetoecommerce.service.validation;

import br.com.dominio.projetoecommerce.exception.EmailNotFoundException;
import br.com.dominio.projetoecommerce.exception.model.FieldMessage;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, NewClienteDto> {

  @Autowired
  private ClienteRepository repository;

  @Autowired
  private HttpServletRequest request;

  @Override
  public void initialize(ClienteUpdate ann) {

  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean isValid(NewClienteDto objDto, ConstraintValidatorContext context) {

    Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    Integer uriId =  Integer.valueOf(map.get("id"));

    List<FieldMessage> list = new ArrayList<>();

    Cliente aux = repository.findClienteByEmailContainingIgnoreCase(objDto.getEmail());

    Cliente aux2 = repository.findClienteByCpfCnpjContainingIgnoreCase(objDto.getCpfCnpj());

    if(aux != null && !aux.getId().equals(uriId)) {
      list.add(new FieldMessage("email", "email já cadastrado!"));
    }

    if(aux2 != null && !aux2.getId().equals(uriId)) {
      list.add(new FieldMessage("cpfCnpj", "documento já cadastrado"));
    }

    for (FieldMessage x : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(x.getMessage())
            .addPropertyNode(x.getFieldName()).addConstraintViolation();
    }
    return list.isEmpty();
  }
}
