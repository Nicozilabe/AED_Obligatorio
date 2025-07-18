package sistemaAutogestion;

import dominio.Calificacion;
import dominio.Cliente;
import dominio.Entrada;
import dominio.EstadisticaDia;
import dominio.Evento;
import dominio.Sala;
import java.time.LocalDate;

import org.w3c.dom.events.Event;

import tads.ListaN;
import tads.ListaO;
import tads.Nodo;
import tads.Pila;

public class Sistema implements IObligatorio {

    // prueba
    private ListaO<Cliente> Clientes;
    private ListaO<Evento> Eventos;
    private ListaN<Sala> Salas;
    private Pila<Entrada> EntradasCompradas;
    private ListaO<Evento> MejoresEventos;

    public Sistema() {
        Clientes = new ListaO<>();
        Eventos = new ListaO<>();
        Salas = new ListaN<>();
        EntradasCompradas = new Pila<>();
        MejoresEventos = new ListaO<>();

    }

    @Override
    public Retorno crearSistemaDeGestion() {
        Clientes = new ListaO<>();
        Eventos = new ListaO<>();
        Salas = new ListaN<>();
        MejoresEventos = new ListaO<>();
        EntradasCompradas = new Pila<>();
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
        Cliente c = new Cliente(cedula, "");
        Evento e = new Evento(codigoEvento, "", 0, LocalDate.now());
        c = Clientes.obtenerElemento(c);
        e = Eventos.obtenerElemento(e);
        if (c == null) {
            return Retorno.error1();
        }
        if (e == null) {
            return Retorno.error2();
        }
        Entrada entrada = new Entrada(c, e);
        e.comprarEntrada(entrada);
        EntradasCompradas.apilar(entrada);
        return Retorno.ok();

    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        Evento e = new Evento(codigo, "", 0, LocalDate.now());
        e = Eventos.obtenerElemento(e);
        if (e == null) {
            return Retorno.error1();
        }
        if (e.getEntradasCantVendidas() > 0) {
            return Retorno.error2();
        }

        e.getSala().eliminarEvento(e);
        Eventos.eliminarElemento(e);
        return Retorno.ok();

    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        Cliente c = new Cliente(cedula, "");
        Evento e = new Evento(codigoEvento, "", 0, LocalDate.now());

        e = Eventos.obtenerElemento(e);
        c = Clientes.obtenerElemento(c);
        if (c == null) {
            return Retorno.error1();
        }
        if (e == null) {
            return Retorno.error2();
        }
        Entrada entrada = new Entrada(c, e);
        e.devolverEntrada(entrada);
        return Retorno.ok();

    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        Cliente c = new Cliente(cedula, "");
        Evento e = new Evento(codigoEvento, "", 0, LocalDate.now());
        c = Clientes.obtenerElemento(c);
        e = Eventos.obtenerElemento(e);
        if (c == null) {
            return Retorno.error1();
        }
        if (e == null) {
            return Retorno.error2();
        }
        if (puntaje < 1 || puntaje > 10) {
            return Retorno.error3();
        }
        Calificacion calificacion = new Calificacion(puntaje, comentario, e, c);
        if (e.calificarEvento(calificacion)) {
            if (MejoresEventos.cantidadElementos() == 0) {
                MejoresEventos.agregarDato(e);
            } else {
                Nodo<Evento> actual = MejoresEventos.getNodoInicio();
                boolean agregado = false;
                while (actual != null && !agregado) {
                    Evento a = actual.getDato();
                    if (a.getPuntaje() < e.getPuntaje()) {
                        MejoresEventos.vaciar();
                        MejoresEventos.agregarDato(e);
                        agregado = true;
                    }
                    if (a.getPuntaje().equals(e.getPuntaje()) && !a.equals(e)) {
                        if (!MejoresEventos.existeElemento(e)) {
                            MejoresEventos.agregarDato(e);
                        }
                        agregado = true;
                    }
                    if (a.getPuntaje() > e.getPuntaje()) {
                        MejoresEventos.eliminarElemento(e);
                        agregado = true;
                    }
                    actual = actual.getSiguiente();
                }

            }
            return Retorno.ok();
        } else {
            return Retorno.error4();
        }

    }

    @Override
    public Retorno listarSalas() {
        String ret = Salas.mostrarInverso();
        return Retorno.ok(ret);
    }

