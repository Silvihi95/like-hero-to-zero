/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.controller;

import de.iubh.service.ScientistService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private ScientistService scientistService;

    private String username;
    private String password;
    private boolean loggedIn = false;

    public String login() {
        if (scientistService.login(username, password)) {
            loggedIn = true;
            return "dashboard?faces-redirect=true";
        }
        return null;
    }

    public String logout() {
        loggedIn = false;
        username = null;
        password = null;
        return "index?faces-redirect=true";
    }

    // Getters und Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isLoggedIn() { return loggedIn; }
}