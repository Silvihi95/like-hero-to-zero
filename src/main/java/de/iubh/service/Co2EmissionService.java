/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.service;

import de.iubh.model.Co2Emission;
import de.iubh.model.Country;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Co2EmissionService {

    @PersistenceContext(unitName = "likeHeroToZeroPU")
    private EntityManager em;

    public List<Co2Emission> findAll() {
        return em.createQuery("SELECT e FROM Co2Emission e", Co2Emission.class).getResultList();
    }

    public List<Co2Emission> findLatestByCountry(Country country) {
        return em.createQuery(
            "SELECT e FROM Co2Emission e WHERE e.country = :country ORDER BY e.year DESC",
            Co2Emission.class)
            .setParameter("country", country)
            .setMaxResults(1)
            .getResultList();
    }

    public void save(Co2Emission emission) {
        em.persist(emission);
    }

    public void update(Co2Emission emission) {
        em.merge(emission);
    }

    public void delete(Long id) {
        Co2Emission e = em.find(Co2Emission.class, id);
        if (e != null) em.remove(e);
    }

    public List<Co2Emission> findLatestForAllCountries() {
        return em.createQuery(
            "SELECT e FROM Co2Emission e WHERE e.year = " +
            "(SELECT MAX(e2.year) FROM Co2Emission e2 WHERE e2.country = e.country) " +
            "ORDER BY e.country.name ASC",
            Co2Emission.class)
            .getResultList();
    }
}