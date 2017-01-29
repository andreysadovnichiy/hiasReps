package com.solveast.rreps.config;

import com.solveast.rreps.account_manager.Account;
import com.solveast.rreps.account_manager.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Андрей on 29.01.2017.
 */
//@Component
public class AccountResolver implements HandlerMethodArgumentResolver {
    private Account account;
//    @Autowired
//    private SecurityRepository repository;

    public AccountResolver() {
    }

    public AccountResolver(Account account) {
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Account.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        Account admin = repository.findOneByLogin("admin");
//        return admin;
        return new Account(1l, "login", "password", "role", false);
    }
}
