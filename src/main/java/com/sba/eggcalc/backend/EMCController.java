package com.sba.eggcalc.backend;

import org.springframework.http.ResponseEntity;

public class EMCController {  // implements generated api class

  private final EggMoveCalc eggMoveCalc;

  public EMCController(EggMoveCalc eggMoveCalc) {
    this.eggMoveCalc = eggMoveCalc;
  }

  //@Override
  public ResponseEntity<EggChainResponse> getEggMoveChain(
      Integer speciesId,
      Integer moveId
  ) {

    return ResponseEntity.ok(new EggChainResponse());
  }
}
