package edu.fiuba.algo3.tests_integracion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import edu.fiuba.algo3.modelo.excepciones.*;
import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.construcciones.*;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.comercio.*;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.*;
import javafx.scene.paint.Color;

public class TestEntrega2 {
    private Jugador jugador1, jugador2, jugador3;
    private Vertice v1, v2, vLejano, vPoblado, vertice1, vertice2;
    private Poblado poblado1, poblado2, poblado3;
    private Arista arista;
    private Ciudad ciudad;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1, "Pepe", Color.RED);
        jugador2 = new Jugador(2, "Juan", Color.BLUE);
        jugador3 = new Jugador(3, "Luis", Color.GREEN);
        v1 = new Vertice(); 
        v2 = new Vertice();
        vLejano = new Vertice();
        vPoblado = new Vertice();
        vertice1 = new Vertice(); 
        vertice2 = new Vertice();
        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        arista = new Arista(vertice1, vertice2);
        poblado1 = new Poblado(jugador1);
        poblado2 = new Poblado(jugador2);
        poblado3 = new Poblado(jugador3);
        ciudad = new Ciudad(jugador2);
    }
    

    @Test
    public void test01VerificarConsumoRecursosYCorrectaColocacionCarreteraAdyacente() {

        jugador3.construir(poblado3, vertice1); 
        jugador3.construir(new Carretera(jugador3), new Arista(new Vertice(), new Vertice()));
        jugador3.construir(new Carretera(jugador3), new Arista(new Vertice(), new Vertice())); 
        jugador3.construir(new Carretera(jugador3), new Arista(new Vertice(), new Vertice()));

        jugador3.recibirRecurso(new Madera()); 
        jugador3.recibirRecurso(new Ladrillo()); 
    
        jugador3.construir(new Carretera(jugador3), arista);

        assertThrows(RecursosInsuficientesError.class, () -> jugador3.entregarRecursos(List.of(new Madera(), new Ladrillo())),
            "El jugador no debería tener recursos (se consumieron al construir).");

        assertTrue(arista.validarCarreteraPropia(jugador3), 
            "La arista debe tener una carretera perteneciente al jugador.");
    }

    @Test
    public void test02VerificarConsumoRecursosYReglaDistanciaPoblado() {
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Ladrillo());
        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Grano());

        Jugador enemigo = new Jugador(9, "Enemigo", Color.PINK);
        enemigo.construir(new Poblado(enemigo), v2);

        assertThrows(ReglaDeDistanciaError.class,
            () -> jugador1.construir(poblado1, v1),
            "La construcción debe fallar porque v2 está ocupado.");


        try {
            jugador1.entregarRecursos(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano()));
        } catch (RecursosInsuficientesError e) {
            fail("Los recursos fueron gastados cuando la construcción falló por regla de distancia.");
        }

        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Ladrillo());
        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Grano());


        jugador1.construir(new Carretera(jugador1), new Arista(new Vertice(), new Vertice())); 
        jugador1.construir(new Carretera(jugador1), new Arista(new Vertice(), new Vertice())); 
        jugador1.construir(new Carretera(jugador1), new Arista(new Vertice(), new Vertice())); 
        jugador1.construir(new Carretera(jugador1), new Arista(new Vertice(), new Vertice())); 

        jugador1.entregarRecursos(List.of(new Madera(), new Ladrillo(), new Lana()));

        assertThrows(RecursosInsuficientesError.class,
            () -> jugador1.construir(poblado1, vLejano),
            "Debe fallar porque faltan recursos.");
    }

    @Test
    public void test03VerificarConsumoRecursosAlMejorarACiudadYCambioPV() {

        jugador2.construir(new Carretera(jugador2), new Arista(new Vertice(), new Vertice())); 
        jugador2.construir(new Carretera(jugador2), new Arista(new Vertice(), new Vertice())); 
        jugador2.construir(new Carretera(jugador2), new Arista(new Vertice(), new Vertice())); 
        jugador2.construir(poblado2, vPoblado);
        assertEquals(1, jugador2.conseguirPuntosDeVictoria(), "Debe empezar con 1 PV por el poblado.");

        jugador2.recibirRecurso(new Grano());
        jugador2.recibirRecurso(new Grano());
        jugador2.recibirRecurso(new Mineral());
        jugador2.recibirRecurso(new Mineral());
        jugador2.recibirRecurso(new Mineral());

        jugador2.construir(ciudad, vPoblado);

        assertThrows(RecursosInsuficientesError.class,
                     () -> jugador2.entregarRecursos(List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral())),
                     "Todos los recursos para la ciudad deberían haberse consumido.");

        assertEquals(2, jugador2.conseguirPuntosDeVictoria(), "La mejora a Ciudad debe resultar en 2 PV.");
    }

    @Test
    public void test04ComercioConBancaAplicaBienTasa4a1() {
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());

        Banca comercio = new Banca(new Madera(), new Ladrillo());
        comercio.ejecutar(jugador1);

        assertDoesNotThrow(() -> jugador1.entregarRecursos(List.of(new Ladrillo())));

        assertThrows(RecursosInsuficientesError.class, () -> jugador1.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test04ComercioConPuertoGenericoAplicaBienTasa3a1() {

        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Lana()); 
        jugador1.recibirRecurso(new Lana());

        Vertice verticeConPuerto = spy(new Vertice());
        ComercioPuerto puerto = new PuertoGenerico();
        doAnswer(invocation -> {
            puerto.ejecutar(invocation.getArgument(0), verticeConPuerto, invocation.getArgument(1), invocation.getArgument(2));
            return null;
        }).when(verticeConPuerto).ejecutarComercio(any(), any(), any());


        jugador1.comerciarConPuerto(verticeConPuerto, List.of(new Lana(), new Lana(), new Lana()), new Grano());

        assertDoesNotThrow(() -> jugador1.entregarRecursos(List.of(new Grano())));
        assertThrows(RecursosInsuficientesError.class, () -> jugador1.entregarRecursos(List.of(new Lana())));
    }

    @Test
    public void test04ComercioConPuertoEspecialAplicaBienTasa2a1() {
        jugador1.recibirRecurso(new Mineral());
        jugador1.recibirRecurso(new Mineral());

        Vertice verticeConPuerto = spy(new Vertice());
        ComercioPuerto puerto = new PuertoEspecial(new Mineral());
        doAnswer(invocation -> {
            puerto.ejecutar(invocation.getArgument(0), verticeConPuerto, invocation.getArgument(1), invocation.getArgument(2));
            return null;
        }).when(verticeConPuerto).ejecutarComercio(any(), any(), any());

        jugador1.comerciarConPuerto(verticeConPuerto, List.of(new Mineral(), new Mineral()), new Ladrillo());

        assertDoesNotThrow(() -> jugador1.entregarRecursos(List.of(new Ladrillo())));
        assertThrows(RecursosInsuficientesError.class, () -> jugador1.entregarRecursos(List.of(new Mineral())));
    }

    @Test
    public void test05JugadorIntercambiaConJugador() {

        jugador1.recibirRecurso(new Madera()); 
        jugador2.recibirRecurso(new Ladrillo());

        jugador2.aceptarOferta(jugador1, List.of(new Madera()), List.of(new Ladrillo()));

        assertDoesNotThrow(() -> jugador1.entregarRecursos(List.of(new Ladrillo())));
        assertThrows(RecursosInsuficientesError.class, () -> jugador1.entregarRecursos(List.of(new Madera())));

        assertDoesNotThrow(() -> jugador2.entregarRecursos(List.of(new Madera())));
        assertThrows(RecursosInsuficientesError.class, () -> jugador2.entregarRecursos(List.of(new Ladrillo())));
    }

    @Test
    public void test06ConsumoRecursosComprarCartaDesrrolloYMazoOculto() {

        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Grano());
        jugador1.recibirRecurso(new Mineral());

        CartaDesarrollo cartaComprada = mock(CartaDesarrollo.class);

        jugador1.guardarCartaDesarrollo(cartaComprada, List.of(new Lana(), new Grano(), new Mineral()));

        assertThrows(RecursosInsuficientesError.class, () -> {
            jugador1.entregarRecursos(List.of(new Lana(), new Grano(), new Mineral()));
        });
    }

    @Test
    public void test07CartaDesarrolloCompradaEnTurnoNoSePuedeJugar() {

        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Grano());
        jugador1.recibirRecurso(new Mineral());

        CartaDesarrollo cartaComprada = mock(CartaDesarrollo.class);

        doThrow(new CartaNoJugableError("La carta no puede ser jugada en este turno.")).when(cartaComprada).ejecutar(any(), any(), any(), any(), any(), any(), any());

        jugador1.guardarCartaDesarrollo(cartaComprada, List.of(new Lana(), new Grano(), new Mineral()));

        assertThrows(CartaNoJugableError.class, () -> cartaComprada.ejecutar(null, null, jugador1, null, null, null, null), "No se debería poder jugar una carta recién comprada.");
    }
}
