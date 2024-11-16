package com.backend_sat;

public abstract class Constantes {
    public static final String NOME_CACHE = "taxas";
    
    public static final String OPERACAO_AGENDAR = "Agendar transferência";
    public static final String OPERACAO_LISTAR = "Criar extrato de transferências do sistema";
    
    public static final String MENSAGEM_TAXA_NAO_APLICAVEL = "Taxa não aplicável para o dia de transferência";
    public static final String MENSAGEM_ERRO_DB = "Erro ao acessar o banco de dados";
    public static final String MENSAGEM_ERRO_INESPERADO = "Erro inesperado ao calcular taxa de transferência";

    public static final String HTTP_STATUS_OK = "200";
    public static final String HTTP_STATUS_CREATED = "201";
    public static final String HTTP_STATUS_UNPROCESSABLE_ENTITY= "422";
    public static final String HTTP_STATUS_SERVER_ERROR = "500";

    public static final String MEDIA_TYPE = "application/json";
    public static final String DESCRICAO_AGENDAR = "Transferência agendada com sucesso";
    public static final String DESCRICAO_AGENDAR_ERRO = "Transferência não pôde ser agendada pois ocorreu um erro. Entre em contato se o problema persistir. ";
    public static final String DESCRICAO_AGENDAR_NAO_PROCESSAVEL = "Transferência não pode ser agendada pois os valores inseridos não atendem ao critério esperado";

    public static final String DESCRICAO_AGENDAMENTOS_LISTADOS = "Lista de agendamentos gerada com sucesso";
}
