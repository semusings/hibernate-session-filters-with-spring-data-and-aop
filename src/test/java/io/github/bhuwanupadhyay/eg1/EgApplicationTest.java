package io.github.bhuwanupadhyay.eg1;

import io.github.bhuwanupadhyay.eg1.core.LoggedInUser;
import io.github.bhuwanupadhyay.eg1.sale.application.SaleService;
import io.github.bhuwanupadhyay.eg1.sale.domain.Sale;
import io.github.bhuwanupadhyay.eg1.sale.domain.SaleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class EgApplicationTest {

	@MockBean
	private LoggedInUser loggedInUser;

	@Autowired
	private SaleService saleService;

	@Autowired
	private SaleRepository repository;

	@Test
	void canFiltersByCreatedBy() {

		when(loggedInUser.userId()).thenReturn("system");
		Sale sale = saleService.placeOrder("#1", 20);
		saleService.cancelOrder(sale.getId());

		when(loggedInUser.userId()).thenReturn("sys");
		List<Sale> cancelled = repository.findAllCancelled();
		assertEquals(0, cancelled.size());

		when(loggedInUser.userId()).thenReturn("system");
		List<Sale> cancelled2 = repository.findAllCancelled();
		assertEquals(1, cancelled2.size());

	}

}