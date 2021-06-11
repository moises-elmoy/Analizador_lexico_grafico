/*
Centro Universsitario de los Altos
Ingenieria en Computacion 
Miguel Angel Sanabria
Analizador Lexico
 */

import java.io.*;
import java.util.Arrays;


public class Analizador {
    
    public static void main(String [] arg){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
       
        //Variables
        char[] operador = {'+', '-', '/', '*', '='};
        char[] caracterEspecial = {'(', ')', ',', ';', '[', ']', '{', '}'};
        char[] palabra = new char[15];
        int j = 0;
        char[] numero = new char[15];
        int x = 0;
        int punto = 0;
        
        //Error
        char[] errores = {'#', '"', '!', '?', '¡', '¿', '@', '`', '%', '&', '$'}; 
        
        System.out.println("-----------------------------------------");
        System.out.println("-----.:|Identificación de Cadenas|:.-----");
        System.out.println("-----------------------------------------");

        try {
            //Ruta absoluta del archivo txt
            archivo = new File ("E:\\6° Semestre\\Traductores de Lenguaje II\\Seminario\\AnalizadorLexico\\src\\main\\java\\Archivo.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String lineas;
            int linea;
            linea= fr.read();
            while(linea != -1) {
                for(int i=0; i<5;i++){
                    if(((char)linea == operador[i]) && (j == 0) && (x == 0)){
                        System.out.println((char)linea + " -> Es un operador ");
                    }
                }
                
                for(int i=0; i<8;i++){
                    if(((char)linea == caracterEspecial[i]) && (j == 0) && (x == 0)){
                        System.out.println((char)linea + " -> Es un caracter especial ");
                    }
                }
               
                
                if(Character.isLetter((char)linea)){
                    palabra[j++] = (char)linea;
                }else if(((char)linea == ' ' || (char)linea == '\n' || (char)linea == '\t') && (j!=0)){
                    
                    j = 0;
                    
                    if(palabraReservada(palabra)){
                        System.out.print(palabra);
                        System.out.print(" -> Es una palabra reservada");
                        System.out.println();
                    }else {
                        System.out.print(palabra);
                        System.out.print(" -> Es un identificador");
                        System.out.println();
                    }
                       
                    for (int i = 0; i < palabra.length; i++) {
                        palabra[i] = '\0';
                    }
                   
                }else if(((char)linea == '#' || (char)linea == '?' || (char)linea == '¿' || (char)linea == '!' || (char)linea == '`' 
                        || (char)linea == '¡' 
                        || (char)linea == '@' || (char)linea == '&' || (char)linea == '$' || (char)linea == '%') && (j!=0)){
                        palabra[j++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situcion = ");
                        System.out.print(palabra);
                        break;
                }else if((Character.isDigit((char)linea)) && (j!=0)){
                        palabra[j++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situcion = ");
                        System.out.print(palabra);
                        break;
                }
                
                if(((Character.isDigit((char)linea)) || (char)linea == '.')){
                    numero[x++] = (char)linea;
                    if((char)linea == '.'){
                        punto++;
                    }else if(punto > 1){
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> el numero tiene mas de un punto = ");
                        System.out.println(numero);
                        break;
                    }
                    
                }else if(((char)linea == ' ' || (char)linea == '\n' || (char)linea == '\t') && (x!=0)){
                    numero[x]='\0';
                    x = 0;
                    
                    System.out.print(numero);
                    System.out.print(" -> Es un digito");
                    System.out.println();
     
                    for (int i = 0; i < numero.length; i++) {
                        numero[i] = '\0';
                    }
                    punto = 0;
                }else if(((char)linea == '#' || (char)linea == '?' || (char)linea == '¿' || (char)linea == '!' || (char)linea == '`' 
                        || (char)linea == '¡' 
                        || (char)linea == '@' || (char)linea == '&' || (char)linea == '$' || (char)linea == '%') && (x!=0)){
                        numero[x++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situacion = ");
                        System.out.print(numero);
                        break;
                }else if((Character.isLetter((char)linea)) && (x!=0)){
                        numero[x++] = (char)linea;
                        System.out.print("ERROR ");
                        System.out.print((char)linea);
                        System.out.print(" -> Caracter invalido en esta situacion = ");
                        System.out.print(numero);
                        break;
                }
                
                linea = fr.read();

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
    }
    
     //Funcion para ver si es palabra reservada
    static boolean palabraReservada(char palabra[]){
        String[] keywords = {"main","int","float","void","char","bool","double","short","enum",
                            "try","catch","if","else","do","while","for","switch","case","delete","return",
                            "static","public","private","continue","struct"};
 
        String cadena = String.valueOf(palabra);
  
        for (int i = 0; i < 25; i++) {
            if (cadena.equals(keywords[i])) {
                return true;
            }
        }
        
        return false;
    }

    
}