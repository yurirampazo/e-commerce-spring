package br.com.dominio.projetoecommerce.security.service;

import br.com.dominio.projetoecommerce.domain.Cliente;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.security.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private ClienteRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Cliente cli = repository.findByEmail(email);
    if (cli == null) {
      throw new UsernameNotFoundException(email);
    }
    return new AppUser(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getRoles());
  }
}
