package projeto.centroOperacoes.enums;
public enum StatusEnum {
	
	INATIVO(0, "Inativo"),
	ATIVO(1, "Ativo");
	
	
	private final Integer codigo;
	private final String descricao;
	
	
	private StatusEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	public static StatusEnum obterPorCodigo(Integer cod) {
		if (cod == 0) {
			return null;
		}

		for (StatusEnum e : StatusEnum.values()) {
			if (e.codigo == cod) {
				return e;
			}
		}
		return null;
	}
	
	public static StatusEnum obterPorCodigo(String cod) {
		if (cod.equals("Inativo")) {
			return null;
		}

		for (StatusEnum e : StatusEnum.values()) {
			if (e.descricao.equals(cod)) {
				return e;
			}
		}
		return null;
	}

	

}