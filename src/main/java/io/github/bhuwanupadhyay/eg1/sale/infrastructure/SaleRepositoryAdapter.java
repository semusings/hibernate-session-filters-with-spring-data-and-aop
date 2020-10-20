package io.github.bhuwanupadhyay.eg1.sale.infrastructure;

import io.github.bhuwanupadhyay.eg1.sale.domain.Sale;
import io.github.bhuwanupadhyay.eg1.sale.domain.Sale.Status;
import io.github.bhuwanupadhyay.eg1.sale.domain.SaleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class SaleRepositoryAdapter implements SaleRepository {

	private final JpaSaleRepository repository;

	public SaleRepositoryAdapter(JpaSaleRepository repository) {
		this.repository = repository;
	}

	@Override
	public Sale save(Sale entity) {
		return repository.save(entity);
	}

	@Override
	public Optional<Sale> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Sale> findAllDelivered() {
		return repository.findAllByStatus(Status.DELIVERED);
	}

	@Override
	public List<Sale> findAllCancelled() {
		Sale probe = new Sale();
		probe.setStatus(Status.CANCELLED);
		return repository.findAll(Example.of(probe, ExampleMatcher.matching().withIgnoreNullValues()));
	}

}
