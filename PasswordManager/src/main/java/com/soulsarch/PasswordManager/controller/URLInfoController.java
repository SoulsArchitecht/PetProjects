package com.soulsarch.PasswordManager.controller;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.service.URLInfoService;
import com.soulsarch.PasswordManager.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class URLInfoController {

    private URLInfoService service;

    private UserService userService;

    @Autowired
    public URLInfoController(URLInfoService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    /*    private ApplicationContext context;*/

    @GetMapping("/")
    public String viewHomePage() {
        return "home";
    }

/*    @GetMapping("/")
    public String viewHomePage(Model model, @AuthenticationPrincipal User user) {
        //List<URLInformation> infoList = user.getUrlInformationList();
        List<URLInformation> infoList = userService.getListByUser(user);
        model.addAttribute("infoList", infoList);
        System.out.println(infoList.toString());
        return "index";
    }*/

    @RequestMapping("/")
    public String viewHomePage(Model model, @AuthenticationPrincipal User user) {
        //Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        //SecurityContextHolder.getContext().setAuthentication(auth);
        //RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT", auth, RequestAttributes.SCOPE_SESSION);
        //List<URLInformation> infoList = user.getUrlInformationList();
        //List<URLInformation> infoList = List.copyOf(user.getUrlInformationList());
        //List<URLInformation> infoList = service.infoList();
        List<URLInformation> infoList = userService.getListByUser(user);

        model.addAttribute("infoList", infoList);
        //System.out.println(infoList.toString());

        return "index";
    }

/*    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@PostMapping
    //@PreAuthorize("hasAnyAuthority('urlinformation:read', 'urlinformation:write', 'urlinformation:moderate')")
    public String viewHomePage(Model model, @AuthenticationPrincipal User user) {
        //urlInformation.setUser(user);
        List<URLInformation> infoList = user.getUrlInformationList();
        //List<URLInformation> infoList = service.infoList();
        model.addAttribute("infoList", infoList);
        System.out.println(infoList.toString());
        return "index";
    }*/

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

/*    @PostMapping("/shutdownContext")
    @PreAuthorize("hasAuthority('urlInformation:moderate')")
    public String shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
        return "redirect:about:blank";
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
    }*/
}
