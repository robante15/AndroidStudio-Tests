package com.robante15.ordeneseinventario;


public class Product {
    private int id;
    private String descripcion;
    private String barcode;
    private double costo;
    private double precio;
    private String image;

    public Product(int id, String descripcion, String barcode, double costo, double precio, String image) {
        this.id = id;
        this.descripcion = descripcion;
        this.barcode = barcode;
        this.costo = costo;
        this.precio = precio;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getCosto() {
        return costo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImage() {
        return image;
    }
}
