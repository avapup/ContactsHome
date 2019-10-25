package net.homecredit.trainee.oberfrancova.services;

import net.homecredit.trainee.oberfrancova.entity.LocalLanguage;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class LocalizationService {

    private ResourceBundle localizedBundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag("en"));

    public void setUpLocalizedBundle(LocalLanguage language) {

        localizedBundle = ResourceBundle.getBundle("messages", language.getLocale());

    }

    public String getLocalizedStringFromBundleByKey(String key) {
        if (!localizedBundle.containsKey(key)) {
            return null;
        }

        return new String(localizedBundle.getString(key).getBytes(), StandardCharsets.UTF_8);
    }


}
