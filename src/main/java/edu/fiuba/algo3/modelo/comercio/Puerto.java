package edu.fiuba.algo3.modelo.comercio;

public interface Puerto {
    default int tasaMadera() { return 4; }
    default int tasaLadrillo() { return 4; }
    default int tasaLana() { return 4; }
    default int tasaGrano() { return 4; }
    default int tasaMineral() { return 4; }
}
