/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sapsaj
 */
public class Zahtev extends Model {

    private int idZahteva;
    private Date datum;
    private String odobreno;

    private Zaposleni zaposleni1;
    private Zaposleni zaposleni2;

    private List<StavkaZahteva> stavkeZahteva;

    public Zahtev() {
        stavkeZahteva=new ArrayList<>();
    }

    public Zahtev(int idZahteva, Date datum, String odobreno, Zaposleni zaposleni1, Zaposleni zaposleni2, List<StavkaZahteva> stavkeZahteva) {
        this.idZahteva = idZahteva;
        this.datum = datum;
        this.odobreno = odobreno;
        this.zaposleni1 = zaposleni1;
        this.zaposleni2 = zaposleni2;
        this.stavkeZahteva = stavkeZahteva;
    }

   

    public List<StavkaZahteva> getStavkeZahteva() {
        return stavkeZahteva;
    }

    public void setStavkeZahteva(List<StavkaZahteva> stavkeZahteva) {
        this.stavkeZahteva = stavkeZahteva;
    }

    

    @Override
    public String insert() {
        java.sql.Date datum = new java.sql.Date(getDatum().getTime());
        return "INSERT INTO zahtev (datum,odobreno,zaposleni1Id,zaposleni2Id) VALUES ('" +datum + "', '" + getOdobreno()+ "', '" + getZaposleni1().getIdZaposleni() + "', '" + getZaposleni2().getIdZaposleni() + "')";
    }

    @Override
    public String update() {
        java.sql.Date datum = new java.sql.Date(getDatum().getTime());
        return "UPDATE zahtev SET datum = '" + datum + "',odobreno = '" + getOdobreno()+ "',zaposleni1Id = '" + getZaposleni1().getIdZaposleni() + "',zaposleni2Id = '" + getZaposleni2().getIdZaposleni() + "' WHERE idZahteva = '" + getIdZahteva() + "'";
    }

    @Override
    public String delete() {
        return "DELETE FROM zahtev WHERE idZahteva = '" + getIdZahteva() + "'";
    }

    @Override
    public String findAll() {
        return "SELECT * FROM zahtev";
    }

    @Override
    public String details() {
        return "SELECT * FROM zahtev WHERE idZahteva = " + getIdZahteva();
    }

    public int getIdZahteva() {
        return idZahteva;
    }

    public void setIdZahteva(int idZahteva) {
        this.idZahteva = idZahteva;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(String odobreno) {
        this.odobreno = odobreno;
    }

 

    

    public Zaposleni getZaposleni1() {
        return zaposleni1;
    }

    public void setZaposleni1(Zaposleni zaposleni1) {
        this.zaposleni1 = zaposleni1;
    }

    public Zaposleni getZaposleni2() {
        return zaposleni2;
    }

    public void setZaposleni2(Zaposleni zaposleni2) {
        this.zaposleni2 = zaposleni2;
    }

}
