package es.example.ezroomsapp.utils;

public class Reserva {
    private String _id;
    private String  nombre;
    private String  apellidos;
    private String  dni;
    private String  email;
    private String  fechaReserva;
    private String  sala;
    private String  horasReserva;
    private String  phone;
    private String  comentario;
    private String  nPersonas;

    public Reserva() {}

    public Reserva(String _id, String nombre, String apellidos, String dni, String email, String fechaReserva, String sala, String horasReserva, String phone, String comentario, String nPersonas) {
        this._id = _id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.fechaReserva = fechaReserva;
        this.sala = sala;
        this.horasReserva = horasReserva;
        this.phone = phone;
        this.comentario = comentario;
        this.nPersonas = nPersonas;
    }

    public Reserva(String nombre, String apellidos, String dni, String email, String fechaReserva, String sala, String horasReserva, String phone, String comentario, String nPersonas) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.fechaReserva = fechaReserva;
        this.sala = sala;
        this.horasReserva = horasReserva;
        this.phone = phone;
        this.comentario = comentario;
        this.nPersonas = nPersonas;
    }

    //getters
    public String get_id() {
        return _id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getDni() {
        return dni;
    }
    public String getEmail() {
        return email;
    }
    public String getFechaReserva() {
        return fechaReserva;
    }
    public String getSala() {
        return sala;
    }
    public String getHorasReserva() {
        return horasReserva;
    }
    public String getPhone() {
        return phone;
    }
    public String getComentario() {
        return comentario;
    }
    public String getNPersonas() {
        return nPersonas;
    }

    //setters
    public void set_id(String _id) {
        this._id = _id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }
    public void setHorasReserva(String horasReserva) {
        this.horasReserva = horasReserva;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public void setNPersonas(String nPersonas) {
        this.nPersonas = nPersonas;
    }

}
