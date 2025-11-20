module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;

    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.modelo;
    exports edu.fiuba.algo3.modelo.tableroFactory;
    exports edu.fiuba.algo3.modelo.terrenos;
    exports edu.fiuba.algo3.modelo.recursos;
    exports edu.fiuba.algo3.modelo.excepciones;
    exports edu.fiuba.algo3.modelo.construcciones;
}