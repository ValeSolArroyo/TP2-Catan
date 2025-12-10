package edu.fiuba.algo3.tests_unitarios.tests_comercio;

import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.comercio.puertos.PuertoGenerico;
import edu.fiuba.algo3.modelo.comercio.puertos.PuertoEspecial;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Lana;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComercioPuertoTest {

    @Test
    public void test01ComerciarConPuertoGenerico3x1() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos", Color.BLUE);
        Vertice vertice = new Vertice();
        PuertoGenerico puertoGenerico = new PuertoGenerico();
        vertice.asignarPuerto(puertoGenerico);

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Madera madera3 = new Madera();
        Ladrillo ladrilloDeseado = new Ladrillo();

        jugador.recibirRecurso(madera1);
        jugador.recibirRecurso(madera2);
        jugador.recibirRecurso(madera3);

        // Act
        jugador.comerciarConPuerto(vertice, List.of(madera1, madera2, madera3), ladrilloDeseado);

        // Assert
        assertDoesNotThrow(() -> {
            jugador.entregarRecursos(List.of(ladrilloDeseado));
        });
    }

    @Test
    public void test02ComerciarConPuertoGenericoFallaCon2Recursos() {
        // Arrange
        Jugador jugador = new Jugador(2, "Maria", Color.BLUE);
        Vertice vertice = new Vertice();
        PuertoGenerico puertoGenerico = new PuertoGenerico();
        vertice.asignarPuerto(puertoGenerico);

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Ladrillo ladrilloDeseado = new Ladrillo();

        jugador.recibirRecurso(madera1);
        jugador.recibirRecurso(madera2);

        // Act & Assert
        assertThrows(ComercioInvalidoError.class, () -> {
            jugador.comerciarConPuerto(vertice, List.of(madera1, madera2), ladrilloDeseado);
        });
    }

    @Test
    public void test03ComerciarConPuertoGenericoFallaConRecursosDiferentes() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", Color.BLUE);
        Vertice vertice = new Vertice();
        PuertoGenerico puertoGenerico = new PuertoGenerico();
        vertice.asignarPuerto(puertoGenerico);

        Madera madera = new Madera();
        Ladrillo ladrillo = new Ladrillo();
        Lana lana = new Lana();
        Grano granoDeseado = new Grano();

        jugador.recibirRecurso(madera);
        jugador.recibirRecurso(ladrillo);
        jugador.recibirRecurso(lana);

        // Act & Assert
        assertThrows(ComercioInvalidoError.class, () -> {
            jugador.comerciarConPuerto(vertice, List.of(madera, ladrillo, lana), granoDeseado);
        });
    }

    @Test
    public void test04ComerciarConPuertoEspecialMadera2x1() {
        // Arrange
        Jugador jugador = new Jugador(4, "Pedro", Color.BLUE);
        Vertice vertice = new Vertice();
        Madera maderaEspecial = new Madera();
        PuertoEspecial puertoMadera = new PuertoEspecial(maderaEspecial);
        vertice.asignarPuerto(puertoMadera);

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Ladrillo ladrilloDeseado = new Ladrillo();

        jugador.recibirRecurso(madera1);
        jugador.recibirRecurso(madera2);

        // Act
        jugador.comerciarConPuerto(vertice, List.of(madera1, madera2), ladrilloDeseado);

        // Assert
        assertDoesNotThrow(() -> {
            jugador.entregarRecursos(List.of(ladrilloDeseado));
        });
    }

    @Test
    public void test05ComerciarConPuertoEspecialFallaCon1Recurso() {
        // Arrange
        Jugador jugador = new Jugador(6, "Roberto", Color.BLUE);
        Vertice vertice = new Vertice();
        Madera maderaEspecial = new Madera();
        PuertoEspecial puertoMadera = new PuertoEspecial(maderaEspecial);
        vertice.asignarPuerto(puertoMadera);

        Madera madera = new Madera();
        Mineral mineralDeseado = new Mineral();

        jugador.recibirRecurso(madera);

        // Act & Assert
        assertThrows(ComercioInvalidoError.class, () -> {
            jugador.comerciarConPuerto(vertice, List.of(madera), mineralDeseado);
        });
    }

    @Test
    public void test06ComerciarConPuertoEspecialFallaConRecursosDiferentes() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", Color.BLUE);
        Vertice vertice = new Vertice();
        Madera maderaEspecial = new Madera();
        PuertoEspecial puertoEspecial = new PuertoEspecial(maderaEspecial);
        vertice.asignarPuerto(puertoEspecial);

        Madera madera = new Madera();
        Ladrillo ladrillo = new Ladrillo();
        Grano granoDeseado = new Grano();

        jugador.recibirRecurso(madera);
        jugador.recibirRecurso(ladrillo);

        // Act & Assert
        assertThrows(ComercioInvalidoError.class, () -> {
            jugador.comerciarConPuerto(vertice, List.of(madera, ladrillo), granoDeseado);
        });
    }

    @Test
    public void test07ComerciarConPuertoEspecialFallaConRecursoIncorrecto() {
        // Arrange
        Jugador jugador = new Jugador(5, "Ana", Color.BLUE);
        Vertice vertice = new Vertice();
        Madera maderaEspecial = new Madera();
        PuertoEspecial puertoMadera = new PuertoEspecial(maderaEspecial);
        vertice.asignarPuerto(puertoMadera);

        Ladrillo ladrillo1 = new Ladrillo();
        Ladrillo ladrillo2 = new Ladrillo();
        Grano granoDeseado = new Grano();

        jugador.recibirRecurso(ladrillo1);
        jugador.recibirRecurso(ladrillo2);

        // Act & Assert
        assertThrows(ComercioInvalidoError.class, () -> {
            jugador.comerciarConPuerto(vertice, List.of(ladrillo1, ladrillo2), granoDeseado);
        });
    }
}