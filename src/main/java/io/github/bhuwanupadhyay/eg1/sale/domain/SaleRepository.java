package io.github.bhuwanupadhyay.eg1.sale.domain;

import io.github.bhuwanupadhyay.eg1.core.DataRepository;

import java.util.List;

public interface SaleRepository extends DataRepository<Sale, Long> {

    List<Sale> findAllDelivered();

    List<Sale> findAllCancelled();
}
