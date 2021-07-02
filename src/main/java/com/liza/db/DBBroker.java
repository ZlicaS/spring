/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liza.db;

import com.liza.domain.Broj;
import com.liza.domain.Delatnost;
import com.liza.domain.Klijent;
import com.liza.domain.Mesto;
import com.liza.domain.Model;
import com.liza.domain.PotencijalniKlijent;
import com.liza.domain.Pozicija;
import com.liza.domain.StavkaZahteva;
import com.liza.domain.Ulica;
import com.liza.domain.Zahtev;
import com.liza.domain.Zaposleni;
import com.liza.util.IOperation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lizasapsaj
 */
public class DBBroker {

    private Connection connection;
    private static DBBroker instance;

    private DBBroker() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public boolean connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/vipliza";
            String username = "root";
            String password = "";
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean pokreniDBTransakciju() {
        return connect();
    }

    public int zapamtiDBTransakciju(Model model) {
        try {
            String sql = "";
            switch (model.getOperation()) {
                case IOperation.INSERT:
                    sql = model.insert();
                    System.out.println(sql);
                    break;
                case IOperation.UPDATE:
                    sql = model.update();
                    break;
                case IOperation.DELETE:
                    sql = model.delete();
                    break;
                default:
                    return 0;
            }
            Statement statement = connection.createStatement();
             System.out.println(sql);
            statement.executeUpdate(sql); //, Statement.RETURN_GENERATED_KEYS); 

            statement.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean potvrdiDBTransakciju() {
        try {
            connection.commit();
            disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ponistiDBTransakciju() {
        try {
            connection.rollback();
            disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Klijent> vratiSveKlijente() {
        try {
            List<Klijent> klijentiList = new ArrayList<>();
            Klijent klijent = new Klijent();
            String sql = klijent.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                klijent = new Klijent();
                klijent.setIdKlijent(rs.getInt("idKlijent"));
                klijent.setNaziv(rs.getString("naziv"));
                klijent.setPib(rs.getString("pib"));
                klijent.setTelefon(rs.getString("telefon"));
                klijent.setWebStrana(rs.getString("webStrana"));
                klijent.setGodinaOsnivanja(rs.getInt("godinaOsnivanja"));
                klijent.setDelatnost(vratiDelatnost(rs.getInt("delatnostId")));
                klijent.setMesto(vratiMesto(rs.getInt("mestoId")));
                klijent.setPotencijalniKlijent(vratiPotKlijenta(rs.getInt("potencijalniKlijentId")));

                klijentiList.add(klijent);
            }
            statement.close();
            return klijentiList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Klijent vratiKlijenta(int idKlijent) {
        try {
            Klijent klijent = new Klijent(idKlijent, "", "", "", "", 0, new Delatnost(), new Mesto(), new PotencijalniKlijent());
            String sql = klijent.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                klijent = new Klijent();
                klijent.setIdKlijent(rs.getInt("idKlijent"));
                klijent.setNaziv(rs.getString("naziv"));
                klijent.setPib(rs.getString("pib"));
                klijent.setTelefon(rs.getString("telefon"));
                klijent.setWebStrana(rs.getString("webStrana"));
                klijent.setGodinaOsnivanja(rs.getInt("godinaOsnivanja"));
                klijent.setDelatnost(vratiDelatnost(rs.getInt("delatnostId")));
                klijent.setMesto(vratiMesto(rs.getInt("mestoId")));
                klijent.setPotencijalniKlijent(vratiPotKlijenta(rs.getInt("potencijalniKlijentId")));
            }
            statement.close();
            return klijent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Delatnost vratiDelatnost(int delatnostId) {
        try {
            Delatnost delatnost = new Delatnost(delatnostId, "");
            String sql = delatnost.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                delatnost.setSifraDelatnosti(rs.getInt("idDelatnost"));
                delatnost.setNaziv(rs.getString("naziv"));
            }
            statement.close();
            return delatnost;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Mesto vratiMesto(int aInt) {
        try {
            Mesto mesto = new Mesto(aInt, "", null);
            String sql = mesto.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                mesto.setPostanskiBroj(rs.getInt("postanskiBroj"));
                mesto.setNaziv(rs.getString("naziv"));
                mesto.setUlica(vratiUlicu(rs.getInt("ulicaId")));
            }
            statement.close();
            return mesto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Ulica vratiUlicu(int aInt) {
        try {
            Ulica ulica = new Ulica(aInt, "", null);
            String sql = ulica.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ulica.setIdUlice(rs.getInt("idUlice"));
                ulica.setNaziv(rs.getString("naziv"));
                ulica.setBroj(vratiBroj(rs.getInt("brojId")));
            }
            statement.close();
            return ulica;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Broj vratiBroj(int aInt) {
        try {
            Broj broj = new Broj(aInt);
            String sql = broj.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                broj.setBrojAdrese(rs.getInt("brojAdrese"));

            }
            statement.close();
            return broj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PotencijalniKlijent vratiPotKlijenta(int aInt) {
        try {
            PotencijalniKlijent pk = new PotencijalniKlijent(aInt);
            String sql = pk.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                pk.setIdPotKlijenta(rs.getInt("idPotKlijenta"));

            }
            statement.close();
            return pk;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Delatnost> vratiSveDelatnosti() {
        try {
            List<Delatnost> delatnostList = new ArrayList<>();
            Delatnost delatnost = new Delatnost();
            String sql = delatnost.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                delatnost = new Delatnost();
                delatnost.setSifraDelatnosti(rs.getInt("idDelatnost"));
                delatnost.setNaziv(rs.getString("naziv"));
                delatnostList.add(delatnost);
            }
            statement.close();
            return delatnostList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Mesto> vratiSvaMesta() {
        try {
            List<Mesto> mestoList = new ArrayList<>();
            Mesto mesto = new Mesto();
            String sql = mesto.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                mesto = new Mesto();
                mesto.setPostanskiBroj(rs.getInt("postanskiBroj"));
                mesto.setNaziv(rs.getString("naziv"));
                mesto.setUlica(vratiUlicu(rs.getInt("ulicaId")));
                mestoList.add(mesto);
            }
            statement.close();
            return mestoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PotencijalniKlijent> vratiSvePotencijalneKlijente() {
        try {
            List<PotencijalniKlijent> pkList = new ArrayList<>();
            PotencijalniKlijent pk = new PotencijalniKlijent();
            String sql = pk.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                pk.setIdPotKlijenta(rs.getInt("idPotKlijenta"));
                pk.setDatum(rs.getDate("datum"));
                pk.setNaziv(rs.getString("naziv"));

                pkList.add(pk);
            }
            statement.close();
            return pkList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Zahtev> vratiSveZahteve() {
        try {
            List<Zahtev> zahteviList = new ArrayList<>();
            Zahtev zahtev = new Zahtev();
            String sql = zahtev.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                zahtev = new Zahtev();
                zahtev.setIdZahteva(rs.getInt("idZahteva"));
                zahtev.setDatum(rs.getDate("datum"));
                zahtev.setOdobreno(rs.getString("odobreno"));

                zahtev.setZaposleni1(vratiZaposlenog(rs.getInt("zaposleni1Id")));
                zahtev.setZaposleni2(vratiZaposlenog(rs.getInt("zaposleni2Id")));

               // zahtev.setStavkeZahteva(vratiStavkeZahteva(rs.getInt("idZahteva")));

                zahteviList.add(zahtev);
            }
            statement.close();
            return zahteviList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Zaposleni vratiZaposlenog(int aInt) {
        try {
            Zaposleni zaposleni = new Zaposleni(aInt, "", "", new Date(), new Pozicija());
            String sql = zaposleni.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                zaposleni.setIdZaposleni(rs.getInt("idZaposleni"));
                zaposleni.setIme(rs.getString("ime"));
                zaposleni.setPrezime(rs.getString("prezime"));
            }
            statement.close();
            return zaposleni;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private StavkaZahteva vratiStavkuZahteva(int aInt) {
        try {
            StavkaZahteva sz = new StavkaZahteva(aInt, "", new Zahtev(), 0, 0);
            String sql = sz.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sz.setRbr(rs.getInt("rbr"));
                sz.setOpis(rs.getString("opis"));

            }
            statement.close();
            return sz;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void izmeniStatusStavkeZahteva(StavkaZahteva sz, int i) {
        try {
            String sql = "UPDATE stavkazahteva SET status = " + i
                    + " WHERE  rbr = " + sz.getRbr()
                    + " AND zahtevId = " + sz.getZahtev().getIdZahteva();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean obrisiStavkuZahteva(StavkaZahteva sz) {
        try {
            String sql = sz.delete();
            Statement statement = connection.createStatement();
            System.out.println("OVDE BRISE:"+sql);
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean srediRBStavkiZahteva(int idZahteva) {
        try {
            List<StavkaZahteva> stavkeZahteva = vratiStavkeZahteva(idZahteva);
            int i = 1;
            for (StavkaZahteva s : stavkeZahteva) {
                String sql = "UPDATE stavkazahteva SET rbr = '" + i
                        + "', updateID = '0' " + " WHERE  rbr = " + s.getRbr() + " AND zahtevId = " + s.getZahtev().getIdZahteva();
                i = i + 1;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<StavkaZahteva> vratiStavkeZahteva(int idZahteva) {
        try {
            List<StavkaZahteva> stavkeZahtevaList = new ArrayList<>();
            Zahtev zahtev = new Zahtev();
            zahtev.setIdZahteva(idZahteva);
            StavkaZahteva stavkaZahteva = new StavkaZahteva();
            stavkaZahteva.setZahtev(zahtev);
            // String sql = stavkaZahteva.details();
            String sql = "SELECT * FROM stavkazahteva WHERE zahtevId='" + idZahteva + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                stavkaZahteva = new StavkaZahteva();
                stavkaZahteva.setRbr(rs.getInt("rbr"));
                stavkaZahteva.setOpis(rs.getString("opis"));
                stavkaZahteva.setZahtev(zahtev);

                stavkaZahteva.setStatus(rs.getInt("status"));

                stavkaZahteva.setUpdateID(rs.getInt("updateID"));
                stavkeZahtevaList.add(stavkaZahteva);
            }
            statement.close();
            for (StavkaZahteva sz : stavkeZahtevaList) {
                System.out.println(sz.getOpis());
            }
            return stavkeZahtevaList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Zahtev vratiZahtev(int idZahteva) {
        try {
            Zahtev zahtev = new Zahtev();
            zahtev.setIdZahteva(idZahteva);
            String sql = zahtev.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<StavkaZahteva> stavke = DBBroker.getInstance().vratiStavkeZahteva(idZahteva);
            while (rs.next()) {
                //zahtev = new Zahtev();
               // zahtev.setIdZahteva(rs.getInt("idZahteva"));
                zahtev.setDatum(rs.getDate("datum"));
                zahtev.setOdobreno(rs.getString("odobreno"));

                zahtev.setZaposleni1(vratiZaposlenog(rs.getInt("zaposleni1Id")));
                zahtev.setZaposleni2(vratiZaposlenog(rs.getInt("zaposleni2Id")));

                zahtev.setStavkeZahteva(stavke);
            }
            statement.close();
            return zahtev;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int obrisiZahtev(Zahtev zahtev) {
        try {
            for (StavkaZahteva stavkaZahteva : zahtev.getStavkeZahteva()) {
                obrisiStavkuZahteva(stavkaZahteva);
            }
            String sql = zahtev.delete();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public List<Zaposleni> vratiSveZaposlene() {
        try {
            List<Zaposleni> zaposleniList = new ArrayList<>();
            Zaposleni zaposleni = new Zaposleni();
            String sql = zaposleni.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                zaposleni = new Zaposleni();
                zaposleni.setIdZaposleni(rs.getInt("idZaposleni"));
                zaposleni.setIme(rs.getString("ime"));
                zaposleni.setPrezime(rs.getString("prezime"));
                zaposleni.setKorisnickoIme(rs.getString("korisnickoIme"));
                zaposleni.setKorisnickaSifra(rs.getString("korisnickaSifra"));
                zaposleniList.add(zaposleni);
            }
            statement.close();
            return zaposleniList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean izmeniStavkuZahteva(StavkaZahteva sp, int rbr) {
        try {
            String sql = "UPDATE stavkazahteva SET opis = " + sp.getOpis()
                    + ", status = " + sp.getStatus()
                    + " WHERE  rbr = " + rbr + " AND  zahtevId= " + sp.getZahtev().getIdZahteva();
            Statement statement = connection.createStatement();
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean postviUpdateIDSvimStavkama(int idZahteva, int i) {
        try {
            String sql = "UPDATE stavkazahteva SET updateID = " + i
                    + " WHERE zahtevId= " + idZahteva;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean srediOsnovnePodatkeZahteva(Zahtev tekuciZahtev) {
        try {

            String sql = tekuciZahtev.update();
            System.out.println("SQL UPIT SREDI: " + sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
