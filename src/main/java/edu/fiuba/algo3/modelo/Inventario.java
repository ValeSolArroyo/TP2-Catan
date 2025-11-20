package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.recursos.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Inventario {
    private int madera;
    private int ladrillo;
    private int lana;
    private int grano;
    private int mineral;

    public Inventario() {
        this.madera = 0;
        this.ladrillo = 0;
        this.lana = 0;
        this.grano = 0;
        this.mineral = 0;
    }

    // Agragar recursos (Double Dispatch Ida)

    public void agregar(Recurso recurso) {
        recurso.agregarseA(this);
    }

    public void sumarMadera() { this.madera++; }
    public void sumarLadrillo() { this.ladrillo++; }
    public void sumarLana() { this.lana++; }
    public void sumarGrano() { this.grano++; }
    public void sumarMineral() { this.mineral++; }

    // Quitar recursos (Double Dispatch Vuelta)

    public void quitar(Recurso recurso) {
        recurso.restarseDe(this);
    }

    public void restarMadera() {
        if (this.madera > 0) this.madera--;
    }
    public void restarLadrillo() {
        if (this.ladrillo > 0) this.ladrillo--;
    }
    public void restarLana() {
        if (this.lana > 0) this.lana--;
    }
    public void restarGrano() {
        if (this.grano > 0) this.grano--;
    }
    public void restarMineral() {
        if (this.mineral > 0) this.mineral--;
    }

    // Gastar recursos

    public void gastarRecursosPoblado() {
        if (madera < 1 || ladrillo < 1 || lana < 1 || grano < 1) {
            throw new RecursosInsuficientesError("Faltan recursos para construir Poblado");
        }
        madera--;
        ladrillo--;
        lana--;
        grano--;
    }

    public void gastarRecursosCiudad() {
        if (grano < 2 || mineral < 3) {
            throw new RecursosInsuficientesError("Faltan recursos para construir Ciudad");
        }
        grano -= 2;
        mineral -= 3;
    }

    public void gastarRecursosCarretera() {
        if (madera < 1 || ladrillo < 1) {
            throw new RecursosInsuficientesError("Faltan recursos para construir Carretera");
        }
        madera--;
        ladrillo--;
    }


    // Logica de juego

    public int cantidadTotal() {
        return madera + ladrillo + lana + grano + mineral;
    }

    public void descartarMitad() {
        int total = cantidadTotal();
        if (total <= 7) return;

        int aDescartar = total / 2;
        List<Recurso> misRecursos = materializarRecursos();
        Collections.shuffle(misRecursos); // descarte aleatorio

        for (int i = 0; i < aDescartar; i++) {
            Recurso r = misRecursos.get(i);
            quitar(r);
        }
    }

    public Recurso quitarRecursoAlAzar() {
        List<Recurso> misRecursos = materializarRecursos();
        if (misRecursos.isEmpty()) {
            return new NullRecurso();
        }
        Random random = new Random();
        Recurso robado = misRecursos.get(random.nextInt(misRecursos.size()));
        quitar(robado);
        return robado;
    }

    // Convierte los contadores numericos en una lista de objetos para poder operar con Random/Collections
    private List<Recurso> materializarRecursos() {
        List<Recurso> lista = new ArrayList<>();
        for (int i = 0; i < madera; i++) lista.add(new Madera());
        for (int i = 0; i < ladrillo; i++) lista.add(new Ladrillo());
        for (int i = 0; i < lana; i++) lista.add(new Lana());
        for (int i = 0; i < grano; i++) lista.add(new Grano());
        for (int i = 0; i < mineral; i++) lista.add(new Mineral());
        return lista;
    }
}