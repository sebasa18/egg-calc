package com.sba.eggcalc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.sba.eggcalc.backend.EggMoveDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PokemonEggCalcApplicationTests {

	@Autowired private EggMoveDao eggMoveDao;

	PokemonEggCalcApplicationTests(EggMoveDao eggMoveDao) {
		this.eggMoveDao = eggMoveDao;
	}

	@Test
	@Sql("classpath:move_requests.sql")
	void getMoveIdByName_whenGivenAppropriateName_thenReturnsMatchingMove() {
		var result = eggMoveDao.getMoveIdByName("Scratch");
		assertThat(result).isEqualTo(2);
	}

}
