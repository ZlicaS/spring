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
public class Broj extends Model{
    private int brojAdrese;

    public Broj(int brojAdrese) {
        this.brojAdrese = brojAdrese;
    }

    public int getBrojAdrese() {
        return brojAdrese;
    }

    public void setBrojAdrese(int brojAdrese) {
        this.brojAdrese = brojAdrese;
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
        return "SELECT * FROM broj";
    }

    @Override
    public String details() {
        return "SELECT * FROM broj WHERE brojAdrese = " + getBrojAdrese();
    }
    
    
}
