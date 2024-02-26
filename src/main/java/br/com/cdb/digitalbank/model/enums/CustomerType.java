package br.com.cdb.digitalbank.model.enums;

public enum CustomerType {

    COMMON,
    SUPER,
    PREMIUM;

    public String getDescription() {
        return switch (this) {
            case COMMON -> "Cliente Comum";
            case SUPER -> "Cliente Super";
            case PREMIUM -> "Cliente Premium";
        };
    }
}
