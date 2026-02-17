package com.appsmart.persistance.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "customer")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Customer {
  @Id private UUID id;

  @Column(name = "title")
  private String title;

  @Column(name = "is_deleted")
  @NotNull
  private Boolean deleted = false;

  @Column(name = "created_at")
  @NotNull
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Customer customer = (Customer) o;

    return Objects.equals(id, customer.id);
  }

  @Override
  public int hashCode() {
    return 339958611;
  }
}
