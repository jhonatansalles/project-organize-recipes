package br.com.vibbra.organizerecipes.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
	
	@JsonIgnore
	@Column(name = "user_last_change")
	private String userLastchange;

	@JsonIgnore
	@Column(name = "date_last_change")
	private LocalDateTime dateLastChange;

	@JsonIgnore
	@Version
	private Long version = 0L;

	@PreUpdate
	private void preUpdate() {
		dateLastChange = LocalDateTime.now();
	}
	
	@PrePersist
	private void preInsert() {
		dateLastChange = LocalDateTime.now();
	}
	
	@PreRemove
	private void preRemove() {
		dateLastChange = LocalDateTime.now();
	}
	
	public Long getId() {
		throw new UnsupportedOperationException();
	}
}
