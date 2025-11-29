package edu.fiuba.algo3.tests_integracion;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import edu.fiuba.algo3.modelo.excepciones.*;
import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.*;

public class TestEntrega2 {
    private Jugador jugador1, jugador2, jugador3;
    private Vertice v1, v2, vLejano, vPoblado, vertice1, vertice2;
    private Poblado poblado1, poblado2, poblado3;
    private Arista arista;
    private Ciudad ciudad;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1, "Pepe");
        jugador2 = new Jugador(2, "Juan");
        jugador3 = new Jugador(3, "Luis");
        v1 = new Vertice(); 
        v2 = new Vertice();
        vLejano = new Vertice();
        vPoblado = new Vertice();
        vertice1 = new Vertice(); 
        vertice2 = new Vertice();
        arista = new Arista(vertice1, vertice2);
        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        vertice1.agregarVecino(vertice2); 
        vertice2.agregarVecino(vertice1);
        poblado1 = new Poblado(jugador1);
        poblado2 = new Poblado(jugador2);
        poblado3 = new Poblado(jugador3);
        ciudad = new Ciudad(jugador2);
        
    }
    

    @Test
    public void test01VerificarConsumoRecursosYCorrectaColocacionCarreteraAdyacente() {
        jugador3.recibirRecurso(new Madera());
        jugador3.recibirRecurso(new Ladrillo());
        jugador3.recibirRecurso(new Lana());
        jugador3.recibirRecurso(new Grano());
    
        jugador3.agregarConstruccion(new Carretera(jugador3)); 
        jugador3.agregarConstruccion(new Carretera(jugador3)); 
        jugador3.agregarConstruccion(new Carretera(jugador3));
        jugador3.agregarConstruccion(new Carretera(jugador3)); 
    
    
        jugador3.construir(poblado3, vertice1);

        jugador3.recibirRecurso(new Madera()); 
        jugador3.recibirRecurso(new Ladrillo()); 
    
        jugador3.construir(new Carretera(jugador3), arista);

        assertThrows(RecursosInsuficientesError.class, () -> jugador3.tieneRecursos(new Madera(), 1),
            "El jugador no debería tener madera (se consumió).");
        assertThrows(RecursosInsuficientesError.class, () -> jugador3.tieneRecursos(new Ladrillo(), 1),
            "El jugador no debería tener ladrillo (se consumió).");
        
        assertTrue(arista.validarCarreteraPropia(jugador3), 
            "La arista debe tener una carretera perteneciente al jugador.");
    }

    @Test
    public void test02VerificarConsumoRecursosYReglaDistanciaPoblado() {
        v2.asignarConstruccion(new Poblado(new Jugador(9, "Enemigo"))); 
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Ladrillo());
        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Grano());

    
    
        assertThrows(ReglaDeDistanciaError.class,
            () -> jugador1.construir(poblado1, v1),
            "La construcción debe fallar porque v2 está ocupado.");

        try {
            jugador1.tieneRecursos(new Madera(), 1);
            jugador1.tieneRecursos(new Ladrillo(), 1);
            jugador1.tieneRecursos(new Lana(), 1);
            jugador1.tieneRecursos(new Grano(), 1);
        } catch (RecursosInsuficientesError e) {
            fail("Los recursos fueron gastados cuando la regla de distancia falló.");
        }
    
        jugador1.agregarConstruccion(new Carretera(jugador1)); 
        jugador1.agregarConstruccion(new Carretera(jugador1)); 
        jugador1.agregarConstruccion(new Carretera(jugador1)); 
        jugador1.agregarConstruccion(new Carretera(jugador1));

        jugador1.entregarRecursos(List.of(new Madera(), new Ladrillo(), new Lana()));

        assertThrows(RecursosInsuficientesError.class,
            () -> jugador1.construir(poblado1, vLejano),
            "Debe fallar porque faltan 3 recursos (M, L, La) y la validación individual falla.");
    
        try {
            jugador1.tieneRecursos(new Grano(), 1); 
            assertThrows(RecursosInsuficientesError.class,
                () -> jugador1.tieneRecursos(new Madera(), 1),
                "Madera no debería existir después del primer gasto.");
            
        } catch (RecursosInsuficientesError e) {
            fail("El recurso restante fue gastado cuando la validación de costo falló.");
        }
    }

    @Test
    public void test03VerificarConsumoRecursosAlMejorarACiudadYCambioPV() {
        
        jugador2.agregarConstruccion(new Carretera(jugador2));
        jugador2.agregarConstruccion(new Carretera(jugador2)); 
        jugador2.agregarConstruccion(new Carretera(jugador2)); 
        jugador2.agregarConstruccion(new Carretera(jugador2)); 
        
        
        jugador2.recibirRecurso(new Madera());
        jugador2.recibirRecurso(new Ladrillo());
        jugador2.recibirRecurso(new Lana());
        jugador2.recibirRecurso(new Grano());

        jugador2.construir(poblado2, vPoblado);
        
        jugador2.conseguirPuntosDeVictoria();
        assertTrue(jugador2.obtenerPuntosDeVictoria() == 1, "Debe empezar con 1 PV.");
        
        jugador2.recibirRecurso(new Grano());
        jugador2.recibirRecurso(new Grano());
        jugador2.recibirRecurso(new Mineral());
        jugador2.recibirRecurso(new Mineral());
        jugador2.recibirRecurso(new Mineral());
        
        jugador2.construir(ciudad, vPoblado);

        assertThrows(RecursosInsuficientesError.class,
                     () -> jugador2.tieneRecursos(new Grano(), 1),
                     "El inventario no quedó vacío tras el gasto de la Ciudad.");
                     
        jugador2.conseguirPuntosDeVictoria();
        assertTrue(jugador2.obtenerPuntosDeVictoria() == 2, 
                     "La mejora a Ciudad debe resultar en 2 PV.");
    }

    @Test
    public void test04ComercioMaritimoAplicaBienTasas() {
    }

    @Test
    public void test05JugadorIntercambiaConJugador() {
    }

    @Test
    public void test06ConsumoRecursosComprarCartaDesrrolloYMazoOculto() {
    }

    @Test
    public void test07CartaDesarrolloCompradaEnTurnoNoSePuedeJugar() {
    }
}
