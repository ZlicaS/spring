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
public abstract class Model {
    private int operation;
    
    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
    public abstract String insert();

    public abstract String update();

    public abstract String delete();

    public abstract String findAll();

    public abstract String details();
    
}
