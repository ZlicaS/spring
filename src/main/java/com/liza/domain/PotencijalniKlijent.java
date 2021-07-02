/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.domain;

import java.util.Date;

/**
 *
 * @author lizasapsaj
 */
public class PotencijalniKlijent extends Model{
    private int idPotKlijenta;
    private Date datum;
    private String naziv;
    private String telefon;
    private String email;
    private Zaposleni zaposleni1;
    private Zaposleni zaposleni2;

    public PotencijalniKlijent(int idPotKlijenta, Date datum, String naziv, String telefon, String email, Zaposleni zaposleni1, Zaposleni zaposleni2) {
        this.idPotKlijenta = idPotKlijenta;
        this.datum = datum;
        this.naziv = naziv;
        this.telefon = telefon;
        this.email = email;
        this.zaposleni1 = zaposleni1;
        this.zaposleni2 = zaposleni2;
    }

    public PotencijalniKlijent() {
    }

    public PotencijalniKlijent(int idPotKlijenta) {
        this.idPotKlijenta = idPotKlijenta;
       
    }
    public Zaposleni getZaposleni2() {
        return zaposleni2;
    }

    public void setZaposleni2(Zaposleni zaposleni2) {
        this.zaposleni2 = zaposleni2;
    }

    public int getIdPotKlijenta() {
        return idPotKlijenta;
    }

    public void setIdPotKlijenta(int idPotKlijenta) {
        this.idPotKlijenta = idPotKlijenta;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Zaposleni getZaposleni1() {
        return zaposleni1;
    }

    public void setZaposleni1(Zaposleni zaposleni1) {
        this.zaposleni1 = zaposleni1;
    }

    @Override
    public String insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findAll() {
        return "SELECT * FROM potencijalniklijent";
    }

    @Override
    public String details() {
        return "SELECT * FROM potencijalniklijent WHERE idPotKlijenta = " + getIdPotKlijenta();
    }
    
    
    
    
}
