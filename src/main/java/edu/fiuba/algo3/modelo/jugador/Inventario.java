package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import java.util.ArrayList;
import java.util.List;

    public class Inventario {
        private List<Recurso> recursos;
        public Inventario() {
            this.recursos = new ArrayList<>();
        }

    public void agregarRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    public void consumirRecurso(List<Recurso> recursos) {
        for (Recurso recurso: recursos) {
            recurso.eliminarDe(this);
        }
    }

    public void eliminarRecurso(String recurso){
        for (Recurso recursoInventario: recursos){
            if (recursoInventario.brinda(recurso)){
                recursos.remove(recursoInventario);
                return;
            }
        }
        throw new RecursosInsuficientesError("El recurso " + recurso + " no está en el inventario.");
    }
    
    public void descartarMitadRecursos() {
        int cantidadRecursos = recursos.size();
        if (cantidadRecursos > 7 ) {
            int cantidadADescartar = cantidadRecursos/2;
            for (int i = 0; i < cantidadADescartar; i++) {
                recursos.remove(0);
            }
        }
    }

        public void validarRecursos(Recurso recurso, int cantidad) {
            //TODO
        }

        public Recurso quitarRecursoAlAzar() {
            Recurso robado = recursos.get(0);
            recursos.remove(0);
            return robado;
        }
    }

