package br.com.cdb.digitalbank.model.enums;

public enum CardType {
    CREDIT(1, "Cartão de Credito"),
    DEBIT(2, "Cartão de Debito");

    private final String label;
    private final int value;

    CardType(int value, String label) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}
