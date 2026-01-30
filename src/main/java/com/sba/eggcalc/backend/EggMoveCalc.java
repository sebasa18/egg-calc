package com.sba.eggcalc.backend;

import com.sba.eggcalc.database.SpeciesEntity;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class EggMoveCalc {

  private final EggMoveDao eggMoveDao;

  public EggMoveCalc(EggMoveDao eggMoveDao) {
    this.eggMoveDao = eggMoveDao;
  }

  List<Integer> findEggMoveChain(Integer speciesId, Integer moveId) {

    if (speciesId == 9999 || speciesId == 10000) return List.of(); // Manaphy/ditto check

    boolean hasLevelMove = !eggMoveDao.eggMoveByMonIdAndMoveId(speciesId, moveId);
    if (hasLevelMove) return List.of(speciesId); // Target has move as level-up move

    SpeciesEntity goalSpeciesEntity = eggMoveDao.getSpeciesByMonId(speciesId);
    Integer goalGroup1 = goalSpeciesEntity.getGroup1Id();

    if (goalGroup1 == 0) {
      // NED check
      return List.of();
    }

    List<Integer> origins = eggMoveDao.getSpeciesWithLevelMove(moveId);

    DefaultMutableTreeNode top = new DefaultMutableTreeNode(speciesId);
    JTree visited = new JTree();

    while (true) {
      HashSet<Integer> rename = new HashSet<>();
      HashSet<Integer> temp = new HashSet<>();
      for (Integer currentSpeciesId : origins) {
        temp.addAll(eggMoveDao.getCompatibleSpecies(currentSpeciesId));
        temp.retainAll(eggMoveDao.getSpeciesWithEggMove(currentSpeciesId));
        rename.addAll(temp);
      }
    }
  }
}
