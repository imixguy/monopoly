package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by miha on 20.08.2015.
 */
public class MyPMeni extends  JDialog implements ActionListener{
    private java.util.List<String> l= Arrays.asList("vanja", "polnii", "d");
    public MyPMeni() {
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new JLabel("vanka BALBES"));
        JButton but2=new JButton("Закрой меня");
        getContentPane().add(but2);
        but2.addActionListener(this);
        setSize(200, 200);
    }

    public List<String> getL() {
        return l;
    }

    public void setL(List<String> l) {
        this.l = l;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
    }
}
