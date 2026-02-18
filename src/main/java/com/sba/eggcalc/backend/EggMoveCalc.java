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
    if (speciesId == 100) return List.of(speciesId);  // Smeargle check

    boolean hasLevelMove = !eggMoveDao.eggMoveByMonIdAndMoveId(speciesId, moveId);
    if (hasLevelMove) return List.of(speciesId); // Target has move as level-up move

    SpeciesEntity goalSpeciesEntity = eggMoveDao.getSpeciesByMonId(speciesId);
    Integer goalGroup1 = goalSpeciesEntity.getGroup1Id();

    if (goalGroup1 == 0) {
      // NED check
      return List.of();
    }

    if (goalGroup1 == 1) {
      // Field/Smeargle check
      return List.of(speciesId, 100);
    }

    DefaultMutableTreeNode top = new DefaultMutableTreeNode(speciesId);
    JTree visited = new JTree();

    HashSet<Integer> current = Set.of(eggMoveDao.getCompatibleSpecies(speciesId));
    HashSet<Integer> level = eggMoveDao.getSpeciesWithLevelMove(moveId);
    HashSet<Integer> egg = eggMoveDao.getSpeciesWithEggMove(moveId);

    while (current.intersection(level).isEmpty()) {
	for (Integer id : current) {
	    // TODO: make a new hashset from the species compatible with each entry in current.  Somehow make a tree of it as you go?
        }
    }
  }
}
