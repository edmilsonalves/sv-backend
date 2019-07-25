package com.api.entityOld;

public enum StatusPedidoEnum {

	INICIADO("Iniciado"),
	AGUARDANDO_ESPERA("Aguardando / Em espera"),
	SAIU_ENTREGA("Saiu p/ entrega"),
	FINALIZADO_ENTREGUE("Finalizado / ENtregue");

	private String descricao;

	StatusPedidoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
