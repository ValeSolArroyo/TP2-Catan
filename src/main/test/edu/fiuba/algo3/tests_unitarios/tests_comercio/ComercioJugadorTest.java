package edu.fiuba.algo3.tests_unitarios.tests_comercio;

import edu.fiuba.algo3.modelo.comercio.Banca;
import edu.fiuba.algo3.modelo.comercio.ComercioInterno;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComercioJugadorTest {
    @Test
    public void test01ComercioConLaBancaEntrega4RecursosYRecibe1() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos", "verde");
        Recurso madera = new Madera();
        Recurso grano = new Grano();

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());

        Banca banca = new Banca(madera, grano);

        // Act & Assert
        assertDoesNotThrow(() -> banca.ejecutar(jugador));
    }

    @Test
    public void test02ComercioConLaBancaFallaSiNoTiene4Recursos() {
        // Arrange
        Jugador jugador = new Jugador(2, "Maria", "azul");
        Recurso lana = new Lana();
        Recurso ladrillo = new Ladrillo();

        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Lana());

        Banca banca = new Banca(lana, ladrillo);

        // Act & Assert
        assertThrows(RecursosInsuficientesError.class, () -> banca.ejecutar(jugador));
    }

    @Test
    public void test03ComercioEntreJugadoresIntercambiaCorrectamente() {
        // Arrange
        Jugador oferente = new Jugador(3, "Juan", "rojo");
        Jugador aceptante = new Jugador(4, "Ana", "amarillo");

        Recurso madera = new Madera();
        Recurso grano = new Grano();
        oferente.recibirRecurso(new Madera());
        aceptante.recibirRecurso(new Grano());

        ComercioInterno comercio = new ComercioInterno(oferente, List.of(madera), List.of(grano)
        );

        // Act & Assert
        assertDoesNotThrow(() -> comercio.ejecutar(aceptante));
    }


    @Test
    public void test04ComercioEntreJugadoresFallaSiAceptanteNoTieneLoPedido() {
        // Arrange
        Jugador oferente = new Jugador(5, "Pedro", "blanco");
        Jugador aceptante = new Jugador(6, "Sofia", "violeta");

        Recurso grano = new Grano();
        Recurso madera = new Madera();

        oferente.recibirRecurso(new Madera());
        // Aceptante NO tiene Grano

        ComercioInterno comercio = new ComercioInterno(oferente, List.of(madera), List.of(grano));

        // Act & Assert
        assertThrows(RecursosInsuficientesError.class, () -> comercio.ejecutar(aceptante));
    }

    @Test
    public void test05ComercioEntreJugadoresFallaSiOferenteNoTieneLoQueOfrece() {
        // Arrange
        Jugador oferente = new Jugador(7, "Luis", "naranja");
        Jugador aceptante = new Jugador(8, "Elena", "gris");

        Recurso lana = new Lana();
        Recurso mineral = new Mineral();

        // Oferente NO tiene lana
        aceptante.recibirRecurso(new Mineral());

        ComercioInterno comercio = new ComercioInterno(oferente, List.of(lana), List.of(mineral));

        // Act & Assert
        assertThrows(RecursosInsuficientesError.class, () -> comercio.ejecutar(aceptante));
    }
}
