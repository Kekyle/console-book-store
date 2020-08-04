package com.company.guiapp.action;

import com.company.entity.User;
import com.company.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizationAction implements ActionListener {

    private UserService userService;
    private String login;
    private String pass;

    public AuthorizationAction(UserService userService, String login, String pass) {
        this.userService = userService;
        this.login = login;
        this.pass = pass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User userByLogin = userService.findUserByLogin(login);
        if (userByLogin.getPassword().equals(pass)){
            System.out.println("Auth!");
        }
    }
}
