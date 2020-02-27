package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Edmilson
 *
 */
@Entity
@Table(name = "sc_file")
@Getter
@Setter
public class ScFile implements Serializable {

	private static final long serialVersionUID = -1920000384598796574L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "type", length = 45, nullable = false)
	private String type;

	@Column(name = "id_pai")
	private Long idPai;

	@Column(name = "file", nullable = false)
	private byte[] file;

}
