package br.com.dominio.projetoecommerce.security.service;

import br.com.dominio.projetoecommerce.domain.Cliente;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.security.domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final ClienteRepository repository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Cliente cli = repository.findByEmail(email);
    if (cli == null) {
      throw new UsernameNotFoundException(email);
    }
    return new AppUser(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getRoles());
  }
}
