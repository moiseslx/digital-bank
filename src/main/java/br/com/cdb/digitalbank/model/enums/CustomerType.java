package br.com.cdb.digitalbank.model.enums;

public enum CustomerType {

    COMMON,
    SUPER,
    PREMIUM;

    // Retorna a descrição do tipo de cliente
    public String getDescription() {
        return switch (this) {
            case COMMON -> "Cliente Comum";
            case SUPER -> "Cliente Super";
            case PREMIUM -> "Cliente Premium";
            default -> throw new IllegalArgumentException("Invalid customer type: " + this);
        };
    }

    // Retorna a taxa de manutenção do tipo de cliente
    public int getCheckingAccountMaintenanceFee() {
        return switch (this) {
            case COMMON -> 10;
            case SUPER -> 5;
            case PREMIUM -> 0;
            default -> throw new IllegalArgumentException("Invalid customer type: " + this);
        };
    }

    // Retorna a taxa de rendimento do tipo de cliente
    public double getSavingsAccountYield() {
        return switch (this) {
            case COMMON -> 0.01;
            case SUPER -> 0.015;
            case PREMIUM -> 0.02;
            default -> throw new IllegalArgumentException("Invalid customer type: " + this);
        };
    }
}
