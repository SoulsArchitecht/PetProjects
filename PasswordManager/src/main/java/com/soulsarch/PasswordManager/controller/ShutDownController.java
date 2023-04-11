package com.soulsarch.PasswordManager.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;

public class ShutDownController implements ApplicationContextAware {
    private ApplicationContext context;

    @PostMapping("/shutdownContext")
    @PreAuthorize("hasAuthority('urlInformation:moderate')")
    public String shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
        return "redirect:about:blank";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
