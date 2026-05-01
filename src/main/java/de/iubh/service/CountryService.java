/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.service;

import de.iubh.model.Country;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Service-Klasse für Länder-Operationen.
 * Stateless EJB Bean für den Datenbankzugriff auf die Tabelle "countries".
 */
@Stateless
public class CountryService {

    /** EntityManager für den JPA-Datenbankzugriff. */
    @PersistenceContext(unitName = "likeHeroToZeroPU")
    private EntityManager em;

    /**
     * Gibt alle Länder aus der Datenbank zurück.
     * @return Liste aller Länder
     */
    public List<Country> findAll() {
        return em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }

    /**
     * Sucht ein Land anhand des Ländercodes.
     * @param code ISO 3166-1 alpha-2 Ländercode, z.B. "DE"
     * @return Land oder null wenn nicht gefunden
     */
    public Country findByCode(String code) {
        List<Country> result = em.createQuery(
            "SELECT c FROM Country c WHERE c.countryCode = :code", Country.class)
            .setParameter("code", code)
            .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Speichert ein neues Land in der Datenbank.
     * @param country Das zu speichernde Land
     */
    public void save(Country country) {
        em.persist(country);
    }
}