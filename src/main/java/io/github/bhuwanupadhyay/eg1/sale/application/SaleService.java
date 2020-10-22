package io.github.bhuwanupadhyay.eg1.sale.application;

import io.github.bhuwanupadhyay.eg1.core.LoggedInUser;
import io.github.bhuwanupadhyay.eg1.sale.domain.Sale;
import io.github.bhuwanupadhyay.eg1.sale.domain.Sale.Status;
import io.github.bhuwanupadhyay.eg1.sale.domain.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

	private final SaleRepository repository;

	private final LoggedInUser loggedInUser;

	public SaleService(SaleRepository repository, LoggedInUser loggedInUser) {
		this.repository = repository;
		this.loggedInUser = loggedInUser;
	}

	public Sale placeOrder(String itemId, Integer quantity) {
		Sale sale = new Sale();
		sale.setItemId(itemId);
		sale.setQuantity(quantity);
		sale.setStatus(Status.INITIAL);
		sale.setCreatedBy(loggedInUser.userId());
		return repository.save(sale);
	}

	public void cancelOrder(Long id) {
		Sale sale = findByIdOrFail(id);
		sale.setStatus(Status.CANCELLED);
		repository.save(sale);
	}

	public void deliverOrder(Long id) {
		Sale sale = findByIdOrFail(id);
		sale.setStatus(Status.DELIVERED);
		repository.save(sale);
	}

	private Sale findByIdOrFail(Long id) {
		return repository.findOne(id).orElseThrow(() -> new IllegalArgumentException("not fount!"));
	}

}
