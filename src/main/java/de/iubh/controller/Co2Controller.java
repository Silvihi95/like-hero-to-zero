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

    public void search() {
        message = "Suche nach: " + selectedCountryCode;
        results = new ArrayList<>();
        try {
            Country country = countryService.findByCode(selectedCountryCode);
            if (country != null) {
                results = co2Service.findLatestByCountry(country);
                if (results.isEmpty()) {
                    message = "Keine Daten gefunden für: " + selectedCountryCode;
                }
            } else {
                message = "Land nicht gefunden: " + selectedCountryCode;
            }
        } catch (Exception e) {
            message = "Fehler: " + e.getMessage();
        }
    }

    public void save() {
        try {
            Country country = countryService.findByCode(selectedCountryCodeForNew);
            if (country != null) {
                newEmission.setCountry(country);
                co2Service.save(newEmission);
                newEmission = new Co2Emission();
                message = "Gespeichert!";
            } else {
                message = "Land nicht gefunden!";
            }
        } catch (Exception e) {
            message = "Fehler: " + e.getMessage();
        }
    }

    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSelectedCountryCode() { return selectedCountryCode; }
    public void setSelectedCountryCode(String selectedCountryCode) { this.selectedCountryCode = selectedCountryCode; }
    public List<Co2Emission> getResults() { return results; }
    public Co2Emission getNewEmission() { return newEmission; }
    public void setNewEmission(Co2Emission newEmission) { this.newEmission = newEmission; }
    public String getSelectedCountryCodeForNew() { return selectedCountryCodeForNew; }
    public void setSelectedCountryCodeForNew(String s) { this.selectedCountryCodeForNew = s; }
}