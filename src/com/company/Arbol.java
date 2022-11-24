package com.company;

import com.company.Nodo;

import java.util.ArrayList;
import java.util.HashMap;
public class Arbol {

    public HashMap<Character,String> dic = new HashMap<Character, String>();
    public HashMap<Character,Nodo> content = new HashMap<Character,Nodo>();
    public Nodo NYT;
    public static Nodo root;


    public Arbol(){
        root = new Nodo(null,null,null,'└',"-1",107,0);
        NYT=root;
        char comillaDoble = 34;
        char comillaSimple = 39;
        char alfabeto[] = {' ','!',comillaDoble, '#', '$', '%', '&',comillaSimple ,'(', ')', '*','+', ',', '-', '.', '/', '0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','[',']','^','_','`','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','{','|','}','~', '\\','Á','É','Í','Ó','Ú','Ñ','á','é','í','ó','ú','ñ'
        };
        for ( int i = 0 ; i < 107 ; i++){
            String code=Integer.toBinaryString(i);
            while(code.length()<8){
                code='0'+code;
            }
            //System.out.println( alfabeto[i] );

            dic.put( alfabeto[i] ,code);
        }
    }

    public String InsertarSimbolo(char symbol ,String out) {
        if (content.isEmpty()){
            out += dic.get(symbol);
            root.derecha = new Nodo(root ,null,null,symbol,"1",NYT.id - 1,1);
            content.put(symbol,NYT.derecha);
            root.izquierda = new Nodo(root ,null,null,'♠',"0",NYT.id - 2,0);
            root.peso = 1;
            NYT = root.izquierda;
        }
        else if ( content.containsKey(symbol) ){
            Nodo SNode = content.get(symbol);
            out += SNode.code;
            SNode.setPeso(SNode.peso + 1);
            Actualizar(SNode);
        }
        else {      //El simbolo no se ha agregado al arbol binario
            if(dic.get(symbol)==null){ //Si el simbolo no esta en el                                           alfabeto termina el programa
                System.out.println("Símbolo encontrado genera error de compresión.");
                System.exit(0);
            }
            out = out + NYT.code + dic.get(symbol);
            NYT.derecha = new Nodo(NYT ,null ,null ,symbol ,NYT.code+"1" ,NYT.id - 1 ,1);
            content.put(symbol,NYT.derecha);
            NYT.izquierda = new Nodo(NYT ,null ,null ,'♠' ,NYT.code+"0"  ,NYT.id - 2 ,0);
            NYT = NYT.izquierda;
            Actualizar(NYT.parent.derecha);

        }
        return out;
    }

    public void Actualizar(Nodo cur){
        Nodo r = root;
        Boolean alg1 = false;
        Incrementar();
        while( !(r.derecha == cur || r.izquierda == cur) ) {
            if(r.derecha.symbol != '♠'){
                if(r.derecha.id > cur.id && cur.peso > r.derecha.peso){
                    alg1=true;

                    char temp0 = cur.symbol;
                    cur.symbol = r.derecha.symbol;
                    r.derecha.symbol = temp0;

                    Integer temp2 = cur.peso;
                    cur.peso = r.derecha.peso;
                    r.derecha.peso = temp2;

                    content.put(r.derecha.symbol,r.derecha);
                    content.put(cur.symbol,cur);
                    Incrementar();
                    break;
                }
                r = r.izquierda;
            }
            else if(r.izquierda.symbol != '♠'){
                if(r.izquierda.id > cur.id && cur.peso > r.izquierda.peso){
                    alg1=true;

                    char temp0 = cur.symbol;
                    cur.symbol = r.izquierda.symbol;
                    r.izquierda.symbol = temp0;

                    Integer temp2 = cur.peso;
                    cur.peso = r.izquierda.peso;
                    r.izquierda.peso = temp2;

                    content.put(r.izquierda.symbol,r.izquierda);
                    content.put(cur.symbol,cur);
                    Incrementar();
                    break;
                }
                r = r.derecha;
            }
        }

        if (alg1) return;
        while(cur != root){
            if(cur.parent.izquierda.peso > cur.parent.derecha.peso ){

                Nodo temp = cur.parent.izquierda;
                cur.parent.izquierda = cur.parent.derecha;
                cur.parent.derecha = temp;

                Integer temp1 = cur.parent.izquierda.id;
                cur.parent.izquierda.id = cur.parent.derecha.id;
                cur.parent.derecha.id = temp1;

                FixCode(cur);
                return;
            }
            cur = cur.parent;
        }
    }

    public void Incrementar(){
        Nodo r = NYT;
        while ( !(r ==  root ) ) {
            r.parent.peso = r.parent.izquierda.peso + r.parent.derecha.peso;
            r = r.parent;
        }
    }

    public void FixCode(Nodo cur){
        if(cur.parent.derecha.symbol == '♠')  cur = cur.parent.derecha;
        else cur = cur.parent.izquierda;
        while ( cur != NYT ) {
            cur = cur.izquierda;
            if(cur.parent.derecha.symbol == '♠')  cur = cur.parent.derecha;
            else cur = cur.parent.izquierda;
        }
    }


    // Imprime las caracteristicas de un nodo
    public void PrintNode(Nodo P){
        System.out.print("[");
        System.out.print(P.symbol);
        System.out.print(",");
        System.out.print(P.id);
        System.out.print(",");
        System.out.print(P.peso);
        System.out.print("]");
        System.out.println();
    }


    public void PrintTree(Nodo n) {
        //Impresion recursiva infija del arbol binario
        if (n != null) {
            PrintTree(n.izquierda);
            PrintNode(n);
            PrintTree(n.derecha);
        }
    }
}