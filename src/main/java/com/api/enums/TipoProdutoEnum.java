package com.api.enums;


public enum TipoProdutoEnum
{

	//NAO ALTERAR A ORDEM DESTA ENUM.
	PRODUTO("Produto"),
	SERVICO("Serviço");

	private final String descricao;

	private TipoProdutoEnum (final String desc)
	{
		this.descricao = desc;
	}

	public String getDescricao ()
	{
		return descricao;
	}

	public static TipoProdutoEnum findByName (final String name)
	{
		if (name == null)
		{
			return null;
		}

		for (final TipoProdutoEnum tipoAtributo : TipoProdutoEnum.values())
		{
			if (tipoAtributo.name().equalsIgnoreCase(name))
			{
				return tipoAtributo;
			}
		}

		return null;
	}
	
	public static TipoProdutoEnum findById (final Integer id)
	{
		if (id == null)
		{
			return null;
		}

		for (final TipoProdutoEnum tipo : TipoProdutoEnum.values())
		{
			if (tipo.ordinal() == id)
			{
				return tipo;
			}
		}

		return null;
	}
}
