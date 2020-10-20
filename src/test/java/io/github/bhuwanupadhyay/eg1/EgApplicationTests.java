package io.github.bhuwanupadhyay.eg1;

import io.github.bhuwanupadhyay.eg1.sale.application.SaleService;
import io.github.bhuwanupadhyay.eg1.sale.domain.Sale;
import io.github.bhuwanupadhyay.eg1.sale.domain.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static io.github.bhuwanupadhyay.eg1.sale.domain.Sale.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EgApplicationTests {

	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void canPlaceOrder() {

		Sale result = saleService.placeOrder("#001", 20);

		assertNotNull(result);
		assertEquals("#001", result.getItemId());
		assertEquals(20, result.getQuantity());
		assertEquals(Status.INITIAL, result.getStatus());
	}

	@Test
	void canCancelOrder() {

		Sale result = saleService.placeOrder("#001", 20);

		saleService.cancelOrder(result.getId());

		Optional<Sale> one = repository.findOne(result.getId());

		assertEquals(Status.CANCELLED, one.get().getStatus());

	}

}
