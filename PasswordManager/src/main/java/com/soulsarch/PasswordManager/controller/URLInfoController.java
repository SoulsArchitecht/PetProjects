package com.soulsarch.PasswordManager.controller;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.service.URLInfoService;
import com.soulsarch.PasswordManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class URLInfoController {

    private final URLInfoService service;

    private final UserService userService;

    @Autowired
    public URLInfoController(URLInfoService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "home";
    }

    @RequestMapping("/viewUrlTable")
    public String viewHomePage(Model model, @AuthenticationPrincipal User user) {
        List<URLInformation> infoList = userService.getListByUser(user);
        model.addAttribute("infoList", infoList);
        return "index";
    }

    @RequestMapping("/new")
    //@PreAuthorize("hasAnyAuthority('urlinformation:read', 'urlinformation:write', 'urlinformation:moderate')")
    public String showCreatingNewURLInfoForm(Model model, @AuthenticationPrincipal User user, SessionStatus sessionStatus) {
        URLInformation urlInformation = new URLInformation();
        urlInformation.setUser(user);
        model.addAttribute("urlInformation", urlInformation);
        sessionStatus.setComplete();
        return "new_urlInformation";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    //@PreAuthorize("hasAnyAuthority('urlinformation:read', 'urlinformation:write', 'urlinformation:moderate')")
    public String saveURLInfo(@ModelAttribute("urlInformation") URLInformation urlInformation,
                              @AuthenticationPrincipal User user, SessionStatus sessionStatus) {
        urlInformation.setUser(user);
        service.save(urlInformation);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    //@PreAuthorize("hasAnyAuthority('urlinformation:read', 'urlinformation:write', 'urlinformation:moderate')")
    public ModelAndView showEditURLInfoForm(@PathVariable(name = "id") int id, @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("edit_urlInformation");

        URLInformation urlInformation = service.get(id);
        //urlInformation.setUser(user);
        modelAndView.addObject("urlInformation", urlInformation);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    //@PreAuthorize("hasAnyAuthority('urlinformation:read', 'urlinformation:write', 'urlinformation:moderate')")
    public String deleteURLInfo(@PathVariable(name = "id") int id, @AuthenticationPrincipal User user) {
        service.delete(id);

        return "redirect:/";
    }

    @RequestMapping("/search")
    //@PreAuthorize("hasAnyAuthority('urlinformation:read', 'urlinformation:write', 'urlinformation:moderate')")
    public ModelAndView search (@RequestParam String keyword, @AuthenticationPrincipal User user) {
        List<URLInformation> result = service.search(keyword);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("result", result);

        return modelAndView;
    }
}
