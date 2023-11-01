package test.java;

import codekeepers.controlador.Controlador;
import codekeepers.modelo.*;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class TestController {

    @Test
    public void testAddShowClientesPremium() {
        Controlador controlador = new Controlador();

        Cliente leo = controlador.addCliente("leo@mail.com", "Leo", "10101010F", "C/ Diamente 22", true);
        Cliente gin = controlador.addCliente("gin@mail.com", "Gin", "88888888S", "C/ Rubi 22", false);

        List<ClientePremium> listaClientesPremium = controlador.showClientesPremium();
        assertTrue(listaClientesPremium.contains(leo));
        assertFalse(listaClientesPremium.contains(gin));
        System.out.print("Test de showClientesPremium superado con exito!");
    }

    @Test
    public void testAddNewArticulo() {
        Controlador controlador = new Controlador();

        String nombre = "Articulo de prueba";
        String descripcion = "Descripción de prueba";
        float precio = 10.0f;
        float gastoEnvio = 2.0f;
        int tiempoPreparacion = 5;
        int stock = 20;

        Articulo nuevoArticulo = controlador.addNewArticulo(nombre, descripcion, precio, gastoEnvio, tiempoPreparacion, stock);

        assertNotNull(nuevoArticulo);
        assertEquals(nombre, nuevoArticulo.getNombre());
        assertEquals(descripcion, nuevoArticulo.getDescripcion());
        assertEquals(precio, nuevoArticulo.getPrecio(), 0.01);
        assertEquals(gastoEnvio, nuevoArticulo.getGastoEnvio(), 0.01);
        assertEquals(tiempoPreparacion, nuevoArticulo.getTiempoPreparacion());
        assertEquals(stock, nuevoArticulo.getStock());
        System.out.print("Test de addNewArticulo superado con exito!");
    }
    public static void main(String[] args) {

    }
}
