package com.api.enums;

public enum ErrorCode {

	SIS_1("SIS-1", "Parametros inválidos."),
	SIS_2("SIS-2", "Registro não encontrado."),
	SIS_3("SIS-3", "O campo {0} obrigatório."),
	SIS_4("SIS-4", "O campo {0} não é válido ({1})."),
	SIS_5("SIS-5", "Esse registro está vinculado e não pode ser excluído."),
	SIS_6("SIS-6", "Esse código busca já está sendo utilizado em outro produto!."),
	
	EXP_1("EXP-1", "Ocorreu um erro desconhecido."),
	EXP_2("EXP_2", "Ocorreu um erro de comunicação em nossos serviços."),
	
	
	EMAIL_1("EMAIL_1", "O modelo fornecido não nulo, vazio ou em branco.");
	
	

	private final String codigo;
	private final String mensagem;

	private ErrorCode (final String codigo, final String mensagem)
	{
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public static ErrorCode findByCodigo (final String codigo)
	{
		if (codigo == null)
		{
			return null;
		}

		for (final ErrorCode errorCode : ErrorCode.values())
		{
			if (errorCode.getCodigo().equalsIgnoreCase(codigo))
			{
				return errorCode;
			}
		}

		return null;

	}

	@Override
	public String toString ()
	{
		return codigo + ": " + mensagem;
	}

	public String getCodigo ()
	{
		return codigo;
	}

	public String getMensagem ()
	{
		return mensagem;
	}

}
