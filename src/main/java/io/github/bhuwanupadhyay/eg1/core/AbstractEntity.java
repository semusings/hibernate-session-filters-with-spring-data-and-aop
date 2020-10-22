package io.github.bhuwanupadhyay.eg1.core;

import org.hibernate.annotations.*;

import javax.persistence.AccessType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// @formatter:on
@FilterDefs({
	@FilterDef(
		name = AbstractEntity.CREATED_BY_FILTER,
		parameters = { @ParamDef(name = AbstractEntity.CREATED_BY_PARAM, type = "string") })
})
@Filters({
	@Filter(
		name = AbstractEntity.CREATED_BY_FILTER,
		condition = ":" + AbstractEntity.CREATED_BY_PARAM + " = " + AbstractEntity.CREATED_BY_COLUMN)
})
// @formatter:off
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity implements Serializable {

	public static final String CREATED_BY_FILTER = "createdByFilter";

	public static final String CREATED_BY_PARAM = "createdBy";

	public static final String CREATED_BY_COLUMN = "created_by";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = CREATED_BY_COLUMN)
	private String createdBy;

	public Long getId() {
		return id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AbstractEntity that = (AbstractEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
