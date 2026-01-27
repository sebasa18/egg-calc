package com.sba.eggcalc.backend;

import com.sba.eggcalc.database.SpeciesEntity;
import org.jetbrains.annotations.NotNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
@Validated
@Transactional(propagation = Propagation.MANDATORY)
public interface EggMoveDao extends CrudRepository<SpeciesEntity, Integer> {

  Integer getMoveIdByName(@NotBlank String moveName);

  @NotNull
  @Query("SELECT moveId FROM LEARNSETS WHERE monId = :speciesId AND eggMove = TRUE")
  List<Integer> getEggMovesBySpeciesId(@NotNull Integer speciesId);

  @NotNull
  @Query("SELECT monId FROM LEARNSETS WHERE moveId = :moveId AND eggMove = FALSE AND NOT EXISTS" +
          "(select * from SPECIES where monId=LEARNSETS.monId and group1Id=0)")
  List<Integer> getSpeciesWithLevelMove(@NotNull Integer moveId);

  @Query("SELECT s2.monId FROM SPECIES s1 INNER JOIN SPECIES s2 ON s1.monId = :speciesId AND " +
          "(s1.group1Id = s2.group1Id OR s1.group1Id = s2.group2Id OR s1.group2Id = s2.group1Id OR s1.group2Id = s2.group2Id)")
  List<Integer> getCompatibleSpecies(@NotNull Integer speciesId);


  /*
   * WHAT REMAINS TO IMPLEMENT
   * - Get species given another (list of?) species and move, compatible with species and has move as egg move (step of search)
   * select monId from learnsets inner join species on moveId = id and eggMove = true and
   *
   */
}