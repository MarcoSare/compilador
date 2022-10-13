//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class main extends JFrame implements ActionListener {

    // Text Area at the Center
    JTextArea ta = new JTextArea();
    JTextField tf = new JTextField(20); // accepts upto 10 characters
    JButton buscar = new JButton("Buscar");
    JButton compilar = new JButton("Compilar");

    public static void main(String args[]) {
        main obj = new main();
        obj.gui();
    }
    
    void gui(){
        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); //center
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //fullscreen

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Seleccionar código fuente ");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(buscar);
        panel.add(compilar);

        compilar.addActionListener(this);
        buscar.addActionListener(this);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object click = e.getSource();
        if (click == buscar) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Seleccionar código fuente");
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto plano", "txt", "e");
            jfc.addChoosableFileFilter(filter);
    
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                System.out.println(jfc.getSelectedFile().getPath());
                tf.setText(jfc.getSelectedFile().getPath());
            }


            try {
                String texto = "", full = "";
                //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector=new FileReader(jfc.getSelectedFile().getPath());

                //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido=new BufferedReader(lector);

                //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                while((texto=contenido.readLine())!=null) {
                    System.out.println(texto);
                    full += texto+"\n";
                }
                ta.setText(full);
                
            } catch (Exception ex) {
                // TODO: handle exception
            }


        }else if(click == compilar){
            System.exit(0); 
        }

    }

}