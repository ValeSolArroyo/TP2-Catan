package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private final int id;
    private String tipoTerreno;
    private Integer numeroFicha;
    private List<Integer> hexagonosAdyacentes;
    private List<Vertice> vertices;

    public Hexagono(int id, String tipoTerreno, Integer numeroFicha) {
        this.id = id;
        this.tipoTerreno = tipoTerreno;
        this.numeroFicha = numeroFicha;
        this.hexagonosAdyacentes = new ArrayList<>();
        this.vertices = new ArrayList<>(6);
    }

    public void agregarAdyacente(int idVecino) {
        if (!hexagonosAdyacentes.contains(idVecino)) {
            hexagonosAdyacentes.add(idVecino);
        }
    }

    public void agregarVertice(Vertice vertice) {
        if (!vertices.contains(vertice) && vertices.size() < 6) {
            vertices.add(vertice);
        }
    }

    public boolean esDesierto() {
        return tipoTerreno.equals("Desierto");
    }

    public String producirRecurso(Ladron ladron) {
        if (ladron != null && ladron.estaEn(this)) {
            return null;
        }

        if (esDesierto()) {
            return null;
        }

        String tipo = tipoTerreno.toLowerCase();
        String recurso;
        switch (tipo) {
            case "bosque":
                recurso = "Madera";
                break;
            case "colina":
                recurso = "Ladrillo";
                break;
            case "pastizal":
                recurso = "Lana";
                break;
            case "campo":
                recurso = "Grano";
                break;
            case "montaña":
                recurso = "Mineral";
                break;
            default:
                recurso = null;
                break;
        }

        return recurso;
    }

    public int obtenerId() {
        return id;
    }

    public String obtenerTipoTerreno() {
        return tipoTerreno;
    }

    public Integer obtenerNumeroFicha() {
        return numeroFicha;
    }

    public List<Vertice> obtenerVertices() {
        return vertices;
    }

    public List<Integer> obtenerAdyacentes() {
        return hexagonosAdyacentes;
    }
}
