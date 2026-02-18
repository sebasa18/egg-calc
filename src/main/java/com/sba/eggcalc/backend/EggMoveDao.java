package com.sba.eggcalc.backend;

import com.sba.eggcalc.database.SpeciesEntity;
import org.jetbrains.annotations.NotNull;
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

  Integer getMonIdByName(@NotNull String speciesName);

  Integer getMoveIdByName(@NotNull String moveName);

  @NotNull
  @Query("SELECT moveId FROM LEARNSETS WHERE monId = :speciesId AND eggMove = TRUE")
  List<Integer> getEggMovesBySpeciesId(@NotNull Integer speciesId);

  @NotNull
  @Query("SELECT monId FROM LEARNSETS WHERE moveId = :moveId AND eggMove = FALSE")
  List<Integer> getSpeciesWithLevelMove(@NotNull Integer moveId);

  @Query("SELECT monId FROM LEARNSETS WHERE moveId = :moveId AND eggMove = TRUE" +
        "AND EXISTS(SELECT * FROM SPECIIES WHERE monId = monId AND canBeF = TRUE")
  // Needs to be F in order to have the move passed onto them
  List<Integer> getSpeciesWithEggMove(@NotNull Integer moveId);

  @Query("SELECT s2.monId FROM SPECIES s1 INNER JOIN SPECIES s2 ON s1.monId = :speciesId AND " +
          "s2.canBeM = TRUE AND" +
          "(s1.group1Id = s2.group1Id OR s1.group1Id = s2.group2Id OR s1.group2Id = s2.group1Id OR s1.group2Id = s2.group2Id)")
  // Needs to be M to pass the move onto a child of a diff species
  List<Integer> getCompatibleSpecies(@NotNull Integer speciesId);

  SpeciesEntity getSpeciesByMonId(@NotNull Integer monId);

  Boolean eggMoveByMonIdAndMoveId(@NotNull Integer monId, @NotNull Integer moveId);

}
