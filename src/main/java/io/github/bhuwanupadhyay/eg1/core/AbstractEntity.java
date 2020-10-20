package io.github.bhuwanupadhyay.eg1.core;

import org.hibernate.annotations.*;

import javax.persistence.AccessType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@FilterDefs({ @FilterDef(name = "createdByFilter", parameters = { @ParamDef(name = "createdBy", type = "string") }) })
@Filters({ @Filter(name = "createdByFilter", condition = ":createdBy = created_by") })
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String createdBy;

	public Long getId() {
		return id;
	}

	public String getCreatedBy() {
		return createdBy;
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
