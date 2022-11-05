package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args){
        System.out.println( "Ingrese la cadena de texto a comprimir");
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();
        System.out.println( Compress(texto) );

    }

    public static String Compress(String S){
        Arbol comp = new Arbol ();
        String out = "";
        for(int i = 0 ; i < S.length() ; ++i){
            out = S.charAt(i) + comp.InsertarSimbolo(S.charAt(i) ,out);
        }
        Nodo P = comp.root;
        comp.PrintTree(P);
        return "Compreso";
    }


}
