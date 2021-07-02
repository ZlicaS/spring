/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.domain;

/**
 *
 * @author lizasapsaj
 */
public class Ulica extends Model{
    private int idUlice;
    private String naziv;
    private Broj broj;

    public Ulica(int idUlice, String naziv, Broj broj) {
        this.idUlice = idUlice;
        this.naziv = naziv;
        this.broj = broj;
    }

    public Broj getBroj() {
        return broj;
    }

    public void setBroj(Broj broj) {
        this.broj = broj;
    }

    public int getIdUlice() {
        return idUlice;
    }

    public void setIdUlice(int idUlice) {
        this.idUlice = idUlice;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        return "SELECT * FROM ulica";
    }

    @Override
    public String details() {
        return "SELECT * FROM ulica WHERE idUlice = " + getIdUlice();
    }
    
    
    
}
