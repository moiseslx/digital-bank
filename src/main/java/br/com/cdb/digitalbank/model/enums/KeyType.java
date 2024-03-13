package br.com.cdb.digitalbank.model.enums;

public enum KeyType {
    EMAIL(1, "Email"),
    PHONE(2, "Telefone"),
    CPF(3, "CPF"),
    RANDOM(4, "Aleatório");

    private final int value;
    private final String label;

    KeyType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
