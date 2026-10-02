package org.example.model;

public class Categoria  {
    private int idcategoria;
    private String categoria;

    public Categoria() {
    }

    public Categoria(int idcategoria, String categoria) {
        super();
        this.idcategoria = idcategoria;
        this.categoria = categoria;
    }
    public int getIdcategoria() {
        return this.idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return this.idcategoria + " - " + this.categoria;
    }
}

