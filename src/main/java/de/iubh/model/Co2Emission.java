/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.iubh.model;

import jakarta.persistence.*;

@Entity
@Table(name = "co2_emissions")
public class Co2Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(name = "emission_kt", nullable = false)
    private double emissionKt;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    // Getters und Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getEmissionKt() { return emissionKt; }
    public void setEmissionKt(double emissionKt) { this.emissionKt = emissionKt; }
    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }
}