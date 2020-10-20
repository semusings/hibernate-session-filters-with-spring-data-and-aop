package io.github.bhuwanupadhyay.eg1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Aspect
@Component
public class RepositoryCreatedByAdvisor {

	public static final String DEFAULT = "DEFAULT";
	private static final Logger LOG = LoggerFactory.getLogger(RepositoryCreatedByAdvisor.class);
	@PersistenceContext
	private final EntityManager entityManager;

	public RepositoryCreatedByAdvisor(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Pointcut("execution(* io.github.bhuwanupadhyay.eg1.core.DataRepository+.*(..))"
			+ "&& ! execution(* io.github.bhuwanupadhyay.eg1.core.DataRepository+.save(..))")
	public void createdByFilter() {
	}

	@Around(value = "createdByFilter()")
	public Object createdByFilter(ProceedingJoinPoint joinPoint) throws Throwable {

		Session session = entityManager.unwrap(Session.class);

		boolean hasSession = Objects.nonNull(session);

		if (hasSession) {
			LOG.debug("created by filter enabled with createdBy: {}", DEFAULT);
			Filter createdByFilter = session.enableFilter("createdByFilter");
			createdByFilter.setParameter("createdBy", DEFAULT);
		}

		Object obj = joinPoint.proceed();

		if (hasSession) {
			session.disableFilter("createdByFilter");
		}

		return obj;
	}

}
