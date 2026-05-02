/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.controller;

import de.iubh.model.Co2Emission;
import de.iubh.model.Country;
import de.iubh.service.Co2EmissionService;
import de.iubh.service.CountryService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * JSF Backing Bean für CO2-Emissionen.
 * Verarbeitet Suchanfragen und das Eintragen neuer Daten.
 */
@Named
@RequestScoped
public class Co2Controller {

    @Inject
    private Co2EmissionService co2Service;

    @Inject
    private CountryService countryService;

    private String selectedCountryCode;
    private List<Co2Emission> results = new ArrayList<>();
    private Co2Emission newEmission = new Co2Emission();
    private String selectedCountryCodeForNew;
    private String message;

    /**
     * Sucht die aktuellste freigegebene CO2-Emission für das eingegebene Land.
     */
    public void search() {
        message = "Suche nach: " + selectedCountryCode;
        results = new ArrayList<>();
        try {
            Country country = countryService.findByCode(selectedCountryCode);
            if (country != null) {
                results = co2Service.findLatestApprovedByCountry(country);
                if (results.isEmpty()) {
                    message = "Keine Daten gefunden für: " + selectedCountryCode;
                } else {
                    message = null;
                }
            } else {
                message = "Land nicht gefunden: " + selectedCountryCode;
            }
        } catch (Exception e) {
            message = "Fehler: " + e.getMessage();
        }
    }

    /**
     * Speichert eine neue CO2-Emission mit Status PENDING.
     */
    public void save() {
        try {
            Country country = countryService.findByCode(selectedCountryCodeForNew);
            if (country != null) {
                newEmission.setCountry(country);
                newEmission.setStatus(Co2Emission.Status.PENDING);
                co2Service.save(newEmission);
                newEmission = new Co2Emission();
                message = "Gespeichert! Wartet auf Freigabe.";
            } else {
                message = "Land nicht gefunden!";
            }
        } catch (Exception e) {
            message = "Fehler: " + e.getMessage();
        }
    }

    /**
     * Gibt eine Emission frei (nur für Herausgeber).
     */
    public void approve(Long id) {
        try {
            Co2Emission emission = co2Service.findById(id);
            if (emission != null) {
                emission.setStatus(Co2Emission.Status.APPROVED);
                co2Service.update(emission);
                message = "Eintrag freigegeben!";
            }
        } catch (Exception e) {
            message = "Fehler: " + e.getMessage();
        }
    }

    /**
     * Lehnt eine Emission ab und löscht sie.
     */
    public void reject(Long id) {
        try {
            co2Service.delete(id);
            message = "Eintrag abgelehnt!";
        } catch (Exception e) {
            message = "Fehler: " + e.getMessage();
        }
    }

    /**
     * Gibt alle ausstehenden Emissionen zurück.
     */
    public List<Co2Emission> getPendingEmissions() {
        return co2Service.findPending();
    }

    /**
     * Gibt alle freigegebenen Emissionen für alle Länder zurück.
     */
    public List<Co2Emission> getAllLatestEmissions() {
        return co2Service.findLatestApprovedForAllCountries();
    }

    /**
     * Gibt alle verfügbaren Länder zurück.
     */
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSelectedCountryCode() { return selectedCountryCode; }
    public void setSelectedCountryCode(String s) { this.selectedCountryCode = s; }
    public List<Co2Emission> getResults() { return results; }
    public Co2Emission getNewEmission() { return newEmission; }
    public void setNewEmission(Co2Emission e) { this.newEmission = e; }
    public String getSelectedCountryCodeForNew() { return selectedCountryCodeForNew; }
    public void setSelectedCountryCodeForNew(String s) { this.selectedCountryCodeForNew = s; }
}