/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.domain;

/**
 *
 * @author Sapsaj
 */
public class StavkaZahteva extends Model {

    private int rbr;
    private String opis;
    private Zahtev zahtev;
    private int status;
    private int updateID;

    public StavkaZahteva() {
    }

    public StavkaZahteva(int rbr, String opis, Zahtev zahtev, int status, int updateID) {
        this.rbr = rbr;
        this.opis = opis;
        this.zahtev = zahtev;
        this.status = status;
        this.updateID = updateID;
    }

    public Zahtev getZahtev() {
        return zahtev;
    }

    public void setZahtev(Zahtev zahtev) {
        this.zahtev = zahtev;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    @Override
    public String insert() {
        return "INSERT INTO stavkazahteva (rbr,opis,zahtevId,status,updateID)"
                + " VALUES ('" + getRbr() + "', '" + getOpis() + "', '" + getZahtev().getIdZahteva() + "', '" + getStatus() + "', '" + getUpdateID() + "')";
    }

    @Override
    public String update() {
        return "UPDATE stavkazahteva SET opis = " + getOpis()
                + ", zahtevId = " + getZahtev().getIdZahteva()
                + ", status = " + getStatus()
                + " AND WHERE  rbr = " + getRbr();
    }

    @Override
    public String delete() {
        return "DELETE FROM stavkazahteva WHERE rbr = " + getRbr() + " AND zahtevId = " + getZahtev().getIdZahteva();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM stavkazahteva";
    }

    @Override
    public String details() {
        return "SELECT * FROM stavkazahteva WHERE zahtevId = " + getZahtev().getIdZahteva();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUpdateID() {
        return updateID;
    }

    public void setUpdateID(int updateID) {
        this.updateID = updateID;
    }

}
