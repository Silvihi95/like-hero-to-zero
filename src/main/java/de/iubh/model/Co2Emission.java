/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.model;

import jakarta.persistence.*;

/**
 * Entity-Klasse für eine CO2-Emission.
 * Repräsentiert einen Datensatz in der Tabelle "co2_emissions".
 * Jede Emission ist einem Land zugeordnet.
 */
@Entity
@Table(name = "co2_emissions")
public class Co2Emission {

    /** Eindeutiger Primärschlüssel, wird automatisch generiert. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Jahr der Messung, z.B. 2023. */
    @Column(nullable = false)
    private int year;

    /** CO2-Ausstoß in Kilotonnen (kt). */
    @Column(name = "emission_kt", nullable = false)
    private double emissionKt;

    /** Das Land dem diese Emission zugeordnet ist. */
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getEmissionKt() { return emissionKt; }
    public void setEmissionKt(double emissionKt) { this.emissionKt = emissionKt; }
    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }
}