/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.model;

import jakarta.persistence.*;

/**
 * Entity-Klasse für ein Land.
 * Repräsentiert einen Datensatz in der Tabelle "countries".
 */
@Entity
@Table(name = "countries")
public class Country {

    /** Eindeutiger Primärschlüssel, wird automatisch generiert. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name des Landes, z.B. "Deutschland". */
    @Column(nullable = false)
    private String name;

    /** ISO 3166-1 alpha-2 Ländercode, z.B. "DE". */
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
}
