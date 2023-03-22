package com.shinmen.relationships.onetomany.unidirectional.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String location;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "tournament_id")
	private List<Registration> registrations = new ArrayList<>();

	// set up one to many relationship
	public void addRegistration(Registration reg) {
		registrations.add(reg);
	}

	// remove registration
	public void removeRegistration(Registration reg) {
		if (registrations != null) {
			registrations.remove(reg);
		}
	}

}
