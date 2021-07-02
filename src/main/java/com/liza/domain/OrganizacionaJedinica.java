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
public class OrganizacionaJedinica extends Model{
    private int idOrgJed;
    private String naziv;

    public OrganizacionaJedinica(int idOrgJed, String naziv) {
        this.idOrgJed = idOrgJed;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getIdOrgJed() {
        return idOrgJed;
    }

    public void setIdOrgJed(int idOrgJed) {
        this.idOrgJed = idOrgJed;
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
        return "SELECT * FROM organizaciona_jedinica";
    }

    @Override
    public String details() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
