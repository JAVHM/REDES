/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test3;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

/**
 *
 * @author javan
 */
public class Dijkstra {
    //Encontrar la POSICION de la distancia minima
    public Integer minDistanciaIndex(Integer dist[], Boolean b[], Integer matriz[][])
    {
        int min = Integer.MAX_VALUE, index = -1;
        for(int i = 0; i < matriz.length; i++)
        {
            if(b[i] == false && dist[i] <= min)
            {
                min = dist[i];
                index = i;
            } 
        }
        return index;
    }
    
    public void printMatriz(Integer dist[], int x, int iteracion, int origen, Integer distF[])
    {
        System.out.println("Iteracion: " + (iteracion+1) );
        for(int i = 0; i < x; i++)
        {
            if(dist[i] != Integer.MAX_VALUE)
            {
                if(dist[i] < distF[i])
                {
                    System.out.println("Nodo " + origen + " --> " + "Nodo " + i + ": " + dist[i]);
                }
                else
                {
                    System.out.println("Nodo " + origen + " --> " + "Nodo " + i + ": " + distF[i]);
                }
            }
        }
    }
    
    public void Djisktra(Integer matriz[][], int origen)
    {
        Integer distancias[] = new Integer[matriz.length];
        Integer distanciasFinales[] = new Integer[matriz.length];
        Boolean yaUsados[] = new Boolean[matriz.length];
        
        //Inicializar todo
        for(int i = 0; i<matriz.length; i++)
        {
            distancias[i] = Integer.MAX_VALUE;
            distanciasFinales[i] = Integer.MAX_VALUE;
            yaUsados[i] = false;//No repetir de index
        }
        
        distancias[origen] = 0;//Distancia del nodo origen al nodo origen
        System.out.println("Iteracion: " + 0 );
        System.out.println("Nodo " + origen + " --> " + "Nodo " + origen + ": " + 0);
        for(int iteracion = 0; iteracion<matriz.length; iteracion++)
        {
            int indexMenor = minDistanciaIndex(distancias, yaUsados, matriz);
            yaUsados[indexMenor] = true;
            for(int j = 0; j < matriz.length; j++)
            {
                if(yaUsados[j] == false && matriz[indexMenor][j]!= 0 && distancias[indexMenor]!=Integer.MAX_VALUE && matriz[indexMenor][j] <= distancias[j])
                {
                    distancias[j] = distancias[indexMenor] + matriz[indexMenor][j];
                    if(distancias[j] < distanciasFinales[j])
                    {
                        distanciasFinales[j] = distancias[j];
                    }
                }
            }
            printMatriz(distancias,matriz.length,iteracion,origen ,distanciasFinales);
        }
    }
    
    
    public static Integer[][] readMatrix()
    {
        try {
            //Lee el archivo txt según su posición en la PC
            Scanner input = new Scanner(new File("C:\\Users\\javan\\OneDrive\\Escritorio\\Test.txt"));
            while (input.hasNextInt()) 
            {
                //Primer input = dimensiones del arreglo
                int size = input.nextInt();
                Integer[][] matriz = new Integer[size][size];
                //ahora en bucle los siguientes inputs
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < size; j++)
                    {
                        try{
                            matriz[i][j] = input.nextInt();
                        }
                        catch (java.util.NoSuchElementException e) {
                            //e.printStackTrace();
                        }
                    }
                }
                //imprimir la matriz recién creada
                System.out.println("La matriz es: ");
                for (int i = 0; i < size; i++) 
                {
                    for (int j = 0; j < size; j++) 
                    {
                        System.out.printf("%1d ", matriz[i][j]);
                    }
                    System.out.println();
                }
                return matriz;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        
        //System.out.print("¿Con cuantos nodos contará el sistema?: ");
        //Scanner scanner = new Scanner(System.in);
        //int numeroDeNodos = scanner.nextInt();
        //CrearMatriz(numeroDeNodos);
        //ImprimirMatriz(CrearMatriz(numeroDeNodos));
        //ImprimirMatriz(CrearMatrizPrefab());
        //read();
        
        Dijkstra p = new Dijkstra();
        p.Djisktra(readMatrix(), 0);
    }
}
