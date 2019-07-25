package com.api.entityOld;
// Generated 15/04/2017 17:47:02 by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioRoleId implements java.io.Serializable {

	private static final long serialVersionUID = -385979399493204778L;

	@Column(name = "usuario_id", nullable = false)
	private long usuarioId;

	@Column(name = "role_id", nullable = false)
	private long roleId;

	/**
	 * @return the usuarioId
	 */
	public long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId
	 *            the usuarioId to set
	 */
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the roleId
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
