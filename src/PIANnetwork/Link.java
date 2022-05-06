/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class Link extends JFrame{
     String str;
   
 
    public Link(String str) throws HeadlessException 
    {
        super();
        JLabel link=new JLabel(str);
        setTitle(str);
 
        link.setForeground(Color.BLUE.darker());
        link.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        link.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                try 
                {
                    Desktop.getDesktop().browse(new URI(str));
                } 
                catch (IOException | URISyntaxException e1) 
                {
                    e1.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                link.setText(str);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                link.setText(str);
            }
        });
 
        setLayout(new FlowLayout());
        getContentPane().add(link);
 
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public  void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new Link(str).setVisible(true);;
            }
        });
    }
}
