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

@Stateless
public class CountryService {

    @PersistenceContext(unitName = "likeHeroToZeroPU")
    private EntityManager em;

    public List<Country> findAll() {
        return em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }

    public Country findByCode(String code) {
        List<Country> result = em.createQuery(
            "SELECT c FROM Country c WHERE c.countryCode = :code", Country.class)
            .setParameter("code", code)
            .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Country country) {
        em.persist(country);
    }
}