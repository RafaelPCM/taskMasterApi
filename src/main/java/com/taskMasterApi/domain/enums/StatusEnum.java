package com.taskMasterApi.domain.enums;


public enum StatusEnum {
    TODO("TODO", "Para fazer"),
    IN_PROGRESS("IN_PROGRESS", "Fazendo"),
    COMPLETED("COMPLETED", "Completo"),
    PAUSED("PAUSED", "Pausado");

    private final String codigo;
    private final String descricao;

    StatusEnum(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEnum fromString(String codigo) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.codigo.equalsIgnoreCase(codigo)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status desconhecido: " + codigo);
    }
}
