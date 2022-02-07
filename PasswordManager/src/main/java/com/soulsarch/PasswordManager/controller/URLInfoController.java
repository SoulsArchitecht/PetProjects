package com.soulsarch.PasswordManager.controller;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.service.URLInfoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class URLInfoController implements ApplicationContextAware {
    @Autowired
    private URLInfoService service;

    private ApplicationContext context;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<URLInformation> infoList = service.infoList();
        model.addAttribute("infoList", infoList);
        return "index";
    }

    @RequestMapping("/new")
    public String showCreatingNewURLInfoForm(Model model) {
        URLInformation urlInformation = new URLInformation();
        model.addAttribute("urlInformation", urlInformation);

        return "new_urlInformation";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveURLInfo(@ModelAttribute("urlInformation") URLInformation urlInformation) {
        service.save(urlInformation);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditURLInfoForm(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit_urlInformation");

        URLInformation urlInformation = service.get(id);
        modelAndView.addObject("urlInformation", urlInformation);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteURLInfo(@PathVariable(name = "id") int id) {
        service.delete(id);

        return "redirect:/";
    }

    @RequestMapping("/search")
    @PreAuthorize("hasAuthority('user:write')")
    public ModelAndView search (@RequestParam String keyword) {
        List<URLInformation> result = service.search(keyword);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("result", result);

        return modelAndView;
    }

    @PostMapping("/shutdownContext")
    @PreAuthorize("hasAuthority('user:moderate')")
    public String shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
        return "redirect:about:blank";
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
    }
}
