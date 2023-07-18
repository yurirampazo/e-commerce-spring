package br.com.dominio.projetoecommerce.domain.enums;

import br.com.dominio.projetoecommerce.exception.MapEnumException;

public enum AppRole {
  ADMIN(1, "ROLE_ADMIN"),
  USER(2, "ROLE_USER");

  private final Integer id;
  private final String name;

  AppRole(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static AppRole toEnum(Integer cod) {
    if (cod == null) {
      throw new MapEnumException();
    }

    for (AppRole x : AppRole.values()) {
      if (x.getId().equals(cod)) {
        return x;
      }
    }
    throw new IllegalArgumentException("Invalid ID: " + cod);
  }

}
