package model;

public class Producto {
    private int idproducto;
    private String nombre;
    private int idcategoria;
    private String medida;
    private int precio;
    private int stock;

    public Producto() { }

    public Producto(int idproducto, String nombre, int idcategoria,
                    String medida, int precio, int stock) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.idcategoria = idcategoria;
        this.medida = medida;
        this.precio = precio;
        this.stock = stock;
    }

    // getters y setters de los 6 atributos
    public int getIdproducto() { return idproducto; }
    public void setIdproducto(int idproducto) { this.idproducto = idproducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getIdcategoria() { return idcategoria; }
    public void setIdcategoria(int idcategoria) { this.idcategoria = idcategoria; }
    public String getMedida() { return medida; }
    public void setMedida(String medida) { this.medida = medida; }
    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return idproducto + " - " + nombre + " (cat=" + idcategoria + "), medida="
                + medida + ", precio=" + precio + ", stock=" + stock;
    }
}