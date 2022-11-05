package com.company;

public class Nodo {

    public Nodo parent = null;
    public Nodo izquierda = null;
    public Nodo derecha = null;

    public char symbol;
    public String code;
    public int id;
    public int peso;

    public Nodo(Nodo parent, Nodo izquierda, Nodo derecha, char symbol, String code, int id, int peso) {
        this.parent = parent;
        this.izquierda = izquierda;
        this.derecha = derecha;

        this.symbol = symbol;
        this.code = code;
        this.id = id;
        this.peso = peso;
    }

    public Nodo(Nodo parent) {
        this.parent = parent;
    }


    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}