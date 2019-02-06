package es.javy.pruebas_sqlite.model;

/**
 * Created by javy on 3/8/18.
 */

public class Product {
    private int id;
    private String JuegoNombre;
    private String JuegoExpansion;
    private int ExpansionTiempo;
    private int ExpansionMinJugadores;
    private int ExpansionMaxJugadores;
    private int ExpansionOptimoJugadores;
    private String ExpansionReglas;
    private String ExpansionFaqs;
    private String ExpansionImagen;
    private String JuegoDescripcion;

    public Product(int id, String juegoNombre, String juegoExpansion, int expansionTiempo, int expansionMinJugadores, int expansionMaxJugadores, int expansionOptimoJugadores, String expansionReglas, String expansionFaqs, String expansionImagen, String juegoDescripcion) {
        this.id = id;
        JuegoNombre = juegoNombre;
        JuegoExpansion = juegoExpansion;
        ExpansionTiempo = expansionTiempo;
        ExpansionMinJugadores = expansionMinJugadores;
        ExpansionMaxJugadores = expansionMaxJugadores;
        ExpansionOptimoJugadores = expansionOptimoJugadores;
        ExpansionReglas = expansionReglas;
        ExpansionFaqs = expansionFaqs;
        ExpansionImagen = expansionImagen;
        JuegoDescripcion = juegoDescripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJuegoNombre() {
        return JuegoNombre;
    }

    public void setJuegoNombre(String juegoNombre) {
        JuegoNombre = juegoNombre;
    }

    public String getJuegoExpansion() {
        return JuegoExpansion;
    }

    public void setJuegoExpansion(String juegoExpansion) {
        JuegoExpansion = juegoExpansion;
    }

    public int getExpansionTiempo() {
        return ExpansionTiempo;
    }

    public void setExpansionTiempo(int expansionTiempo) {
        ExpansionTiempo = expansionTiempo;
    }

    public int getExpansionMinJugadores() {
        return ExpansionMinJugadores;
    }

    public void setExpansionMinJugadores(int expansionMinJugadores) {
        ExpansionMinJugadores = expansionMinJugadores;
    }

    public int getExpansionMaxJugadores() {
        return ExpansionMaxJugadores;
    }

    public void setExpansionMaxJugadores(int expansionMaxJugadores) {
        ExpansionMaxJugadores = expansionMaxJugadores;
    }

    public int getExpansionOptimoJugadores() {
        return ExpansionOptimoJugadores;
    }

    public void setExpansionOptimoJugadores(int expansionOptimoJugadores) {
        ExpansionOptimoJugadores = expansionOptimoJugadores;
    }

    public String getExpansionReglas() {
        return ExpansionReglas;
    }

    public void setExpansionReglas(String expansionReglas) {
        ExpansionReglas = expansionReglas;
    }

    public String getExpansionFaqs() {
        return ExpansionFaqs;
    }

    public void setExpansionFaqs(String expansionFaqs) {
        ExpansionFaqs = expansionFaqs;
    }

    public String getExpansionImagen() {
        return ExpansionImagen;
    }

    public void setExpansionImagen(String expansionImagen) {
        ExpansionImagen = expansionImagen;
    }

    public String getJuegoDescripcion() {
        return JuegoDescripcion;
    }

    public void setJuegoDescripcion(String juegoDescripcion) {
        JuegoDescripcion = juegoDescripcion;
    }
}