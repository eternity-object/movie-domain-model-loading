package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.EntityManager;
import org.eternity.domainmodel.generic.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
public class JpaLoadingTest {
	@Autowired
	private EntityManager em;

	@Test
	public void one_to_one_loading() {
		Movie movie = new Movie("한산", 120, Money.wons(10000));
		Screening screening = new Screening(movie, 1, LocalDateTime.of(2024, 12, 9, 9, 0));

		em.persist(screening);
		em.flush();
		em.clear();

		Screening loadedScreening = em.find(Screening.class, screening.getId());
		assertThat(loadedScreening.getFixedFee()).isEqualTo(Money.wons(10000));
	}
}