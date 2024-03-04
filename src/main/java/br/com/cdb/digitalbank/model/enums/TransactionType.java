package br.com.cdb.digitalbank.model.enums;

public enum TransactionType {

    CREDIT("Crédito", 1),
    DEBIT("Débito", 2),
    PIX("PIX", 3),
    TED("TED", 4),
    BETWEEN("Entre contas", 5);

    private final String label;
    private final int value;

    TransactionType(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public static TransactionType fromValue(int value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TransactionType value: " + value);
    }
}
