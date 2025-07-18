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

    //1.1
    @Test
    public void testCrearSistemaDeGestion() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    //1.2
    @Test
    public void testRegistrarSala() {
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.error1().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 2", 0);
        assertEquals(Retorno.error2().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 3", -1);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    //1.3
    @Test
    public void testEliminarSala() {
        Retorno r = miSistema.eliminarSala("Sa 1");
        assertEquals(Retorno.error1().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.eliminarSala("Sa 1");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    //1.4
    @Test
    public void testRegistrarEvento() {
        Retorno r = miSistema.registrarSala("Sa 1", 1);
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
        r = miSistema.registrarSala("Sa 2", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("c3c", "cc", 10, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.error3().resultado, r.resultado);
        //prueba falta aforo
        r = miSistema.registrarEvento("c4c", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    //1.5
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

    //1.6
    @Test
    public void testComprarEntrada() {

        //prueba ok
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("53752389", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //prueba error1
        r = miSistema.comprarEntrada("00000000", "cc");
        assertEquals(Retorno.error1().resultado, r.resultado);
        //prueba error2
        r = miSistema.comprarEntrada("53752389", "zz");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    //1.7
    @Test
    public void testEliminarEvento() {
        //prueba ok
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.eliminarEvento("cc");
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Prueba error 1
        r = miSistema.eliminarEvento("cc");
        assertEquals(Retorno.error1().resultado, r.resultado);

        //Prueba error 2
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("53752389", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.eliminarEvento("cc");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }
    
    //1.8
    @Test
    public void testDevolverEntrada() {
        //prueba ok
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("53752389", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverEntrada("53752389", "cc");
        //prueba error1
        r = miSistema.devolverEntrada("00000000", "cc");
        assertEquals(Retorno.error1().resultado, r.resultado);
        //prueba error2
        r = miSistema.devolverEntrada("53752389", "zz");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    //1.9
    @Test
    public void testCalificarEvento() {
        //prueba error1 
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("53752389", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.calificarEvento("00000000", "cc", 4, "Comentario");
        assertEquals(Retorno.error1().resultado, r.resultado);
        //prueba error2
        r = miSistema.calificarEvento("53752389", "zz", 4, "Comentario");
        assertEquals(Retorno.error2().resultado, r.resultado);
        //prueba error3
        r = miSistema.calificarEvento("53752389", "cc", 40, "Comentario");
        assertEquals(Retorno.error3().resultado, r.resultado);
        r = miSistema.calificarEvento("53752389", "cc", 0, "Comentario");
        assertEquals(Retorno.error3().resultado, r.resultado);
        //prueba ok
        r = miSistema.calificarEvento("53752389", "cc", 4, "Comentario");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //prueba error 4
        r = miSistema.calificarEvento("53752389", "cc", 4, "Comentario");
        assertEquals(Retorno.error4().resultado, r.resultado);
    }

    //2.1
    @Test
    public void testListarSalas() {
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 2", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 3", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sa 4", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("Sa 4-1#Sa 3-1#Sa 2-1#Sa 1-1", r.valorString);
    }

    //2.2
    @Test
    public void testListarEventos() {
        Retorno r = miSistema.registrarSala("Sala Roja", 40);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.registrarEvento("CUC22", "Comidazul", 5, LocalDate.of(1950, 2, 10));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("TKURA", "Cos", 8, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("P93DF", "wdqqddq", 23, LocalDate.of(1977, 5, 9));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.listarEventos();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("CUC22-Comidazul-Sala Roja-40-0#P93DF-wdqqddq-Sala Roja-40-0#TKURA-Cos-Sala Roja-40-0", r.valorString);
    }

    //2.3
    @Test
    public void testListarClientes() {
        Retorno r = miSistema.registrarCliente("44444444", "Mariana");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22222222", "Rodrigo");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.listarClientes();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("22222222-Rodrigo#44444444-Mariana", r.valorString);
    }

    //2.4
    @Test
    public void testEsSalaOptima() {
        String[][] vistaSala = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "X", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "O", "#"},
            {"#", "O", "O", "O", "X", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "O"},
            {"#", "X", "X", "X", "O", "O", "X"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "#", "#", "O", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(vistaSala);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("No es óptimo", r.valorString);

        String[][] vistaSala2 = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "X", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "X", "#"},
            {"#", "O", "O", "O", "O", "O", "#"},
            {"#", "O", "X", "O", "O", "O", "O"},
            {"#", "O", "X", "X", "O", "O", "X"},
            {"#", "X", "X", "O", "X", "O", "#"},
            {"#", "X", "X", "O", "X", "O", "#"},
            {"#", "#", "#", "O", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };

        r = miSistema.esSalaOptima(vistaSala2);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("Es óptimo", r.valorString);
    }

    //2.5
    @Test
    public void testListarClientesEvento() {
        //error 1
        Retorno r = miSistema.listarClientesDeEvento("zz", 2);
        assertEquals(Retorno.error1().resultado, r.resultado);
        //error 2
        r = miSistema.registrarSala("Sa 1", 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 3, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.listarClientesDeEvento("cc", 0);
        assertEquals(Retorno.error2().resultado, r.resultado);
        //prueba ok
        r = miSistema.registrarCliente("11111111", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22222222", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("33333333", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.listarClientesDeEvento("cc", 2);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("33333333-Pepe#22222222-Pepe", r.valorString);
        r = miSistema.listarClientesDeEvento("cc", 4);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("33333333-Pepe#22222222-Pepe#11111111-Pepe", r.valorString);
    }

    //2.6
    @Test
    public void testColaEvento() {
        Retorno r = miSistema.registrarSala("Sa 1", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("aa", "aa", 1, LocalDate.of(1995, 4, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        //prueba ok
        r = miSistema.registrarCliente("11111111", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22222222", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("33333333", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "aa");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "aa");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarEsperaEvento();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("aa-33333333#cc-22222222#cc-33333333", r.valorString);
    }

    //2.7
    @Test
    public void testDeshacerUltimasCompras() {
        Retorno r = miSistema.registrarSala("Sa 1", 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 3, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("aa", "aa", 2, LocalDate.of(1995, 4, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        //prueba ok
        r = miSistema.registrarCliente("11111111", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22222222", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("33333333", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "aa");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "aa");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.deshacerUtimasCompras(4);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("aa-22222222#aa-33333333#cc-22222222#cc-33333333", r.valorString);
    }

    //2.8
    @Test
    public void testEventoMejorPuntuado() {
        Retorno r = miSistema.registrarSala("Sa 1", 11);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("CUC25", "Comidaverde", 10, LocalDate.of(1951, 3, 11));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("TKUFA", "Corredor", 8, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("ARUFA", "Marina", 10, LocalDate.of(1990, 9, 12));
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.registrarCliente("11111111", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22222222", "Lucrecia");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "TKUFA");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "CUC25");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "ARUFA");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "TKUFA");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "CUC25");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "ARUFA");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.calificarEvento("11111111", "TKUFA", 2, "P");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.calificarEvento("11111111", "CUC25", 2, "P");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.calificarEvento("11111111", "ARUFA", 2, "P");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.calificarEvento("22222222", "TKUFA", 2, "P");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.calificarEvento("22222222", "CUC25", 2, "P");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.calificarEvento("22222222", "ARUFA", 1, "P");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.eventoMejorPuntuado();
        assertEquals(Retorno.ok().resultado, r.resultado);
        assertEquals("CUC25-2.0#TKUFA-2.0", r.valorString);
    }

    //2.9
    @Test
    public void testComprasDeCliente() {
        Retorno r = miSistema.registrarSala("Sa 1", 11);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22233333", "Lucrecia");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("CUC25", "Comidaverde", 10, LocalDate.of(1951, 3, 11));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("TKUFA", "Corredor", 8, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.comprarEntrada("22233333", "TKUFA");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22233333", "CUC25");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverEntrada("22233333", "CUC25");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprasDeCliente("22233333");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("TKUFA-N#CUC25-D", r.valorString);
    }

    //2.10
    @Test
    public void testComprasPorDia() {
        Retorno r = miSistema.registrarSala("Sa 1", 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 3, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("aa", "aa", 2, LocalDate.of(1995, 4, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        //prueba ok
        r = miSistema.registrarCliente("11111111", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("22222222", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("33333333", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("11111111", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("22222222", "aa");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("33333333", "aa");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.comprasXDia(6);
        assertEquals(Retorno.ok().resultado, r.resultado);
        assertEquals(LocalDate.now().getDayOfMonth() + "-5", r.valorString);
    }
}