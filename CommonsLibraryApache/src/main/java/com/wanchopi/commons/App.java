package com.wanchopi.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;


/**
 * Ejemplo de la libreria commons-io de Apache
 *
 */
public class App {
	
    public static void main( String[] args ) {
    	
    	File file = new File("files/text_file.txt");
    	File copy_file = new File("files/copy_text_file.txt");
    	String msg = JOptionPane.showInputDialog("Introduce un texto");
    	
    	InputStream input = null;
    	InputStream input2 = null;
    	OutputStream output = null;
    	Reader reader = null;
    	
    	try {
    		// escribe un texto en el archivo que se pasa como argumento
			FileUtils.writeStringToFile(file, msg, "utf8");
			
			// lee el archivo que se le pasa como argumento
			String content = FileUtils.readFileToString(file, "utf8");
			System.out.println(content);
			System.out.println("=======================================");
			
			// hace una copia de un fichero
			input = new FileInputStream(file);
			output = new FileOutputStream(copy_file);
			IOUtils.copy(input, output);
			
			// comprueba que son iguales
			input = new FileInputStream(file);
			input2 = new FileInputStream(copy_file);
			if (IOUtils.contentEquals(input, input2)){
				System.out.println("Los ficheros son iguales");	
			} else {
				System.out.println("Los ficheros son distintos");
			}
			System.out.println("=======================================");
			// Cerramos los Stream de la prueba
			input.close();
			input2.close();
			output.close();
			
			// Leemos un stream de texto a través de un Iterator
			reader = new FileReader(copy_file);
			LineIterator fileIte = IOUtils.lineIterator(reader);
			try {
				while (fileIte.hasNext()) {
					String line = fileIte.nextLine(); 
					// Realizamos el tratamiento de la línea
					System.out.println(line);
				}
			} finally {
				fileIte.close();
				reader.close();
			}
			System.out.println("=======================================");
			
			// Averiguamos el número de líneas de un Stream de texto.
			reader = new FileReader(file);
			List<String> list1 = IOUtils.readLines(reader);
			System.out.println("El fichero tiene " + list1.size() + " lineas.");
			reader.close();
			System.out.println("=======================================");
			
			// Compara la fecha de creación de dos ficheros
			if (FileUtils.isFileNewer(file, copy_file)){
				System.out.println("El archivo " + file.getName() + " es más reciente");
			} else {
				System.out.println("El archivo "+ copy_file.getName() + " es más reciente");
			}
			System.out.println("=======================================");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