    @Override
    public Retorno listarEventos() {
        String ret = Eventos.mostrar();
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
                if (!(vistaSala[f][c].equals("#"))) {
                    if (vistaSala[f][c].equals("X")) {
                        cantLibres++;
                        if (cantOcupadosConsecutivosAux > cantOcupadosConsecutivos) {
                            cantOcupadosConsecutivos = cantOcupadosConsecutivosAux;
                            cantOcupadosConsecutivosAux = 0;
                        }
                    }
                    if (vistaSala[f][c].equals("O")) {

                        cantOcupadosConsecutivosAux++;
                    }
                }
            }
            if (cantOcupadosConsecutivosAux > cantOcupadosConsecutivos) {
                cantOcupadosConsecutivos = cantOcupadosConsecutivosAux;
            }
            if (cantOcupadosConsecutivos > cantLibres) {
                cantColsOptima++;
            }

        }
        if (cantColsOptima > 1) {
            return Retorno.ok("Es óptimo");
        } else {
            return Retorno.ok("No es óptimo");
        }

    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        Evento e = new Evento(código, "", 0, LocalDate.now());
        e = Eventos.obtenerElemento(e);
        if (e == null) {
            return Retorno.error1();
        }
        if (n < 1) {
            return Retorno.error2();
        }
        String ret = e.mostrarNUltclientes(n);
        return Retorno.ok(ret);
    }

    @Override
    public Retorno listarEsperaEvento() {

        ListaO<Entrada> EntradasEspera = new ListaO<>();
        if (!Eventos.esVacia()) {
            Nodo<Evento> actual = Eventos.getNodoInicio();
            while (actual != null) {
                Nodo<Entrada> nodo = actual.getDato().getColaEspera().getNodoFrente();

                while (nodo != null) {
                    Entrada entrada = nodo.getDato();
                    if (entrada != null) {
                        EntradasEspera.agregarDato(entrada);
                    }
                    nodo = nodo.getSiguiente();
                }
                actual = actual.getSiguiente();

            }
        }
        return Retorno.ok(EntradasEspera.mostrar());
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        ListaO<Entrada> EntradasDevueltas = new ListaO<>();
        while (n > 0 && !EntradasCompradas.estaVacia()) {
            Entrada ultimaEntrada = EntradasCompradas.desapilar();
            Evento evento = ultimaEntrada.getEvento();
            evento.devolverEntrada(ultimaEntrada);
            EntradasDevueltas.agregarDato(ultimaEntrada);
            n--;
        }
        return Retorno.ok(EntradasDevueltas.mostrar());
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        String ret = "";
        Nodo<Evento> actual = MejoresEventos.getNodoInicio();
        while (actual != null) {

            Evento e = actual.getDato();
            if (actual.getSiguiente() == null) {
                ret += e.getCodigo() + "-" + e.getPuntaje();
            } else {
                ret += e.getCodigo() + "-" + e.getPuntaje() + "#";
            }

            actual = actual.getSiguiente();

        }
        return Retorno.ok(ret);
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        Cliente c = new Cliente(cedula, "");
        c = Clientes.obtenerElemento(c);
        if (c == null) {
            return Retorno.error1();
        }
        String ret = c.MostrarEntradasCompradas();

        return Retorno.ok(ret);
    }

    // @Override
    // public Retorno comprasXDia(int mes) {
    // if( mes < 1 || mes > 12) {
    // return Retorno.error1();
    // }
    // String ret = "";
    // Nodo<Entrada> actual = EntradasCompradas.getNodoTope();
    // int[] cantComprasPorDia = new int[32];
    // while (actual != null) {
    // Entrada e = actual.getDato();
    // if (e.getFecha().getMonthValue() == mes) {
    // cantComprasPorDia[e.getFecha().getDayOfMonth()] += 1;
    // }
    // actual = actual.getSiguiente();
    // }
    // for (int i = 1; i < cantComprasPorDia.length; i++) {
    // if (cantComprasPorDia[i] > 0) {
    // if (ret == "") {
    // ret += i + "-" + cantComprasPorDia[i];
    // } else {
    // ret += "#" + i + "-" + cantComprasPorDia[i];
    // }
    // }
    // }
    // return Retorno.ok(ret);
    // }

    @Override
    public Retorno comprasXDia(int mes) {
        if (mes < 1 || mes > 12) {
            return Retorno.error1();
        }
        String ret = "";
        Pila<Entrada> nPila = EntradasCompradas.copiarPila();
        Entrada e = nPila.desapilar();
        ListaO<EstadisticaDia> estadisticas = new ListaO<>();
        while (e != null && nPila.cantidadElementos() >= 0) {

            if (e.getFecha().getMonthValue() == mes) {
                EstadisticaDia estadistica = new EstadisticaDia(e.getFecha().getDayOfMonth(), 1);
                EstadisticaDia estadisticaExistente = estadisticas.obtenerElemento(estadistica);
                if (estadisticaExistente != null) {
                    estadisticaExistente.aumentarCantidad(1);
                } else {
                    estadisticas.agregarDato(estadistica);
                }

            }
            
            if (nPila.cantidadElementos() == 0) {
                e = null;
            } else {
            e = nPila.desapilar();
            }
        }
        Nodo<EstadisticaDia> actual = estadisticas.getNodoInicio();
        while (actual != null) {
            EstadisticaDia estadistica = actual.getDato();
            if (ret.equals("")) {
                ret += estadistica.toString();
            } else {
                ret += "#" + estadistica.toString();
            }
            actual = actual.getSiguiente();
        }
        return Retorno.ok(ret);
    }

}
