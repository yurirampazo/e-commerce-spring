package br.com.dominio.projetoecommerce.security.domain;

import br.com.dominio.projetoecommerce.domain.enums.AppRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUser implements UserDetails {

  private Integer id;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public AppUser() {
  }

  public AppUser(Integer id, String email, String password, Set<AppRole> perfis) {
    super();
    this.id = id;
    this.email = email;
    this.password = password;
    this.authorities = perfis.stream().map(x ->
          new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
  }

  public Integer getId() {
    return id;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
