package br.com.cdb.digitalbank.model.enums;

public enum AccountType {
    CHECKING(1, "Corrente"),
    SAVINGS(2, "Poupança");

    private final String label;
    private final int value;

    AccountType(int value, String label) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    // Método para obter o tipo de cliente a partir do valor
    public static AccountType fromValue(int value) {
        for (AccountType type : AccountType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AccountType value: " + value);
    }

    // Método para obter o tipo de cliente a partir do rótulo
    public static AccountType fromLabel(String label) {
        for (AccountType type : AccountType.values()) {
            if (type.getLabel().equalsIgnoreCase(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AccountType label: " + label);
    }
}
