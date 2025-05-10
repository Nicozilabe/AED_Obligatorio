package sistemaAutogestion;

import dominio.Cliente;
import dominio.Evento;
import dominio.Sala;
import java.time.LocalDate;
import tads.ListaN;
import tads.ListaO;
import tads.Nodo;

public class Sistema implements IObligatorio {

    // prueba
    private ListaO<Cliente> Clientes;
    private ListaO<Evento> Eventos;
    private ListaN<Sala> Salas;

    public Sistema() {
        Clientes = new ListaO<>();
        Eventos = new ListaO<>();
        Salas = new ListaN<>();
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        Clientes = new ListaO<>();
        Eventos = new ListaO<>();
        Salas = new ListaN<>();
        return (Retorno.ok());
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        // if(nombre == null || nombre.length() == 0){
        // return Retorno.error2();
        // }
        if (capacidad <= 0) {
            return Retorno.error2();
        }
        Sala n = new Sala(nombre, capacidad);
        if (Salas.existeElemento(n)) {
            return Retorno.error1();
        }
        ;
        Salas.agregarFinal(n);
        return Retorno.ok();

    }

    @Override
    public Retorno eliminarSala(String nombre) {
        Sala s = new Sala(nombre, 1);
        boolean ret = Salas.eliminarConfirmado(s);
        if (!ret) {
            return Retorno.error1();
        } else {
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {

        if (aforoNecesario <= 0) {
            return Retorno.error2();
        }

        Evento e = new Evento(codigo, descripcion, aforoNecesario, fecha);
        
        if (Eventos.existeElemento(e)) {
            return Retorno.error1();
        }
        
        boolean fechaDisponible = false;
        
        if (!Salas.esVacia()) {
            Nodo<Sala> actual = Salas.getNodoInicio();
            while (!fechaDisponible && actual != null) {
                Sala a = actual.getDato();
                boolean salaOcupada = false;
                if (a.getCapacidad() >= aforoNecesario) {
                    ListaN<Evento> eventosSala = a.getEventos();
                    if (eventosSala == null) {
                        e.setEntradasDisponibles(a.getCapacidad());
                        e.setSala(a);
                        a.addEvento(e);
                        return Retorno.ok();
                    } else {
                        Nodo<Evento> evactual = eventosSala.getNodoInicio();
                        while (!fechaDisponible && evactual != null && !salaOcupada) {
                            if (evactual.getDato().getFecha().equals(fecha)) {
                                salaOcupada = true;
                            }
                            evactual = evactual.getSiguiente();
                        }
                    }
                    if (!salaOcupada) {
                        e.setEntradasDisponibles(a.getCapacidad());
                        e.setSala(a);
                        a.addEvento(e);
                        Eventos.agregarDato(e);
                        fechaDisponible = true;
                        return Retorno.ok();
                    }
                }
                actual = actual.getSiguiente();
            }

        }
        return Retorno.error3();

    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {

        boolean ciCorrecta = true;
        if (cedula == null || cedula.isEmpty() || cedula.length() != 8) {
            ciCorrecta = false;
            return Retorno.error1();
        }
        ;
        try {
            Long.parseLong(cedula);
        } catch (NumberFormatException e) {
            ciCorrecta = false;
            return Retorno.error1();
        }
        Cliente c = new Cliente(cedula, nombre);
        // if(Clientes.existeElemento(c)){
        // return Retorno.error2();
        // }
        // AgregardatoConfirmado verifica si el dato ya existe en la lista, nos
        // ahorramos 1 recorrida.
        if (Clientes.agregarDatoConfirmado(c)) {
            return Retorno.ok();
        } else {
            return Retorno.error2();
        }
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarSalas() {
        String ret =Salas.mostrarInverso();
        return Retorno.ok(ret);
    }

    @Override
    public Retorno listarEventos() {
        String ret =Eventos.mostrar();
        return Retorno.ok(ret);
        
    }

    @Override
    public Retorno listarClientes() {
        String ret = Clientes.mostrar();
        return Retorno.ok(ret);
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        Integer cantColsOptima = 0;
        for (int c = 0; c < vistaSala[0].length; c++) {   
             Integer cantOcupadosConsecutivos = 0;
             Integer cantLibres = 0;  
             Integer cantOcupadosConsecutivosAux = 0;  

            for (int f = 0; f < vistaSala.length; f++) {       
                if(!(vistaSala[f][c].equals("#"))){
                    if(vistaSala[f][c].equals("X")){
                        cantLibres++;
                        if(cantOcupadosConsecutivosAux > cantOcupadosConsecutivos){
                            cantOcupadosConsecutivos = cantOcupadosConsecutivosAux;
                            cantOcupadosConsecutivosAux= 0;
                        }
                    }
                    if(vistaSala[f][c].equals("O")){

                        cantOcupadosConsecutivosAux++;
                    }
                }
            }
            if(cantOcupadosConsecutivosAux > cantOcupadosConsecutivos){
                cantOcupadosConsecutivos = cantOcupadosConsecutivosAux;
            }
            if(cantOcupadosConsecutivos > cantLibres){
                cantColsOptima++;
            }

        }
        if(cantColsOptima > 1){
            return Retorno.ok("Es óptimo");
        }else{
            return Retorno.ok("No es óptimo");
        }
        
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEsperaEvento() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }

}
