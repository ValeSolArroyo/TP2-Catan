package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;

import java.util.*;

public class Jugador {
    private final int id;
    private final String nombre;
    private Map<Recurso, Integer> recursos;
    private List<Construccion> construcciones;

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.recursos = new HashMap<>();
        this.construcciones = new ArrayList<>();
    }

    public void recibir(Recurso recurso) {
        agregarRecursos(recurso, 1);
    }

    public void agregarRecursos(Recurso tipoRecurso, int cantidad) {
        if (recursos.containsKey(tipoRecurso)) {
            recursos.put(tipoRecurso, recursos.get(tipoRecurso) + cantidad);
        } else {
            recursos.put(tipoRecurso, cantidad);
        }
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void descartarMitadDeRecursos() {
        int total = obtenerCantidadTotalDeRecursos();
        if (total <= 7) return;

        int aDescartar = total / 2; // redondea hacia abajo
        while (aDescartar > 0 && !recursos.isEmpty()) {
            for (Recurso tipo : new ArrayList<>(recursos.keySet())) {
                if (aDescartar == 0) break;

                int cantidad = recursos.get(tipo);
                if (cantidad > 0) {
                    recursos.put(tipo, cantidad - 1);
                    aDescartar--;
                }
            }
            recursos.values().removeIf(v -> v == 0);
        }
    }

    private void eliminarRecurso(Recurso tipo, int cantidad) {
        if (!recursos.containsKey(tipo)) {
            return; // Si no tiene ese recurso, no hace nada
        }

        int actual = recursos.get(tipo);
        int nuevo = Math.max(0, actual - cantidad); // Evitamos números negativos
        recursos.put(tipo, nuevo);
    }

    public void construirPoblado(Vertice vertice) {
        eliminarRecurso(new Madera(), 1);
        eliminarRecurso(new Ladrillo(), 1);
        eliminarRecurso(new Lana(), 1);
        eliminarRecurso(new Grano(), 1);
        vertice.construirPoblado(this);

    }

    public void construirCiudad(Vertice vertice) {
        eliminarRecurso(new Grano(), 2);
        eliminarRecurso(new Mineral(), 3);
        vertice.construirCiudad(this);
    }

    public void robarCarta(Jugador victima) {
        // Obtener los tipos de recursos que la víctima tiene (cantidad > 0)
        List<Recurso> recursosDisponibles = new ArrayList<>();
        for (Map.Entry<Recurso, Integer> entry : victima.recursos.entrySet()) {
            if (entry.getValue() > 0) {
                recursosDisponibles.add(entry.getKey());
            }
        }

        // Si la víctima no tiene recursos, no se roba nada
        if (recursosDisponibles.isEmpty()) {
            return;
        }

        Random random = new Random();
        Recurso recursoRobado = recursosDisponibles.get(random.nextInt(recursosDisponibles.size()));

        victima.recursos.put(recursoRobado, victima.recursos.get(recursoRobado)-1);
        this.agregarRecursos(recursoRobado, 1);
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerCantidadDeConstrucciones() {
        return construcciones.size();
    }

    public int obtenerCantidadDeRecurso(Recurso tipoRecurso) {
        if (recursos.containsKey(tipoRecurso)) {
            return recursos.get(tipoRecurso);
        } else {
            return 0;
        }
    }

    public int obtenerCantidadTotalDeRecursos() {
        int total = 0;
        for (int cantidad : recursos.values()) {
            total += cantidad;
        }
        return total;
    }
}
