package com.sba.eggcalc.backend;

import org.springframework.http.ResponseEntity;

import java.util.List;

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
    List<String> result = eggMoveCalc.findEggMoveChain(speciesId, moveId);
    return ResponseEntity.ok(new EggChainResponse());
  }
}
