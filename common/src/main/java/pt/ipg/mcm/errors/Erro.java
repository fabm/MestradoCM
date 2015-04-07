package pt.ipg.mcm.errors;

public enum Erro {
  TECNICO(-1, "Problema técnico"),
  CAMPO_VAZIO(-2, "O campo ''{0}'' não pode ser vazio"),
  PRODUTO_NAO_ENCONTRADO(-3, "Produto não encontrado {0}"),
  CATEGORIA_NAO_ENCONTRADO(-3, "Categoria não encontrado {0}"),
  FORMATO_INVALIDO(-4, "O Campo {0} é inválido"),
  APENAS_UM_FICHEIRO(-5, "Apenas um ficheiro deve ser carregado"),
  SEM_FICHEIROS(-6, "Nenhum ficheiro para fazer upload"),
  PADEIRO_NAO_ENCONTRADO(-7, "Padeiro não Encontrado "),
  LOGIN_JA_EXISTENTE(-8, "O login {0} não está disponivel");

  private final int codigo;
  private final String mensagem;

  Erro(int codigo, String mensagem) {

    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getMensagem() {
    return mensagem;
  }
}
