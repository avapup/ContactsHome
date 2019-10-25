package net.homecredit.trainee.oberfrancova.entity;

import java.util.Locale;

public enum LocalLanguage {

    EN("en"),
    CZ("cs"),
    SK("sk"),
    DE("de");

    private String locale;

    LocalLanguage(String locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return Locale.forLanguageTag(locale);
    }

    @Override
    public String toString() {
        return locale;
    }

    public static LocalLanguage findValueByString(String name) {
        for (LocalLanguage enumElement : LocalLanguage.values()){
            if (enumElement.locale.equalsIgnoreCase(name)) {
                return enumElement;
            }
        }

        return null;
    }

}
