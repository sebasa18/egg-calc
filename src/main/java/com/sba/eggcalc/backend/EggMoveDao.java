package com.sba.eggcalc.backend;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

@Repository
@Validated
@Transactional(propagation = Propagation.MANDATORY)
public class EggMoveDao {

  private final DSLContext dslContext;

  public GroupRegisterJooqDao(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  /*
   * WHAT IS NEEDED
   * - Get species name by id
   * select name from species where monId = id
   *
   * - Get move id by name
   * select id from moves where name = moveName
   *
   * - Get species with a given move not egg (maybe filter out preevos)
   * select monId from learnsets where moveId = id and eggMove = false
   *
   * - Get compatible species
   * select monId from compatibility inner join species
   * on species.group1Id = compatibility.group1Id or species.group2Id = compatibility.group1Id
   * where exists (select * from species inner join compatibility
   *     on species.group1Id = compatibility.group2Id or species.group2Id = compatibility.group2Id
   *     where monId = givenSpeciesId)
   *
   * - Get species given another species and move, compatible with species and has move as egg move
   * select monId from learnsets inner join species where moveId = id and eggMove = true and
   */
}