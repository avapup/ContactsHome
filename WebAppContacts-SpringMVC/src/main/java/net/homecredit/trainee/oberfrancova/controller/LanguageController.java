package net.homecredit.trainee.oberfrancova.controller;

import net.homecredit.trainee.oberfrancova.entity.LocalLanguage;
import net.homecredit.trainee.oberfrancova.services.LocalizationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LanguageController {

    private final LocalizationService localizationService;

    public LanguageController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @RequestMapping(value = "/local/localeOptions.html", method = RequestMethod.GET)
    public ModelAndView showLanguageOptions() {

        ModelAndView modelAndView = new ModelAndView(/*"/WEB-INF/view/local/localeOptions.jsp"*/);
        modelAndView.addObject("localeList", LocalLanguage.values());
        modelAndView.setViewName("/WEB-INF/view/local/localeOptions.jsp");

        return modelAndView;
    }

    @RequestMapping(value = "/contacts/all/locale.html", method = RequestMethod.GET, params = "locale")
    public ModelAndView setCustomLocale(@RequestParam String locale) {

        ModelAndView modelAndView = new ModelAndView();

        final LocalLanguage localeAsEnum = LocalLanguage.findValueByString(locale);


        if (localeAsEnum == null) {

            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            return null;
        }

        localizationService.setUpLocalizedBundle(localeAsEnum);


        modelAndView.setViewName("redirect:/contacts/all.html");

        return modelAndView;
    }


}
