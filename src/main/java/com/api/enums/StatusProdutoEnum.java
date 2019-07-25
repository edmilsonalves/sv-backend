package com.api.enums;


public enum StatusProdutoEnum
{

	//NAO ALTERAR A ORDEM DESTA ENUM.
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	EXCLUIDO("Excluido");

	private final String descricao;

	private StatusProdutoEnum (final String desc)
	{
		this.descricao = desc;
	}

	public String getDescricao ()
	{
		return descricao;
	}

	public static StatusProdutoEnum findByName (final String name)
	{
		if (name == null)
		{
			return null;
		}

		for (final StatusProdutoEnum tipoAtributo : StatusProdutoEnum.values())
		{
			if (tipoAtributo.name().equalsIgnoreCase(name))
			{
				return tipoAtributo;
			}
		}

		return null;
	}
	
	public static StatusProdutoEnum findById (final Integer id)
	{
		if (id == null)
		{
			return null;
		}

		for (final StatusProdutoEnum tipo : StatusProdutoEnum.values())
		{
			if (tipo.ordinal() == id)
			{
				return tipo;
			}
		}

		return null;
	}
}
