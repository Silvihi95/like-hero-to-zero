/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.controller;

import de.iubh.model.Scientist;
import de.iubh.service.ScientistService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * JSF Backing Bean für den Login.
 * Verwaltet die Benutzeranmeldung und Session.
 * SessionScoped: bleibt für die gesamte Browser-Session aktiv.
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private ScientistService scientistService;

    private String username;
    private String password;
    private boolean loggedIn = false;
    private boolean editor = false;

    /**
     * Überprüft die Login-Daten und leitet bei Erfolg weiter.
     */
    public String login() {
        Scientist s = scientistService.login(username, password);
        if (s != null) {
            loggedIn = true;
            editor = s.isEditor();
            return "dashboard?faces-redirect=true";
        }
        return null;
    }

    /**
     * Loggt den Benutzer aus.
     */
    public String logout() {
        loggedIn = false;
        editor = false;
        username = null;
        password = null;
        return "index?faces-redirect=true";
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isLoggedIn() { return loggedIn; }
    public boolean isEditor() { return editor; }
}