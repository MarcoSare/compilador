package interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

import analizadores.lexico.alfabeto;
import analizadores.lexico.token;
import tblSimbolos.simbolo;
import tblSimbolos.tblSimbolo;

public class IntMain extends JFrame implements ActionListener { // Extension de Interfaz y Eventos

    // Declaracion de Atributos para la interfaz
    JFrame frame = new JFrame("Chat Frame");
    JTextField tf = new JTextField(20); // Longitud de 20 caracteres
    JButton compilar = new JButton("Compilar");
    JButton buscar = new JButton("Buscar");
    JTextArea ta = new JTextArea(); // Editor de codigo
    JTextArea ta2 = new JTextArea(); // Editor de codigo
    

    JMenuBar mb = new JMenuBar();
    JPanel panel = new JPanel(); // Opciones de compilacion desde archivos
    JMenu m1 = new JMenu("Archivo");
    JMenu m2 = new JMenu("Equipo3");
    tblSimbolo tablaSimbolos = new tblSimbolo();
    /* 
    public static void main(String args[]) { // Ejecutable
        main obj = new main();
        obj.initGui();
    }*/
    
    public void initGui(){
        ta2.setText("\n \n \n");
        // Configuracion del Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla Completa
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); // Pantalla centrada

        // MenuBar y creacion del sus componentes
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");


        mb.add(m1);
        mb.add(m2);
        m1.add(m11);
        m1.add(m22);
        

        // Creacion del Panel
        JLabel label = new JLabel("Seleccionar codigo fuente ");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(buscar);
        panel.add(compilar);

        mb.add(panel);

        // Configuracion del editor de texto
        Font font = new Font("Monospaced", Font.BOLD, 17);
        ta.setFont(font);
        ta.setForeground(Color.CYAN); // Letra
        ta.setBackground(Color.BLACK); // Fondo
        JScrollPane sp = new JScrollPane(ta); // Scroll del editor
        sp.setBounds(10,50,400,300);
        add(sp);


        ta2.setFont(font);
        ta2.setForeground(Color.BLACK); // Letra
        ta2.setBackground(Color.white); // Fondo
        JScrollPane spr = new JScrollPane(ta2); // Scroll del editor
        spr.setBounds(10,50,400,300);
        add(spr);

        // Se agregan los componentes al Frame en la posicion adecuada
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        //frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, sp);
        frame.getContentPane().add(BorderLayout.SOUTH, spr);


        // Acciones de click para los botones
        compilar.addActionListener(this);
        buscar.addActionListener(this);

        frame.setVisible(true); // Se muestra la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        
        // Control de Eventos (clicks del usuario)
        Object click = e.getSource();
        if (click == buscar) {
            // Interfaz de la ventana desplegable para seleccionar un archivo
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Seleccionar codigo fuente");
            jfc.setAcceptAllFileFilterUsed(false); // Limitar extensiones de los archivos
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto plano", "txt", "e");
            jfc.addChoosableFileFilter(filter);
            int returnValue = jfc.showOpenDialog(null);

            // Cuando seleccionamos un archivo correcto
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //System.out.println(jfc.getSelectedFile().getPath());
                String texto = "", full = "", url ="";
                try {
                    url = jfc.getSelectedFile().getPath();
                    tf.setText(url); // Localizacion
                    // Creamos un archivo FileReader que obtiene lo que tenga el archivo
                    FileReader lector = new FileReader(url);
                    //El contenido de lector se guarda en un BufferedReader
                    BufferedReader contenido = new BufferedReader(lector);
                    //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                    while((texto=contenido.readLine())!=null) { // Mediante saltos de linea
                        //System.out.println(texto);
                        full += texto+"\n"; // Concatena cada linea y agrega un salto
                    }
                    ta.setText(full);
                } catch (Exception ex) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, ex.getCause(), "Exception", JOptionPane.PLAIN_MESSAGE);
                }
            }

        } else if(click == compilar){
            tablaSimbolos = new tblSimbolo();
            JOptionPane.showMessageDialog(null, "Analisis...", "Compilando", JOptionPane.PLAIN_MESSAGE);
            alfabeto alfa = new alfabeto();
            boolean bandAlf=true;
            //System.out.println("l " + lineas.length);
            String[]lineas = ta.getText().split("\n");
            int li;
            for( li=0;lineas.length>li&&bandAlf;li++)
               if(!alfa.validar(lineas[li]))
               bandAlf=false;

            if(bandAlf){
                String text ="";
                token t = new token();
                    for(int i=0;lineas.length>i;i++){
                        String[] s = t.getListTokens(lineas[i]);
                        text +=(i+1)+": ";
                        for(int j=0;s.length>j;j++){
                            String[] d =  s[j].split(","); 
                            tablaSimbolos.m_agreSimbolo(new simbolo(d));
                            text+= d[0] +" ";
                        }
                        text+= "\n";
                    }
                    
                ta2.setText(text);
                mostrarTabla();

            }
            else 
            ta2.setText("Caracteres indifidos linea: " + li);
           
        }

    }
    void mostrarTabla(){
        System.out.println("id      numToken            Token           Descripcion");
        System.out.println("---------------------------------------------------------");
        for(int i=0;tablaSimbolos.tamanio()>i;i++){
            simbolo row = tablaSimbolos.getSimbolo(i);
            System.out.println((i+1)+ "     "+row.getId()+"         "+ row.getToken()+"         "+row.getDescripcion());
        }
    }
}