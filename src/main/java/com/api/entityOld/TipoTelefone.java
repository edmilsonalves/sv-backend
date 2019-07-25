package com.api.entityOld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tipo_telefone")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipoTelefone extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6886548443858927094L;

    @Column(name = "descricao", nullable = false, length = 45)
    private String descricao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoTelefone")
    private List<Telefone> telefoneList = new ArrayList<Telefone>();

    public String getDescricao() {
	return this.descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public List<Telefone> getTelefoneList() {
	return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
	this.telefoneList = telefoneList;
    }

}
