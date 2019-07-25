package com.api.entityOld;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_role")
public class UsuarioRole implements Serializable {

	private static final long serialVersionUID = 7404162366195840367L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "usuarioId", column = @Column(name = "usuario_id", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)) })
	private UsuarioRoleId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

	/**
	 * @return the id
	 */
	public UsuarioRoleId getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(UsuarioRoleId id) {
		this.id = id;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
