package interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;

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
    DefaultTableModel modelT = new DefaultTableModel(); 
    JTable tabla = new JTable(modelT); 
   
    JMenuBar menuBar = new JMenuBar(); //Barra superior del menu
    JMenu menu1 = new JMenu("Archivo");
    JMenu menu2 = new JMenu("Compilador");
    JMenu menu3 = new JMenu("Ayuda");
    JMenu menu4 = new JMenu("Acerca de");
    
    //Creacion de submenus
    JMenuItem menuItem11 = new JMenuItem("Abrir");
    JMenuItem menuItem12 = new JMenuItem("Compilar");
    JMenuItem menuItem13 = new JMenuItem("Guardar");
    JMenuItem menuItem14 = new JMenuItem("Guardar como");
    JMenuItem menuItem15 = new JMenuItem("Eliminar");

    JMenuItem menuItem21 = new JMenuItem("Limpiar editor de texto");
    JMenuItem menuItem22 = new JMenuItem("Limpiar tabla de simbolos");
    JMenuItem menuItem23 = new JMenuItem("Limpiar consola");
    JMenuItem menuItem24 = new JMenuItem("Limpiar todo");

    JMenuItem menuItem31 = new JMenuItem("Análisis léxico y sintáctico");
    JMenuItem menuItem32 = new JMenuItem("Análisis sintáctico");
    JMenuItem menuItem33 = new JMenuItem("Análisis semantico");

    JMenuItem menuItem41 = new JMenuItem("Acerca de");

    JPanel panel = new JPanel(); // Opciones de compilacion desde archivos
    tblSimbolo tablaSimbolos = new tblSimbolo();

    String url = ""; //URL para cuando se abra un archivo

    //DefaultTableModel modelo = new DefaultTableModel();
                
    /* 
    public static void main(String args[]) { // Ejecutable
        main obj = new main();
        obj.initGui();
    }*/
    
    public void initGui(){
        modelT.addColumn("Id");
        modelT.addColumn("No. Token");
        modelT.addColumn("Token");
        modelT.addColumn("Descripcion");
        modelT.addColumn("Valor");

        ta2.setText("\n \n \n");
        // Configuracion del Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla Completa
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null); // Pantalla centrada

        // Agregacion de componentes a los menus
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

        menu1.add(menuItem11);
        menu1.add(menuItem12);
        menu1.add(menuItem13);
        menu1.add(menuItem14);
        menu1.add(menuItem15);

        menu2.add(menuItem21);
        menu2.add(menuItem22);
        menu2.add(menuItem23);
        menu2.add(menuItem24);

        menu3.add(menuItem31);
        menu3.add(menuItem32);
        menu3.add(menuItem33);

        menu4.add(menuItem41);

        menuItem11.addActionListener(this);
        menuItem12.addActionListener(this);
        menuItem13.addActionListener(this);
        menuItem14.addActionListener(this);
        menuItem15.addActionListener(this);

        menuItem21.addActionListener(this);
        menuItem22.addActionListener(this);
        menuItem23.addActionListener(this);
        menuItem24.addActionListener(this);

        menuItem31.addActionListener(this);
        menuItem32.addActionListener(this);
        menuItem33.addActionListener(this);

        menuItem41.addActionListener(this);

        //Agregacion del menu al frame
        frame.setJMenuBar(menuBar);
        
        // Creacion del Panel
        JLabel label = new JLabel("Seleccionar codigo fuente ");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(buscar);
        panel.add(compilar);

        // Configuracion del editor de texto
        Font font = new Font("Monospaced", Font.BOLD, 17);
        ta.setFont(font);
        ta.setForeground(Color.CYAN); // Letra
        ta.setBackground(Color.BLACK); // Fondo
        ta.setCaretColor(Color.WHITE);
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
        frame.getContentPane().add(BorderLayout.WEST,new JScrollPane(tabla));

        // Acciones de click para los botones
        compilar.addActionListener(this);
        buscar.addActionListener(this);

        frame.setVisible(true); // Se muestra la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        
        // Control de Eventos (clicks del usuario)
        Object click = e.getSource();

        if (click == buscar || click == menuItem11) {
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
                String texto = "", full = "";
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

        } else if(click == compilar || click == menuItem12){
            tablaSimbolos = new tblSimbolo();
            System.out.println("Compilando...");
            //JOptionPane.showMessageDialog(null, "Analisis...", "Compilando", JOptionPane.PLAIN_MESSAGE);
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
                Object[] r = mostrarTabla();
            }
            else 
                ta2.setText("Caracteres indefidos linea: " + li);
        } else if(click == menuItem13){
            guardarArchivo();
        } else if(click == menuItem14){
            guardarComoArchivo();
        } else if(click == menuItem15){
            if(!new File(url).exists()){
                JOptionPane.showMessageDialog(null, "No se ha cargado ningun archivo para eliminar.", "Error eliminar", JOptionPane.WARNING_MESSAGE);
            }else{
                switch(createDialogEliminar(frame, url)){
                    case 0:
                        File file = new File(url);
                        System.out.println("URL:" + url);
                        file.delete();
                        JOptionPane.showMessageDialog(null, "Se ha eliminado con exito el archivo " + url + ".", "Eliminado exitosao", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null, "No se ha eliminado el archivo " + url + ".", "Eliminado incorrecto", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
        } else if(click == menuItem21){
            switch(createDialogLimpiar(frame, "el EDITOR DE TEXTO")){
                case 0:
                    ta.setText("");
                    createDialogLimpiado(frame, "el EDITOR DE TEXTO");
                break;
                default:
                    createDialogNoLimpiado(frame, "el EDITOR DE TEXTO");
                break;
            }
        } else if(click == menuItem22){
            switch(createDialogLimpiar(frame, "la TABLA DE SIMBOLOS")){
                case 0:
                    int tamanio = modelT.getRowCount();
                    for(int i=tamanio-1;i>=0;i--){
                        modelT.removeRow(i);
                    }
                    tablaSimbolos.limpTabla();
                    Object[] r = mostrarTabla();
                    createDialogLimpiado(frame, "la TABLA DE SIMBOLOS");
                break;
                default:
                    createDialogNoLimpiado(frame, "la TABLA DE SIMBOLOS");
                break;
            }
        } else if(click == menuItem23){
            switch(createDialogLimpiar(frame, "la CONSOLA")){
                case 0:
                    ta2.setText("");
                    createDialogLimpiado(frame, "la CONSOLA");
                break;
                default:
                    createDialogNoLimpiado(frame, "la CONSOLA");
                break;
            }
        } else if(click == menuItem24){
            switch(createDialogLimpiar(frame, "el COMPILADOR COMPLETO")){
                case 0:
                    ta.setText("");
                    int tamanio = modelT.getRowCount();
                    for(int i=tamanio-1;i>=0;i--){
                        modelT.removeRow(i);
                    }
                    tablaSimbolos.limpTabla();
                    Object[] r = mostrarTabla();
                    ta2.setText("");
                    createDialogLimpiado(frame, "el COMPILADOR COMPLETO");
                break;
                default:
                    createDialogNoLimpiado(frame, "el COMPILADOR COMPLETO");
                break;
            }
        } else if(click == menuItem31){
            try {
                File path = new File ("recursos/PDFs/Actividad 4.pdf");
                Desktop.getDesktop().open(path);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if(click == menuItem32){
            try {
                File path = new File ("recursos/PDFs/Actividad 5.pdf");
                Desktop.getDesktop().open(path);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if(click == menuItem33){
            try {
                File path = new File ("recursos/PDFs/Actividad 6.pdf");
                Desktop.getDesktop().open(path);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if(click == menuItem41){
            JDialog modelAcerca = createDialogAcerca(frame);
            modelAcerca.setVisible(true);
        }

    }
    Object[] mostrarTabla(){
        System.out.println("id      numToken            Token           Descripcion                 valor");
        System.out.println("---------------------------------------------------------");
        Object[] r = new simbolo[tablaSimbolos.tamanio()];
        for(int i=0;tablaSimbolos.tamanio()>i;i++){
            simbolo row = tablaSimbolos.getSimbolo(i);
            modelT.addRow(new Object[]{(i+1),row.getId(),row.getToken(),row.getDescripcion(),row.getToken()});
            System.out.println((i+1)+ "     "+row.getId()+"         "+ row.getToken()+"         "+row.getDescripcion()+"         "+row.getToken());
        }
        return r;
    }

    //Creacion del modal Acerca de
    private static JDialog createDialogAcerca(JFrame frame){
        String titulo, text;
        titulo = "Acerca de";
        
        text = "<html><body style='text-align: center'>" +
               "    <div>"+
               "        <div class='col-md-12' style='display: flex; align-items: center; justify-content: center;'>" +
               "            <img width='80' height='100' src='https://sg.com.mx/sites/default/files/2018-04/LogoITCelaya.png'/>" +
               "            <!--<img src='../recursos/imagenes/itc.png'/>-->" +
               "        </div>" +
               "        <div style='margin-top: 10px'>" +
               "            <label>~ Tecnológico Nacional de México en Celaya ~</label> <br>" +
               "            <label> Lenguajes y Automatas ll</label> <br><br>" +
               "            <label> VERSION: 1.00</label> <br><br>" +
               "            <label style=\"font-family: 'Roboto Medium', sans-serif; font-size:20px;\"> EQUIPO 3 </label> <br>" +
               "            <label> INTEGRANTES: </label> <br>" +
               "            <label>- Garcia Ramirez Luis David </label> <br>" +
               "            <label>- Perez Cabrera Jose Eduardo </label> <br>" +
               "            <label>- Ramirez Garcia Marco Isaias </label> <br>" +
               "        </div>" +
               "    </div>" +
               "</body></html> ";

        JDialog modal = new JDialog(frame, titulo, Dialog.ModalityType.DOCUMENT_MODAL);
        modal.setBounds(0,0,335,300);
        modal.setLocationRelativeTo(frame);

        Container container = modal.getContentPane();
        container.setLayout(new BorderLayout());
   
        container.add(new JLabel(text));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        container.add(panel, BorderLayout.SOUTH);
        
        return modal;
    }

    //Creacion del dialog para confirmar limpiar
    private int createDialogLimpiar(JFrame frame, String p_areaLimpiar){
        int input;
        input = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres limpiar " + p_areaLimpiar + "?", "Confirmacion para limpiar", JOptionPane.YES_NO_OPTION);
        
        return input;
    }

    //Creacion del dialog para confirmacion de que se limpio
    private void createDialogLimpiado(JFrame frame, String p_areaLimpiar){
        JOptionPane.showMessageDialog(null, "Se ha limpiado con exito " + p_areaLimpiar + ".", "Limpieza exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    //Creacion del dialog para no limpiado
    private void createDialogNoLimpiado(JFrame frame, String p_areaLimpiar){
        JOptionPane.showMessageDialog(null, "No se ha limpiado " + p_areaLimpiar + ".", "Limpieza incorrecta", JOptionPane.WARNING_MESSAGE);
    }

    //Creacion del dialog para guardado correcto
    private void createDialogGuardado(JFrame frame, String p_nombFichero){
        JOptionPane.showMessageDialog(null, "El fichero " + p_nombFichero + " se ha guardado exitosamente.", "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    //Creacion del dialog para no no guardado
    private void createDialogNoGuardado(JFrame frame, String p_nombFichero){
        JOptionPane.showMessageDialog(null, "El fichero " + p_nombFichero + " no se ha guardado.", "Guardado incorrecto", JOptionPane.WARNING_MESSAGE);
    }

    //Creacion del dialog para eliminar
    private int createDialogEliminar(JFrame frame, String p_archivo){
        int input;
        input = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres eliminar el archivo " + p_archivo + "?", "Confirmacion para eliminar", JOptionPane.YES_NO_OPTION);
        
        return input;
    }

    private void guardarArchivo(){
        try{
            if(new File(url).exists()){
                File file = new File(url);
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ta.getText());
                bw.close();
                createDialogGuardado(frame, file + "");
            }
            else{
                guardarComoArchivo();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void guardarComoArchivo(){
        JFileChooser jF1 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        String ruta = "";
        try{
            if(jF1.showSaveDialog(null) == jF1.APPROVE_OPTION){
                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println("RUTA:" + ruta);
                if(new File(ruta + ".txt").exists()){
                    File file = new File(ruta + ".txt");
                    if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,"El fichero ya existe, ¿Deseas reemplazarlo?","Fichero ya existente",JOptionPane.YES_NO_OPTION)){
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(ta.getText());
                        bw.close();
                        createDialogGuardado(frame, file + "");
                    }else{
                        createDialogNoGuardado(frame, file + "");
                    }
                }else{
                    File file = new File(ruta);
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(ta.getText());
                    bw.close();
                    createDialogGuardado(frame, file + "");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}