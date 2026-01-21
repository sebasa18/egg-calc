package com.sba.eggcalc.backend;

import java.util.List;

public class EggMoveCalc {

  private final EggMoveDao eggMoveDao;

  public EggMoveCalc(EggMoveDao eggMoveDao) {
    this.eggMoveDao = eggMoveDao;
  }

  List<String> findEggMoveChain(Integer speciesId, Integer moveId) {

    List<Integer> origins = eggMoveDao.getLearnsetMons(moveId);
    List<Integer> visited = List.of();

    for (Integer origin : origins) {
      List<Integer> compatibles = eggMoveDao.getCompatibleMons(speciesId);
      if (compatibles.contains(origin)) {
        // found a valid origin
      }
    }

    return List.of("Move1", "Move2", "Move3");
  }

  private boolean searchChain(Integer currentMon, Integer targetMon, List<Integer> visited) {
    if (currentMon.equals(targetMon)) {
      return true;
    }
    visited.add(currentMon);
    List<Integer> compatibles = eggMoveDao.getCompatibleMons(currentMon);
    for (Integer nextMon : compatibles) {
      if (!visited.contains(nextMon)) {
        if (searchChain(nextMon, targetMon, visited)) {
          return true;
        }
      }
    }
    return false;
  }
}
