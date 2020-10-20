package io.github.bhuwanupadhyay.eg1.sale.infrastructure;

import io.github.bhuwanupadhyay.eg1.sale.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static io.github.bhuwanupadhyay.eg1.sale.domain.Sale.Status;

interface JpaSaleRepository extends JpaRepository<Sale, Long> {

	List<Sale> findAllByStatus(Status status);

}
