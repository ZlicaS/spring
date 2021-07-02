/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.domain;

import java.util.Objects;

/**
 *
 * @author lizasapsaj
 */
public class Klijent extends Model {

    public Klijent() {
    }
    

    private int idKlijent;
    private String naziv;
    private String pib;
    private String telefon;
    private String webStrana;
    private int godinaOsnivanja;
    private Delatnost delatnost;
    private Mesto mesto;
    private PotencijalniKlijent potencijalniKlijent;

    public Klijent(int idKlijent, String naziv, String pib, String telefon, String webStrana, int godinaOsnivanja, Delatnost delatnost, Mesto mesto, PotencijalniKlijent potencijalniKlijent) {
        this.idKlijent = idKlijent;
        this.naziv = naziv;
        this.pib = pib;
        this.telefon = telefon;
        this.webStrana = webStrana;
        this.godinaOsnivanja = godinaOsnivanja;
        this.delatnost = delatnost;
        this.mesto = mesto;
        this.potencijalniKlijent = potencijalniKlijent;
    }

    public PotencijalniKlijent getPotencijalniKlijent() {
        return potencijalniKlijent;
    }

    public void setPotencijalniKlijent(PotencijalniKlijent potencijalniKlijent) {
        this.potencijalniKlijent = potencijalniKlijent;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getWebStrana() {
        return webStrana;
    }

    public void setWebStrana(String webStrana) {
        this.webStrana = webStrana;
    }

    public int getGodinaOsnivanja() {
        return godinaOsnivanja;
    }

    public void setGodinaOsnivanja(int godinaOsnivanja) {
        this.godinaOsnivanja = godinaOsnivanja;
    }

    public Delatnost getDelatnost() {
        return delatnost;
    }

    public void setDelatnost(Delatnost delatnost) {
        this.delatnost = delatnost;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (this.idKlijent != other.idKlijent) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return idKlijent + "";
    }

    @Override
    public String insert() {
        return "INSERT INTO klijent VALUES ('" + getIdKlijent() + "', '" + getNaziv() + "', '" + getPib() + "', '" + getTelefon() + "', '" + getWebStrana() + "', '" + getGodinaOsnivanja() + "', '" + getDelatnost().getSifraDelatnosti() + "', '" + getMesto().getPostanskiBroj() + "', '" + getPotencijalniKlijent().getIdPotKlijenta() + "')";
    }

    @Override
    public String update() {
        return "UPDATE klijent SET naziv = '" + getNaziv()+ "',pib = '" + getPib()+ "',telefon = '" + getTelefon()+ "',webStrana = '" + getWebStrana()+ "',godinaOsnivanja = '" + getGodinaOsnivanja()+"', delatnostId = '" + getDelatnost().getSifraDelatnosti()+"', mestoId = '" + getMesto().getPostanskiBroj()+"', potencijalniKlijentId = '" + getPotencijalniKlijent().getIdPotKlijenta()+ "' WHERE idKlijent = '" + getIdKlijent()+ "'";

    }

    @Override
    public String delete() {
        return "DELETE FROM klijent WHERE idKlijent = '" + getIdKlijent() + "'";
    }

    @Override
    public String findAll() {
        return "SELECT * FROM klijent"; // k JOIN delatnost d ON (k.delatnostId = d.idDelatnost) JOIN mesto m ON (k.mestoId=m.postanskiBroj) JOIN potencijalniKlijent pk ON (k.potencijalniKlijentId=pk.idPotKlijenta)";
    }

    @Override
    public String details() {
        return "SELECT * FROM klijent WHERE idKlijent = " + getIdKlijent(); //k JOIN delatnost d ON (k.delatnostId = d.idDelatnost) JOIN mesto m ON (k.mestoId=m.postanskiBroj) JOIN potencijalniKlijent pk ON (k.potencijalniKlijentId=pk.idPotKlijenta) WHERE idKlijent = '" + getIdKlijent() + "'";

    }

}
