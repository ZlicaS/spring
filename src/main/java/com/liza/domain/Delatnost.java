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
public class Delatnost extends Model{
    private int sifraDelatnosti;
    private String naziv;

    public Delatnost(int sifraDelatnosti, String naziv) {
        this.sifraDelatnosti = sifraDelatnosti;
        this.naziv = naziv;
    }

    public Delatnost() {
    }
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getSifraDelatnosti() {
        return sifraDelatnosti;
    }

    public void setSifraDelatnosti(int sifraDelatnosti) {
        this.sifraDelatnosti = sifraDelatnosti;
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
        return "SELECT * FROM delatnost";
    }

    @Override
    public String details() {
        return "SELECT * FROM delatnost WHERE idDelatnost = " + getSifraDelatnosti();
    }
    
    
    
}
