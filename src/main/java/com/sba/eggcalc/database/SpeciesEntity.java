package com.sba.eggcalc.database;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Accessors(chain = true)
@Table(name = "SPECIES")
public class SpeciesEntity {

  @Id
  @Column("monId")
  @NotNull
  private Long monId;

  @Column("name")
  @NotBlank
  private String name;

  @Column("canBeFemale")
  @NotNull
  private Boolean canBeFemale;

  @Column("canBeMale")
  @NotNull
  private Boolean canBeMale;

  @Column("group1Id")
  @NotNull
  private Integer group1Id;

  @Column("group2Id")
  private Integer group2Id;
}

// Whether additional Entities are needed is under FEAT 3: map out needed dao functions.