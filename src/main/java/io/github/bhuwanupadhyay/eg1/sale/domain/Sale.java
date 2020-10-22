package io.github.bhuwanupadhyay.eg1.sale.domain;

import io.github.bhuwanupadhyay.eg1.core.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Sale extends AbstractEntity {

	private String itemId;

	private Integer quantity;

	private Status status;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {

		INITIAL, DELIVERED, CANCELLED

	}

}
