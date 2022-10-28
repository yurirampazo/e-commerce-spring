package br.com.dominio.projetoecommerce.conf;

import br.com.dominio.projetoecommerce.model.PagamentoComBoleto;
import br.com.dominio.projetoecommerce.model.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    var builder = new Jackson2ObjectMapperBuilder() {
      public void configure(ObjectMapper objectMapper) {
        objectMapper.registerSubtypes(PagamentoComCartao.class);
        objectMapper.registerSubtypes(PagamentoComBoleto.class);
        super.configure(objectMapper);
      }
    };
    return builder;
  }
}
