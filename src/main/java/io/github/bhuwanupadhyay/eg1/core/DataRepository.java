package io.github.bhuwanupadhyay.eg1.core;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface DataRepository<T, ID> {

	T save(T entity);

	Optional<T> findOne(ID id);

}
