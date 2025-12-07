package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FabricaCartas {
    public static List<CartaDesarrollo> crearMazo() {
        List<CartaDesarrollo> mazo = new ArrayList<>();

        /*for (int i = 0; i < 14; i++) {
            mazo.add(new Caballero());

        }

        for (int i = 0; i < 5; i++) {
            mazo.add(new PuntoVictoria());
        }

        for (int i = 0; i < 2; i++) {
            mazo.add(new ProgresoMonopolio());
            mazo.add(new ProgresoDescubrimiento());
            mazo.add(new ProgresoConstruccion());
        }

        Collections.shuffle(mazo);*/

        //mazo.add(new ProgresoMonopolio());
        mazo.add(new ProgresoDescubrimiento());

        return mazo;
    }
}
