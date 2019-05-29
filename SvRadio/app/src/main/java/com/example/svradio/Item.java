package com.example.svradio;

public class Item {

    private String name;
    private String url;
/*
  public Client(Integer id,String nombre, String direccion, String telefono) {
            this.id = id;
            this.direccion = direccion;
            this.nombre = nombre;
            this.telefono = telefono;
        }
*/

    public Item(String name,String url) {
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}