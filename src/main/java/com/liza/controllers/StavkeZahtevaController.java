/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.controllers;

import com.liza.db.DBBroker;
import com.liza.domain.StavkaZahteva;
import com.liza.domain.Zahtev;
import com.liza.util.IOperation;
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
@RequestMapping(value = "/stavkaZahteva")
public class StavkeZahtevaController {

    @RequestMapping(value = "/find/{idZahteva}", method = RequestMethod.GET)
    public ModelAndView all_calculation_items(@PathVariable int idZahteva) {
        List<StavkaZahteva> stavkeZahteva = ZahtevController.tekuciZahtev.getStavkeZahteva();
        //stavke za prikaz
        List<StavkaZahteva> stavkeZahtevaSlanje = new ArrayList<>();
        for (StavkaZahteva stavka : stavkeZahteva) {
            if (stavka.getStatus() == IOperation.UPDATE) {
                stavkeZahtevaSlanje.add(stavka);
            }
            if ((stavka.getStatus() == 0 || stavka.getStatus() == IOperation.INSERT)
                    && stavka.getUpdateID() == 0) {
                stavkeZahtevaSlanje.add(stavka);
            }
             if(stavka.getStatus()==IOperation.DELETE){
                System.out.println("Stavka za brisanje");
            }
        }

        ModelAndView mv = new ModelAndView("stavkaZahteva/find");

        mv.addObject("idZahteva", idZahteva);
        mv.addObject("stavkaZahtevaList", stavkeZahtevaSlanje);
        return mv;

    }

    @RequestMapping(value = "/find/{idZahteva}", method = RequestMethod.POST)
    public String sve_stavke_zahteva_post(@PathVariable int idZahteva) {
        
        DBBroker.getInstance().connect();
        Zahtev zahtev = DBBroker.getInstance().vratiZahtev(idZahteva);
        if (DBBroker.getInstance().vratiZahtev(idZahteva) != null) {
            System.out.println("Ima ga u bazi");
            return "redirect:/zahtev/update/{idZahteva}";
        }
        return "redirect:/zahtev/insert/{idZahteva}";
    }

    @RequestMapping(value = "/insert/{idZahteva}", method = RequestMethod.GET)
    public ModelAndView insert_get(@PathVariable int idZahteva) {
       
        ModelAndView mv = new ModelAndView("stavkaZahteva/insert");
        mv.addObject("stavkaZahteva", new StavkaZahteva());
        mv.addObject("idZahteva", idZahteva);

        return mv;
    }

    @RequestMapping(value = "/insert/{idZahteva}", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("stavkaZahteva") StavkaZahteva stavkaZahteva, @PathVariable int idZahteva) {
        stavkaZahteva.setStatus(IOperation.INSERT);
        DBBroker.getInstance().connect();

        stavkaZahteva.setZahtev(ZahtevController.tekuciZahtev);
        stavkaZahteva.setOperation(IOperation.INSERT);
        int maxRbrStavke = 0;
        List<StavkaZahteva> stavke = ZahtevController.tekuciZahtev.getStavkeZahteva();
        for (StavkaZahteva s : stavke) {
            if (maxRbrStavke < s.getRbr()) {
                maxRbrStavke = s.getRbr();
            }
        }
        stavkaZahteva.setRbr(maxRbrStavke + 1);
        ZahtevController.tekuciZahtev.getStavkeZahteva().add(stavkaZahteva);
        for (StavkaZahteva sp : ZahtevController.tekuciZahtev.getStavkeZahteva()) {
            System.out.println(sp.getOpis());
        }
        return "redirect:/stavkaZahteva/find/" + idZahteva;
    }

    @RequestMapping(value = "/delete/{idZahteva}/{rbrStavke}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable int idZahteva, @PathVariable int rbrStavke) {

        StavkaZahteva stavkaZahetva = vratiStavkuZahteva(idZahteva, rbrStavke);

        ModelAndView mv = new ModelAndView("stavkaZahteva/delete");
        mv.addObject("idZahteva", idZahteva);
        mv.addObject("stavkaZahetva", stavkaZahetva);
        return mv;
    }

    @RequestMapping(value = "/delete/{idZahteva}/{rbrStavke}", method = RequestMethod.POST)
    public String delete_post(@PathVariable int idZahteva, @PathVariable int rbrStavke) {
        StavkaZahteva stavkaZaheta = vratiStavkuZahteva(idZahteva, rbrStavke);
        stavkaZaheta.setStatus(IOperation.DELETE);
        return "redirect:/stavkaZaheta/find/" + idZahteva;
    }

    private StavkaZahteva vratiStavkuZahteva(int idZahteva, int rbrStavke) {
        List<StavkaZahteva> stavke = ZahtevController.tekuciZahtev.getStavkeZahteva();
        for (StavkaZahteva sp : stavke) {
            if (sp.getRbr() == rbrStavke) {
                return sp;
            }
        }
        return null;
    }

    @RequestMapping(value = "/details/{idZahteva}/{rbrStavke}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable int idZahteva, @PathVariable int rbrStavke) {
        DBBroker.getInstance().connect();
        StavkaZahteva stavkaZahteva = vratiStavkuZahteva(idZahteva, rbrStavke);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("stavkaZahteva/details");
        mv.addObject("idZahteva", idZahteva);
        mv.addObject("stavkaZahteva", stavkaZahteva);
        return mv;
    }

    @RequestMapping(value = "/update/{idZahteva}/{rbrStavke}", method = RequestMethod.GET)
    public ModelAndView update_get(@PathVariable int idZahteva, @PathVariable int rbrStavke) {
        DBBroker.getInstance().connect();
        StavkaZahteva stavkaZahteva = vratiStavkuZahteva(idZahteva, rbrStavke);

        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("stavkaZahteva/update");
        mv.addObject("stavkaZahteva", stavkaZahteva);
        mv.addObject("rbrStavke", rbrStavke);
        mv.addObject("idZahteva", idZahteva);

        return mv;
    }

    @RequestMapping(value = "/update/{idZahteva}/{rbrStavke}", method = RequestMethod.POST)
    public String update_post(@PathVariable int idZahteva,
            @PathVariable int rbrStavke,
            @ModelAttribute("stavkaZahteva") StavkaZahteva stavkaZahteva) {
        DBBroker.getInstance().connect();
        stavkaZahteva.setRbr(rbrStavke);
        stavkaZahteva.setStatus(IOperation.UPDATE);

        ZahtevController.update(ZahtevController.tekuciZahtev, stavkaZahteva);
        for (StavkaZahteva sp : ZahtevController.tekuciZahtev.getStavkeZahteva()) {
            System.out.println("Status stavke  update post stavke: " + sp.getStatus());
        }
        return "redirect:/stavkaZahteva/find/" + idZahteva;
    }

}
