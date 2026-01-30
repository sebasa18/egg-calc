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
  @Query("SELECT monId FROM LEARNSETS WHERE moveId = :moveId AND eggMove = FALSE AND NOT EXISTS" +
          "(select * from SPECIES where monId=LEARNSETS.monId and group1Id=0)")
  List<Integer> getSpeciesWithLevelMove(@NotNull Integer moveId);

  @Query("SELECT monId FROM LEARNSETS INNER JOIN SPECIES ON moveId = :moveId AND eggMove = TRUE")
  List<Integer> getSpeciesWithEggMove(@NotNull Integer moveId);

  @Query("SELECT s2.monId FROM SPECIES s1 INNER JOIN SPECIES s2 ON s1.monId = :speciesId AND " +
          "(s1.group1Id = s2.group1Id OR s1.group1Id = s2.group2Id OR s1.group2Id = s2.group1Id OR s1.group2Id = s2.group2Id)")
  List<Integer> getCompatibleSpecies(@NotNull Integer speciesId);

  SpeciesEntity getSpeciesByMonId(@NotNull Integer monId);

  Boolean eggMoveByMonIdAndMoveId(@NotNull Integer monId, @NotNull Integer moveId);

  // Redo all methods to get species going outwards instead of inwards, looking for ones sharing exactly one egg group  // At least one is probably fine so long as I filter out dupes
  // Given a mon and a move, gets everyone that shares at least one egg group with the mon and has the move as an egg move
  //

  // get mon details given id
  // Could still need single-group mons if the chain between two groups can't breed with target mon
  // Don't see this happening outside the target's group though

  // Preemptive checks
  // If the target mon has the move as a level-up move, return empty list
  // Target is not manaphy, ditto or an NED
  // If target is in Field grup, return smeargle

}