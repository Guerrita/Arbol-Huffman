package com.company;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public FileInputStream in = null;

    public static void main(String[] args){
        System.out.println( "Ingrese la ruta del archivo a comprimir");
        Scanner sc = new Scanner(System.in);
        File in = new File(sc.nextLine());
        String str ="";
        try {
            FileInputStream texto = new FileInputStream(in);
            System.out.println("Tamano del archivo antes de codificar: "+in.length()+" bytes");
            //Imprime el contenido del archivo leido
            int n = -1;
            byte []b = new byte[100];
            while ((n = texto.read(b,0,50))!=-1) {
                str += new String(b,0,n,"utf-8");
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String texto =  str; //sc.nextLine();
        String Compression = Compress(texto);
        System.out.println( );
        System.out.println( "Ingrese la ruta donde se comprimira el archivo");
        sc = new Scanner(System.in);

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(sc.nextLine());
            pw = new PrintWriter(fichero);
            pw.print(Compression);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String Compress(String S){
        Arbol comp = new Arbol ();
        String out = "";
        for(int i = 0 ; i < S.length() ; ++i){
            out = comp.InsertarSimbolo(S.charAt(i) ,out);
        }
        Nodo P = comp.root;
        //comp.PrintTree(P);
        return out;
    }


}
