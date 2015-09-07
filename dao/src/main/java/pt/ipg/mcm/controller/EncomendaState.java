package pt.ipg.mcm.controller;

public interface EncomendaState {
    int AGUARDA_CONFIRMACAO_PADEIRO = 1,
            AGUARDA_ENTREGA = 2,
            CANCELADO_INCOMP_ENTREGA = 3,
            CANCELADO_CLIENTE = 4,
            ENTREGA_CONFIRMADA_PADEIRO = 5,
            ENTREGA_CONFIRMADA_CLIENTE = 6,
            REJEICAO_ENTREGA = 7;
}
