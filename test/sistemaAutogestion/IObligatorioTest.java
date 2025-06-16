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
    
    @Test
    public void testComprarEntrada(){
    
        //prueba ok
        Retorno r = miSistema.registrarSala("Sa 1",1);
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

    
    @Test
    public void testEliminarEvento(){
        //prueba ok
        Retorno r = miSistema.registrarSala("Sa 1",1);
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
    
    @Test
    public void testDevolverEntrada(){
    
    //prueba ok
        Retorno r = miSistema.registrarSala("Sa 1",1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("cc", "cc", 1, LocalDate.of(1995, 5, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("53752389", "Pepe");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarEntrada("53752389", "cc");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r= miSistema.devolverEntrada("53752389", "cc");
        //prueba error1
        r = miSistema.devolverEntrada("00000000", "cc");
        assertEquals(Retorno.error1().resultado, r.resultado);
        //prueba error2
        r = miSistema.devolverEntrada("53752389", "zz");
        assertEquals(Retorno.error2().resultado, r.resultado);
    
    }
    
}
