/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSala() {
        Retorno r = miSistema.registrarSala("Sa 1",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 1",1);
        assertEquals(Retorno.error1().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 2",0);
        assertEquals(Retorno.error2().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 3",-1);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testEliminarSala() {
        Retorno r = miSistema.eliminarSala("Sa 1");
        assertEquals(Retorno.error1().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 1",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.eliminarSala("Sa 1");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEvento() {
        Retorno r = miSistema.registrarSala("Sa 1",1);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //prueba ok
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        //prueba mismo código
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 2));
        assertEquals(Retorno.error1().resultado, r.resultado);
        //prueba sala ocupada
        r = miSistema.registrarEvento("c2c", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.error3().resultado, r.resultado);

        //prueba 2 el mismo día
        r = miSistema.registrarSala("Sa 2",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("c3c", "cc", 10, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.error3().resultado, r.resultado);
        //prueba falta aforo
        r = miSistema.registrarEvento("c4c", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarCliente() {
        Retorno r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.error2().resultado, r.resultado);
        r = miSistema.registrarCliente("537525389", "Pepe");
        assertEquals(Retorno.error1().resultado, r.resultado);
        r = miSistema.registrarCliente("5375238a", "Pepe");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testListarSalas() {
        Retorno r = miSistema.registrarSala("Sa 1",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 2",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 3",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 4",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("Sa 4-1#Sa 3-1#Sa 2-1#Sa 1-1", r.valorString);
        
    }

    @Test
    public void testListarEventos() {
        //Completar para primera entrega
    }

    @Test
    public void testListarClientes() {
        //Completar para primera entrega
    }

    @Test
    public void testEsSalaOptima() {
        //Completar para primera entrega
    }

}
