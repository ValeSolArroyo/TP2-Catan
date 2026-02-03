package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {
    private List<Recurso> recursos;
    private final Jugador jugador;

    public Inventario(Jugador jugador) {
        this.jugador = jugador;
        this.recursos = new ArrayList<>();

    }

    public void agregarRecurso(Recurso recurso) {
        recursos.add(recurso);
        jugador.notificarObservadores();
    }

    public void consumirRecurso(List<Recurso> listaRecursos) {
        if (!listaRecursos.isEmpty()) {
            List<Recurso> listaCopiaSeguridad = new ArrayList<>(recursos);
            for (Recurso recurso : listaRecursos) {
                this.eliminarRecurso(recurso, listaCopiaSeguridad);
            }
            this.recursos = listaCopiaSeguridad;
        }
        jugador.notificarObservadores();
    }

    private void eliminarRecurso(Recurso recursoAEliminar, List<Recurso> listaCopiaSeguridad){
        for (Recurso recursoInventario: listaCopiaSeguridad){
            if (recursoInventario.coincideCon(recursoAEliminar)){
                listaCopiaSeguridad.remove(recursoInventario);
                return;
            }
        }
        throw new RecursosInsuficientesError("No tiene recursos suficientes para realizar esta acción.");
    }

    public void descartarMitadRecursos() {
        int cantidadRecursos = recursos.size();
        if (cantidadRecursos > 7 ) {
            int cantidadADescartar = cantidadRecursos/2;
            for (int i = 0; i < cantidadADescartar; i++) {
                recursos.remove(0);
            }
        }
        jugador.notificarObservadores();
    }

    public Recurso quitarRecursoAlAzar() {
        int indice = (int) (Math.random() * recursos.size());
        Recurso robado = recursos.get(indice);
        recursos.remove(indice);
        jugador.notificarObservadores();
        return robado;
    }
    
    public Map<String, Integer> getRecursos() {
        List<Recurso> recursosAContar = List.of(new Madera(), new Lana(), new Mineral(), new Ladrillo(), new Grano());
        Map<String, Integer> recursosAMostrar = new HashMap<>();

        for (Recurso tipoRecurso : recursosAContar) {
            String claveRecurso = tipoRecurso.getNombreRecurso();
            recursosAMostrar.put(claveRecurso, 0);
            for (Recurso recurso : recursos) {
                if (recurso.coincideCon(tipoRecurso)) {
                    recursosAMostrar.put(claveRecurso, recursosAMostrar.get(claveRecurso) + 1);
                }
            }
        }
        return recursosAMostrar;
    }

    public List<Recurso> consumirTodosRecursos(Recurso recursoDeseado) {
        List<Recurso> listaAEntregar =  new ArrayList<>();
        List<Recurso> recursosARemover = new ArrayList<>();

        for (Recurso recurso: recursos) {
            if (recurso.coincideCon(recursoDeseado)) {
                listaAEntregar.add(recurso);
                recursosARemover.add(recurso);
            }
        }
        recursos.removeAll(recursosARemover);
        return listaAEntregar;
    }
}

