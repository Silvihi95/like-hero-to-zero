/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.model;

import jakarta.persistence.*;

/**
 * Entity-Klasse für einen registrierten Wissenschaftler.
 * Kann die Rolle SCIENTIST oder EDITOR haben.
 */
@Entity
@Table(name = "scientists")
public class Scientist {

    /** Mögliche Rollen. */
    public enum Role {
        SCIENTIST, EDITOR
    }

    /** Eindeutiger Primärschlüssel. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Eindeutiger Benutzername. */
    @Column(nullable = false, unique = true)
    private String username;

    /** Passwort. */
    @Column(nullable = false)
    private String password;

    /** E-Mail-Adresse. */
    @Column(nullable = false)
    private String email;

    /** Rolle des Benutzers. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.SCIENTIST;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public boolean isEditor() { return role == Role.EDITOR; }
}