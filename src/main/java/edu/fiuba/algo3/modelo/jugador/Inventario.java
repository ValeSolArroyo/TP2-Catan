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

    public void consumirRecurso(List<Recurso> listaRecursos) {
        if (!listaRecursos.isEmpty()) {
            List<Recurso> listaCopiaSeguridad = new ArrayList<>(recursos);
            for (Recurso recurso : listaRecursos) {
                this.eliminarRecurso(recurso, listaCopiaSeguridad);
            }
            this.recursos = listaCopiaSeguridad;
        }
    }

    public void eliminarRecurso(Recurso recursoAEliminar, List<Recurso> listaCopiaSeguridad){
        for (Recurso recursoInventario: listaCopiaSeguridad){
            if (recursoInventario.coincideCon(recursoAEliminar)){
                listaCopiaSeguridad.remove(recursoInventario);
                return;
            }
        }
        throw new RecursosInsuficientesError("El recurso " + recursoAEliminar + " no está en el inventario.");
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

    public void validarRecursos(Recurso recursoAValidar, int cantidadNecesaria) {
        int cantidadAcumulada = 0;
        for (Recurso recurso : recursos) {
            if (recurso.coincideCon(recursoAValidar)) {
                cantidadAcumulada++;
            }
        }

        if (cantidadAcumulada < cantidadNecesaria) {
            throw new RecursosInsuficientesError("Se requieren " + cantidadNecesaria + " recursos y solo hay " + cantidadAcumulada
            );
        }
    }

    public Recurso quitarRecursoAlAzar() {
        int indice = (int) (Math.random() * recursos.size());
        Recurso robado = recursos.get(indice);
        recursos.remove(indice);
        return robado;
    }
}

