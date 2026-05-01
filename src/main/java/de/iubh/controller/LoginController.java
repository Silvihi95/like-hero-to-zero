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

/**
 * JSF Backing Bean für den Login.
 * Verwaltet die Benutzeranmeldung und Session.
 * SessionScoped: bleibt für die gesamte Browser-Session aktiv.
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    /** Service für Wissenschaftler-Operationen. */
    @Inject
    private ScientistService scientistService;

    /** Eingegebener Benutzername. */
    private String username;

    /** Eingegebenes Passwort. */
    private String password;

    /** Gibt an ob der Benutzer eingeloggt ist. */
    private boolean loggedIn = false;

    /**
     * Überprüft die Login-Daten und leitet bei Erfolg zum Dashboard weiter.
     * @return Navigation zum Dashboard oder null bei Fehler
     */
    public String login() {
        if (scientistService.login(username, password)) {
            loggedIn = true;
            return "dashboard?faces-redirect=true";
        }
        return null;
    }

    /**
     * Loggt den Benutzer aus und leitet zur Startseite weiter.
     * @return Navigation zur Startseite
     */
    public String logout() {
        loggedIn = false;
        username = null;
        password = null;
        return "index?faces-redirect=true";
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isLoggedIn() { return loggedIn; }
}