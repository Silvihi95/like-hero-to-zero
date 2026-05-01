/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.service;

import de.iubh.model.Scientist;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ScientistService {

    @PersistenceContext(unitName = "likeHeroToZeroPU")
    private EntityManager em;

    public Scientist findByUsername(String username) {
        List<Scientist> result = em.createQuery(
            "SELECT s FROM Scientist s WHERE s.username = :username", Scientist.class)
            .setParameter("username", username)
            .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public boolean login(String username, String password) {
        Scientist s = findByUsername(username);
        return s != null && s.getPassword().equals(password);
    }

    public void save(Scientist scientist) {
        em.persist(scientist);
    }
}