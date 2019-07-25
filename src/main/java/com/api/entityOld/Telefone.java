package com.api.entityOld;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "telefone")
public class Telefone extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7734762898933949608L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(Include.NON_NULL)
    @JoinColumn(name = "tipo_telefone_id", nullable = false)
    private TipoTelefone tipoTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(Include.NON_NULL)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "numero", length = 45)
    private String numero;

    public TipoTelefone getTipoTelefone() {
	return this.tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
	this.tipoTelefone = tipoTelefone;
    }

    public Cliente getCliente() {
	return this.cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public String getNumero() {
	return this.numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

}
