/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.controllers;

import com.liza.db.DBBroker;
import com.liza.domain.Delatnost;
import com.liza.domain.Klijent;
import com.liza.domain.Mesto;
import com.liza.domain.PotencijalniKlijent;
import com.liza.util.IOperation;
import java.text.ParseException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sapsaj
 */
@Controller
@RequestMapping(value = "/klijent")
public class KlijentController {

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView all_klijent() {
        DBBroker.getInstance().connect();
        List<Klijent> klijenti = DBBroker.getInstance().vratiSveKlijente();
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("klijent/find");
        mv.addObject("klijentList", klijenti);
        return mv;

    }

    @RequestMapping(value = "/details/{idKlijent}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable int idKlijent) {
        DBBroker.getInstance().connect();
        Klijent klijent = DBBroker.getInstance().vratiKlijenta(idKlijent);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("klijent/details");
        mv.addObject("klijent", klijent);
        return mv;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insert_get() {
        DBBroker.getInstance().connect();
        List<Delatnost> delatnosti = DBBroker.getInstance().vratiSveDelatnosti();
        List<Mesto> mesta = DBBroker.getInstance().vratiSvaMesta();
        List<PotencijalniKlijent> pk = DBBroker.getInstance().vratiSvePotencijalneKlijente();
        DBBroker.getInstance().disconnect();

        ModelAndView mv = new ModelAndView("klijent/insert");
        mv.addObject("klijent", new Klijent());
        mv.addObject("delatnostList", delatnosti);
        mv.addObject("mestaList", mesta);
        mv.addObject("pkList", pk);

        return mv;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("klijent") Klijent klijent) throws ParseException {
        klijent.setOperation(IOperation.INSERT);

        DBBroker.getInstance().pokreniDBTransakciju();
        int result = DBBroker.getInstance().zapamtiDBTransakciju(klijent);
        if (result > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/klijent/find";
    }

    @RequestMapping(value = "/update/{idKlijent}", method = RequestMethod.GET)
    public ModelAndView update_get(@PathVariable int idKlijent) {
        DBBroker.getInstance().connect();
        Klijent klijent = DBBroker.getInstance().vratiKlijenta(idKlijent);
        List<Delatnost> delatnosti = DBBroker.getInstance().vratiSveDelatnosti();
        List<Mesto> mesta = DBBroker.getInstance().vratiSvaMesta();
        List<PotencijalniKlijent> pk = DBBroker.getInstance().vratiSvePotencijalneKlijente();

        DBBroker.getInstance().disconnect();

        ModelAndView mv = new ModelAndView("klijent/update");
        mv.addObject("klijent", klijent);
        mv.addObject("delatnostList", delatnosti);
        mv.addObject("mestaList", mesta);
        mv.addObject("pkList", pk);

        return mv;
    }

    @RequestMapping(value = "/update/{idKlijent}", method = RequestMethod.POST)
    public String update_post(@PathVariable int idKlijent, @ModelAttribute("klijent") Klijent klijent) throws ParseException {
        klijent.setIdKlijent(idKlijent);
        klijent.setOperation(IOperation.UPDATE);

        DBBroker.getInstance().pokreniDBTransakciju();
        int res = DBBroker.getInstance().zapamtiDBTransakciju(klijent);
        if (res > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/klijent/find";
    }

    @RequestMapping(value = "/delete/{idKlijent}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable int idKlijent) {
        DBBroker.getInstance().connect();
        Klijent klijent = DBBroker.getInstance().vratiKlijenta(idKlijent);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("klijent/delete");
        mv.addObject("klijent", klijent);
        return mv;
    }

    @RequestMapping(value = "/delete/{idKlijent}", method = RequestMethod.POST)
    public String delete_post(@PathVariable int idKlijent) {
        DBBroker.getInstance().connect();
        Klijent klijent = DBBroker.getInstance().vratiKlijenta(idKlijent);
        DBBroker.getInstance().disconnect();
        klijent.setOperation(IOperation.DELETE);
        DBBroker.getInstance().pokreniDBTransakciju();
        int res = DBBroker.getInstance().zapamtiDBTransakciju(klijent);
        if (res > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/klijent/find";
    }

}
