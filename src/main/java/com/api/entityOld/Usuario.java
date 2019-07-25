package com.api.entityOld;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.api.intercep.SecurityInterceptor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@FilterDef(name = "empresaTenant", parameters = { @ParamDef(name = "id", type = "long") })
@Filter(name = "empresaTenant", condition = "EMPRESA_TENANT_ID = :id")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2198475916729836031L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "empresa_tenant_id")
	private Long empresaTenantId;
	
	@Column(name = "id_usuario_alteracao")
	private Long idUsuarioAlteracao;

	@Column(name = "id_usuario_inclusao")
	private Long idUsuarioInclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criadoEm", nullable = false)
	private Date criadoEm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "alteradoEm")
	private Date alteradoEm;

	@NotBlank(message = "{br.com.makersweb.text.campo.nome.obrigatorio}")
	@Column(nullable = false)
	private String nome;

	@NotBlank(message = "{br.com.makersweb.text.campo.email.obrigatorio}")
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull(message = "{br.com.raticket.text.campo.status.obrigatorio}")
	@Column(nullable = false)
	private Boolean ativo;

	@NotEmpty
	@Column(name = "password")
	@ColumnTransformer(write = "SHA2(?, 256)")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;

	@PrePersist
	@PreUpdate
	private void empresaTenantId() {
		Long empresaTenantId = SecurityInterceptor.getEmpresaTenantId();
		if (empresaTenantId != null) {
			this.empresaTenantId = empresaTenantId;
		}
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpresaTenantId() {
		return empresaTenantId;
	}

	public void setEmpresaTenantId(Long empresaTenantId) {
		this.empresaTenantId = empresaTenantId;
	}

	public Long getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(Long idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

	public Long getIdUsuarioInclusao() {
		return idUsuarioInclusao;
	}

	public void setIdUsuarioInclusao(Long idUsuarioInclusao) {
		this.idUsuarioInclusao = idUsuarioInclusao;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Date getAlteradoEm() {
		return alteradoEm;
	}

	public void setAlteradoEm(Date alteradoEm) {
		this.alteradoEm = alteradoEm;
	}

}
