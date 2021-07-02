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
public class Pozicija extends Model{
    private int idPoz;
    private String naziv;
    private OrganizacionaJedinica orgJed;
    private Pozicija pozicija;

    public Pozicija(int idPoz, String naziv, OrganizacionaJedinica orgJed, Pozicija pozicija) {
        this.idPoz = idPoz;
        this.naziv = naziv;
        this.orgJed = orgJed;
        this.pozicija = pozicija;
    }

    public Pozicija() {
    }
    

    public Pozicija getPozicija() {
        return pozicija;
    }

    public void setPozicija(Pozicija pozicija) {
        this.pozicija = pozicija;
    }

    public int getIdPoz() {
        return idPoz;
    }

    public void setIdPoz(int idPoz) {
        this.idPoz = idPoz;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public OrganizacionaJedinica getOrgJed() {
        return orgJed;
    }

    public void setOrgJed(OrganizacionaJedinica orgJed) {
        this.orgJed = orgJed;
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
        return "SELECT * FROM pozicija";
    }

    @Override
    public String details() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
