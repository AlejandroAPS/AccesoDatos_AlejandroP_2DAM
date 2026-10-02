package org.example.model;

public class Producto {
    private int idproducto;
    private String medida;
    private String nombre;
    private int precio;
    private int stock;

    public Producto() { }

    public Producto(int idproducto, String medida, String nombre, int precio, int stock) {
        super();
        this.idproducto = idproducto;
        this.medida = medida;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }


    public int getIdproducto() {
        return this.idproducto;
    }
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }
    public String getMedida() {
        return this.medida;
    }
    public void setMedida(String medida) {
        this.medida = medida;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrecio() {
        return this.precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getStock() {
        return this.stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return idproducto + ", medida=" + medida + ", nombre=" + nombre + ", precio=" + precio
                + ", stock=" + stock;
    }

}

