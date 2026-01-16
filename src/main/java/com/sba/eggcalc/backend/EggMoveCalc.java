package com.sba.eggcalc.backend;

import java.util.List;

public class EggMoveCalc {

  private final EggMoveDao eggMoveDao;

  public EggMoveCalc(EggMoveDao eggMoveDao) {
    this.eggMoveDao = eggMoveDao;
  }

  List<String> findEggMoveChain(Integer speciesId, Integer moveId) {

    return List.of("Move1", "Move2", "Move3");
  }
}
