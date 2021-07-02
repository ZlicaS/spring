/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.controllers;

import com.liza.db.DBBroker;
import com.liza.domain.StavkaZahteva;
import com.liza.domain.Zahtev;
import com.liza.domain.Zaposleni;
import com.liza.util.IOperation;
import java.text.ParseException;
import java.util.ArrayList;
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
@RequestMapping(value = "/zahtev")
public class ZahtevController {

    public static Zahtev tekuciZahtev = new Zahtev();

    static void update(Zahtev tekuciZahtev, StavkaZahteva stavkaZahteva) {
        System.out.println("Cao");
        System.out.println("Rb stavke: " + stavkaZahteva.getRbr());
        for (StavkaZahteva sp : tekuciZahtev.getStavkeZahteva()) {
            System.out.println(sp.getRbr());
            if (sp.getRbr() == stavkaZahteva.getRbr()) {
                System.out.println("nasao stavku");
                sp.setStatus(IOperation.UPDATE);
                sp.setOpis(stavkaZahteva.getOpis());

                return;
            }
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView all_zahtev() {
        DBBroker.getInstance().connect();
        List<Zahtev> zahtevi = DBBroker.getInstance().vratiSveZahteve();
        for (Zahtev z : zahtevi) {
            for (StavkaZahteva sz : z.getStavkeZahteva()) {
                DBBroker.getInstance().connect();
 //one koji su za brisanje otkazi
                if (sz.getStatus() == IOperation.DELETE
                        && sz.getUpdateID() == 0) {
                    DBBroker.getInstance().izmeniStatusStavkeZahteva(sz, 0);
                }

                if (sz.getStatus() == IOperation.INSERT
                        || sz.getStatus() == IOperation.UPDATE) {
                    boolean result = DBBroker.getInstance().obrisiStavkuZahteva(sz);
                    if (result) {
                        DBBroker.getInstance().potvrdiDBTransakciju();
                    } else {
                        DBBroker.getInstance().ponistiDBTransakciju();
                    }
                }
            }
            DBBroker.getInstance().connect();
            boolean result = DBBroker.getInstance().srediRBStavkiZahteva(z.getIdZahteva());
            if (result) {
                DBBroker.getInstance().potvrdiDBTransakciju();
            } else {
                DBBroker.getInstance().ponistiDBTransakciju();
            }
        }
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("zahtev/find");
        mv.addObject("zahtevList", zahtevi);
        return mv;

    }

    @RequestMapping(value = "/details/{idZahteva}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable int idZahteva) {
        DBBroker.getInstance().connect();
        Zahtev zahtev = DBBroker.getInstance().vratiZahtev(idZahteva);
        tekuciZahtev = zahtev;
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("zahtev/details");
        mv.addObject("zahtev", zahtev);
        System.out.println("Uzeo stavke");
        for (StavkaZahteva sp : zahtev.getStavkeZahteva()) {
            System.out.println("Stavka: " + sp.getRbr());
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{idZahteva}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable int idZahteva) {
        DBBroker.getInstance().connect();
        Zahtev zahtev = DBBroker.getInstance().vratiZahtev(idZahteva);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("zahtev/delete");
        mv.addObject("zahtev", zahtev);
        return mv;
    }

    @RequestMapping(value = "/delete/{idZahteva}", method = RequestMethod.POST)
    public String delete_post(@PathVariable int idZahteva) {
        DBBroker.getInstance().connect();
        Zahtev zahtev = DBBroker.getInstance().vratiZahtev(idZahteva);
        DBBroker.getInstance().disconnect();
        DBBroker.getInstance().pokreniDBTransakciju();
        int res = DBBroker.getInstance().obrisiZahtev(zahtev);
        if (res > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/zahtev/find";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insert_get() {
        DBBroker.getInstance().connect();
        List<Zahtev> zahtevi = DBBroker.getInstance().vratiSveZahteve();
        List<Zaposleni> zaposleni1 = DBBroker.getInstance().vratiSveZaposlene();
        List<Zaposleni> zaposleni2 = DBBroker.getInstance().vratiSveZaposlene();

        int maxBrojPonude = 0;
        for (Zahtev po : zahtevi) {
            if (maxBrojPonude < po.getIdZahteva()) {
                maxBrojPonude = po.getIdZahteva();
            }
        }
        int brojZahteva = maxBrojPonude + 1;
        Zahtev zahtev = new Zahtev();
        zahtev.setIdZahteva(brojZahteva);
        List<StavkaZahteva> stavkeZahtevaSlanje = new ArrayList<>();
        for (StavkaZahteva stavkaZahteva : zahtev.getStavkeZahteva()) {
            if (stavkaZahteva.getStatus() == IOperation.UPDATE) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
            if ((stavkaZahteva.getStatus() == 0 || stavkaZahteva.getStatus() == IOperation.INSERT)
                    && stavkaZahteva.getUpdateID() == 0) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
        }
        zahtev.setStavkeZahteva(stavkeZahtevaSlanje);
        tekuciZahtev = zahtev;
        System.out.println("Postavljeno");
        System.out.println(tekuciZahtev.getIdZahteva());
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("zahtev/insert");
        mv.addObject("zahtev", zahtev);
        mv.addObject("zaposleniList1", zaposleni1);
        mv.addObject("zaposleniList2", zaposleni2);

        return mv;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("zahtev") Zahtev zahtev) throws ParseException {
        System.out.println("Uslo u novi insert! ");
        zahtev.setOperation(IOperation.INSERT);
        List<StavkaZahteva> stavkeZahtevaSlanje = new ArrayList<>();
        for (StavkaZahteva stavkaZahteva : tekuciZahtev.getStavkeZahteva()) {
            if (stavkaZahteva.getStatus() == IOperation.UPDATE) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
            if ((stavkaZahteva.getStatus() == 0 || stavkaZahteva.getStatus() == IOperation.INSERT)
                    && stavkaZahteva.getUpdateID() == 0) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
        }
        zahtev.setStavkeZahteva(stavkeZahtevaSlanje);

        DBBroker.getInstance().pokreniDBTransakciju();
        int result = DBBroker.getInstance().zapamtiDBTransakciju(zahtev);

        if (result > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        DBBroker.getInstance().pokreniDBTransakciju();
        for (StavkaZahteva sp : stavkeZahtevaSlanje) {
            System.out.println("Stavka zahteva: " + sp.getRbr());
            System.out.println(sp.getOpis());
            result = DBBroker.getInstance().zapamtiDBTransakciju(sp);
            if (result > 0) {
                DBBroker.getInstance().potvrdiDBTransakciju();
            } else {
                DBBroker.getInstance().ponistiDBTransakciju();
            }
        }

        return "redirect:/zahtev/find";
    }

    @RequestMapping(value = "/insert/{idZahteva}", method = RequestMethod.GET)
    public ModelAndView insert_get(@PathVariable int idZahteva) {
        DBBroker.getInstance().connect();

        List<Zaposleni> zaposleni1 = DBBroker.getInstance().vratiSveZaposlene();
        List<Zaposleni> zaposleni2 = DBBroker.getInstance().vratiSveZaposlene();

        Zahtev zahtev = DBBroker.getInstance().vratiZahtev(idZahteva);

        List<StavkaZahteva> stavkeZahtevaSlanje = new ArrayList<>();
        for (StavkaZahteva stavkaZahteva : tekuciZahtev.getStavkeZahteva()) {
            if (stavkaZahteva.getStatus() == IOperation.UPDATE) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
            if ((stavkaZahteva.getStatus() == 0 || stavkaZahteva.getStatus() == IOperation.INSERT)
                    && stavkaZahteva.getUpdateID() == 0) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
        }
        tekuciZahtev.setStavkeZahteva(stavkeZahtevaSlanje);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("zahtev/insert");
        mv.addObject("zahtev", tekuciZahtev);

        mv.addObject("zaposleniList1", zaposleni1);
        mv.addObject("zaposleniList2", zaposleni2);
        return mv;
    }

    @RequestMapping(value = "/insert/{idZahteva}", method = RequestMethod.POST)
    public String insert_post_id(@ModelAttribute("zahtev") Zahtev zahtev) throws ParseException {
        System.out.println("Uslo u novi insert! ");
        zahtev.setOperation(IOperation.INSERT);
        List<StavkaZahteva> stavkeZahtevaSlanje = new ArrayList<>();
        for (StavkaZahteva stavkaZahteva : tekuciZahtev.getStavkeZahteva()) {
            if (stavkaZahteva.getStatus() == IOperation.UPDATE) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
            if ((stavkaZahteva.getStatus() == 0 || stavkaZahteva.getStatus() == IOperation.INSERT)
                    && stavkaZahteva.getUpdateID() == 0) {
                stavkeZahtevaSlanje.add(stavkaZahteva);
            }
        }
        zahtev.setStavkeZahteva(stavkeZahtevaSlanje);
        DBBroker.getInstance().pokreniDBTransakciju();
        for (StavkaZahteva sp : stavkeZahtevaSlanje) {
            System.out.println("Stavka zahteva: " + sp.getRbr());
            int result = DBBroker.getInstance().zapamtiDBTransakciju(sp);
            if (result > 0) {
                DBBroker.getInstance().potvrdiDBTransakciju();
            } else {
                DBBroker.getInstance().ponistiDBTransakciju();
            }
        }
        int result = DBBroker.getInstance().zapamtiDBTransakciju(zahtev);
        if (result > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/zahtev/find";
    }

    @RequestMapping(value = "/update/{idZahteva}", method = RequestMethod.GET)
    public ModelAndView update_get(@PathVariable int idZahteva) {
        System.out.println("Uslo u update get!");
        DBBroker.getInstance().connect();
        Zahtev zahtev = DBBroker.getInstance().vratiZahtev(idZahteva);
        List<Zaposleni> zaposleni1 = DBBroker.getInstance().vratiSveZaposlene();
        List<Zaposleni> zaposleni2 = DBBroker.getInstance().vratiSveZaposlene();

        // List<Klijent> klijenti = DBBroker.getInstance().vratiSveKlijente();
        //stavke za prikaz        
        List<StavkaZahteva> stavkeZahtevaSlanje = new ArrayList<>();
        List<StavkaZahteva> stavkeZahtevaNove = new ArrayList<>();
        if (!tekuciZahtev.getStavkeZahteva().isEmpty()) {
            stavkeZahtevaNove = tekuciZahtev.getStavkeZahteva();
            zahtev.setStavkeZahteva(stavkeZahtevaNove);
            for (StavkaZahteva stavkaZahteva : tekuciZahtev.getStavkeZahteva()) {
                System.out.println("Stavke u update: " + stavkaZahteva.getStatus());
                if (stavkaZahteva.getStatus() == IOperation.UPDATE) {
                    System.out.println("Stavka za update get");
                    stavkeZahtevaSlanje.add(stavkaZahteva);
                }
                if ((stavkaZahteva.getStatus() == 0 || stavkaZahteva.getStatus() == IOperation.INSERT)
                        && stavkaZahteva.getUpdateID() == 0) {
                    stavkeZahtevaSlanje.add(stavkaZahteva);
                }

                if ((stavkaZahteva.getStatus() == IOperation.DELETE)) {
                    System.out.println("Stavka za brisanje!");
                }
            }
        } else {
            for (StavkaZahteva stavkaZahteva : zahtev.getStavkeZahteva()) {
                if (stavkaZahteva.getStatus() == IOperation.UPDATE) {
                    stavkeZahtevaSlanje.add(stavkaZahteva);
                }
                if ((stavkaZahteva.getStatus() == 0 || stavkaZahteva.getStatus() == IOperation.INSERT)
                        && stavkaZahteva.getUpdateID() == 0) {
                    stavkeZahtevaSlanje.add(stavkaZahteva);
                }

                if ((stavkaZahteva.getStatus() == IOperation.DELETE)) {
                    System.out.println("Stavka za brisanje!");
                }
            }
        }

        tekuciZahtev = zahtev;
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("zahtev/update");
        mv.addObject("zahtev", zahtev);
        mv.addObject("stavkeSlanje", stavkeZahtevaSlanje);
        mv.addObject("zaposleniList1", zaposleni1);
        mv.addObject("zaposleniList2", zaposleni2);
        return mv;
    }

    @RequestMapping(value = "/update/{idZahteva}", method = RequestMethod.POST) //preo beansa sa fronta
    public String update_post(@PathVariable int idZahteva, @ModelAttribute("zahtev") Zahtev zahtevForma) throws ParseException {
        System.out.println("Uslo u update post");
        tekuciZahtev.setZaposleni1(zahtevForma.getZaposleni1()); //iz forme uzimam zaposlenog i postavljam kod onog koji sam vukla iz baze
        tekuciZahtev.setZaposleni2(zahtevForma.getZaposleni2());
        tekuciZahtev.setDatum(zahtevForma.getDatum());
        tekuciZahtev.setOdobreno(zahtevForma.getOdobreno());
        boolean result = true;
        //Delete items
        for (StavkaZahteva sp : tekuciZahtev.getStavkeZahteva()) {
            System.out.println("Stavka zahteva: " + sp.getRbr());
            if (sp.getStatus() == IOperation.DELETE) {
                System.out.println("Stavka za brisanje zahtev");
                DBBroker.getInstance().connect();
                result = DBBroker.getInstance().obrisiStavkuZahteva(sp);
                if (result) {
                    DBBroker.getInstance().potvrdiDBTransakciju();
                } else {
                    DBBroker.getInstance().ponistiDBTransakciju();
                }
            }
        }
        //Update items
        for (StavkaZahteva sp : tekuciZahtev.getStavkeZahteva()) {
            if (sp.getStatus() == IOperation.UPDATE) {
                System.out.println("Stavka za update");
                DBBroker.getInstance().connect();
                result = DBBroker.getInstance().izmeniStavkuZahteva(sp, sp.getRbr());
                if (result) {
                    DBBroker.getInstance().potvrdiDBTransakciju();
                } else {
                    DBBroker.getInstance().ponistiDBTransakciju();
                }
            }
        }

        //Insert items
        for (StavkaZahteva sp : tekuciZahtev.getStavkeZahteva()) {
            if (sp.getStatus() == IOperation.INSERT) {
                sp.setStatus(0);
                DBBroker.getInstance().connect();
                int res = DBBroker.getInstance().zapamtiDBTransakciju(sp);
                if (res > 0) {
                    DBBroker.getInstance().potvrdiDBTransakciju();
                } else {
                    DBBroker.getInstance().ponistiDBTransakciju();
                }
            }
        }

        DBBroker.getInstance().connect();
        result = DBBroker.getInstance().srediRBStavkiZahteva(idZahteva);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        //postavi updateID na 0 svim preostalim stavkama
        DBBroker.getInstance().connect();
        result = DBBroker.getInstance().postviUpdateIDSvimStavkama(idZahteva, 0);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }

        //sredi osnovne podatke //proracun se cuva na kraju, tek kad se sve stavke izmene, tad ide potvrdi. Ako pukne baza, ne cuva se nista
        DBBroker.getInstance().connect();
        result = DBBroker.getInstance().srediOsnovnePodatkeZahteva(tekuciZahtev);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }

        return "redirect:/zahtev/find";
    }
}
