/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.banconegocio.controlador;


import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancodominio.Transaccion;
import banco.banconegocio.excepciones.NegocioException;
import banco.bancopersistencia.conexion.controladores.ControlPersistencia;
import banco.bancopersistencia.dtos.*;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author luiis
 */
public class ControlNegocio implements IControlNegocio{

    ControlPersistencia controlPers= new ControlPersistencia();
    private static final Logger LOG =Logger.getLogger(ControlNegocio.class.getName());
    
    @Override
    public Cliente buscarClientePorId(int id) throws NegocioException {
        Cliente clienteBuscado;
        try{
            clienteBuscado=this.controlPers.buscarClientePorId(id);
            return clienteBuscado;
        }catch(PersistenciaException e){
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws NegocioException {
        List<Cliente> lista;
        try{
            lista=this.controlPers.consultarClientes();
            return lista;
        }catch(PersistenciaException e){
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public int insertarCliente(String nombre, String apellidoP, String apellidoM,
            String fecha_nacimiento,  String usuario, String contra) throws NegocioException {
        Date fechaNac=Date.valueOf(fecha_nacimiento);
        int edad=this.calcularEdad(fechaNac);
        ClienteDTO cliente=new ClienteDTO(nombre,apellidoP,apellidoM,fechaNac,edad);
        try {
            int id=this.controlPers.insertarCliente(cliente,usuario,contra);
            return id;
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    private int calcularEdad(Date fecha_nacimiento){
        Calendar hoy=Calendar.getInstance();
        Calendar fechaNac=Calendar.getInstance();
        fechaNac.setTime(fecha_nacimiento);
        int edad =hoy.get(Calendar.YEAR)-fechaNac.get(Calendar.YEAR);
        if(hoy.get(Calendar.DAY_OF_YEAR)<fechaNac.get(Calendar.DAY_OF_YEAR))
            edad--;
        return edad;
    }
    
    @Override
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero)
            throws NegocioException{
        int codigo_direccion;
        try {
            codigo_direccion=this.controlPers.agregarDireccionCliente(calle, colonia, codigo_postal, numero);
            return codigo_direccion;
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }
    
    @Override
    public void actualizarCliente(int idCliente, String nombre, String apellidoPaterno, String apellidoM,
            String fecha_nacimiento, int idDireccion) throws NegocioException {
        Date fechaNac=Date.valueOf(fecha_nacimiento);
        int edad=this.calcularEdad(fechaNac);
        Cliente cliente=new Cliente(idCliente,nombre,apellidoPaterno,apellidoM,
                fechaNac,edad, idDireccion);
        try {
            this.controlPers.actualizarCliente(cliente);
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Cuenta consultarCuentaEspecifica(String numCuenta) throws NegocioException {
        Cuenta cuentaCons;
        try {
            cuentaCons=this.controlPers.consultarCuentaEspecifica(numCuenta);
            return cuentaCons;
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public String obtenerNumCuenta(int idCliente)throws NegocioException{
        String numero;
        try {
            numero=this.controlPers.obtenerNumCuenta(idCliente);
            return numero;
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }
    
    @Override
    public List<Cuenta> consultarTodasCuentas() throws NegocioException {
        List<Cuenta> lista;
        try {
            lista=this.controlPers.consultarTodasCuentas();
            return lista;
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void insertarCuenta(String numCuenta) throws NegocioException {
        Date fechaCreacion=new Date(System.currentTimeMillis());
        CuentaDTO cuenta =new CuentaDTO(numCuenta, "activa", new BigDecimal(0), fechaCreacion);
        try {
            this.controlPers.insertarCuenta(cuenta);
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void cancelarCuenta(String numCuenta)throws NegocioException{
        try{
            this.controlPers.cancelarCuenta(numCuenta);
        }catch(PersistenciaException e){
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public int procesarTransaccion(Transaccion transaccion) throws NegocioException {
        int id;
        try {
            id=this.controlPers.procesarTransaccion(transaccion);
            return id;
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void procesarTransferencia(int idTransaccion, String numCuentaDestino) throws NegocioException {
        try {
            this.controlPers.procesarTransferencia(idTransaccion, numCuentaDestino);
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra) throws NegocioException {
        try {
            this.controlPers.procesarRetiroSinCuenta(idTransaccion, folio, contra);
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizarDireccionCliente(int idDireccion, String calle, String colonia, String codigo_postal, 
            String numero) throws NegocioException {
        try {
            this.controlPers.actualizarDireccion(idDireccion, calle, colonia, codigo_postal, numero);
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public int autenticarUsuario(String usuario, String contra) throws NegocioException {
        int idCliente;
        try {
            idCliente=this.controlPers.autenticarUsuario(usuario, contra);
            return idCliente;
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public int obtenerIdDireccionCliente(int idCliente) throws NegocioException {
        int idDireccion;
        try{
            idDireccion=this.controlPers.obtenerIdDireccion(idCliente);
            return idDireccion;
        }catch(PersistenciaException e){
            LOG.log(Level.SEVERE,"algo salio mal",e);
            throw new NegocioException(e.getMessage(),e.getCause());
        }
    }

  
    
}