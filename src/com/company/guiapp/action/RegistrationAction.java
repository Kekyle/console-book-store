package com.company.guiapp.action;

import com.company.entity.User;
import com.company.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationAction implements ActionListener {

    private UserService userService;
    private String login;
    private String pass;

    public RegistrationAction(UserService userService, String login, String pass) {
        this.userService = userService;
        this.login = login;
        this.pass = pass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
