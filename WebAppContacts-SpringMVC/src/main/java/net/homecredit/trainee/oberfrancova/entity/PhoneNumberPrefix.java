package net.homecredit.trainee.oberfrancova.entity;

public enum PhoneNumberPrefix {

    CZ("+420"),
    SK("+421");

    private String prefix;

    PhoneNumberPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
