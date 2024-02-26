package br.com.cdb.digitalbank.model.enums;

public enum CustomerType {
    COMMON("Comum", 1),
    SUPER("Super", 2),
    PREMIUM("Premium", 3);

    private final String label;
    private final int value;

    CustomerType(String label, int value) {
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
    public static CustomerType fromValue(int value) {
        for (CustomerType type : CustomerType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CustomerType value: " + value);
    }

    // Método para obter o tipo de cliente a partir do rótulo
    public static CustomerType fromLabel(String label) {
        for (CustomerType type : CustomerType.values()) {
            if (type.getLabel().equalsIgnoreCase(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CustomerType label: " + label);
    }
}
