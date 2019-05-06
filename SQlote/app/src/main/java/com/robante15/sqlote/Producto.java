package com.robante15.sqlote;

public class Producto {
    public int codigo;
    public String  descripcion;
    public double precio;

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
            return descripcion;
        }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
