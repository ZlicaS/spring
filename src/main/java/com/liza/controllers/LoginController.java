/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.controllers;

import com.liza.db.DBBroker;
import com.liza.domain.Zaposleni;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Sapsaj
 */
@Controller
@SessionAttributes("zaposleni1")
public class LoginController {

    @ModelAttribute("zaposleni1")
    public Zaposleni setUpZaposleni() {
        return new Zaposleni();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("zaposleni1", new Zaposleni());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("zaposleni1") Zaposleni zaposleni1, Model model) {
        if (validacija(zaposleni1)) {
            System.out.println(zaposleni1.getIme());
            return "redirect:/index";
        }
        return "redirect:/login";
    }

    private boolean validacija(Zaposleni zaposleni) {
        DBBroker.getInstance().connect();
        List<Zaposleni> zaposleni1 = DBBroker.getInstance().vratiSveZaposlene();
        for (Zaposleni z : zaposleni1) {
            if (zaposleni.getKorisnickoIme().equals(z.getKorisnickoIme()) && zaposleni.getKorisnickaSifra().equals(z.getKorisnickaSifra())) {
                zaposleni.setIme(z.getIme());
                zaposleni.setIdZaposleni(z.getIdZaposleni());
                return true;
            }
        }
        return false;
    }

}
