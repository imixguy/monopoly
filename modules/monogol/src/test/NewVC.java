package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by miha on 20.08.2015.
 */
public class NewVC extends JFrame{
    private MyPMeni dialog;
    public static void main(String[] arg){

        MyPMeni m=new MyPMeni();

        NewVC vc=new NewVC();
        vc.setStringJc(m.getL());
        System.out.println( m.getL().get(0));
    }

    private JComboBox jc;

    public NewVC() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        JPanel p=new JPanel(new FlowLayout());
        JButton butok=new JButton("ok");
        dialog=new MyPMeni();
        butok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.setVisible(true);
            }
        });

        p.add(butok);
        p.add(new JButton("ok2"));
        getContentPane().add(p, BorderLayout.SOUTH);
        getContentPane().add(new JLabel("asdf"),BorderLayout.NORTH);
        this.jc=new JComboBox();
        getContentPane().add(jc,BorderLayout.NORTH);
         jc.addItemListener (new ItemListener() {

             @Override
             public void itemStateChanged(ItemEvent itemEvent) {
                 if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                     String item = (String) itemEvent.getItem();
                     JOptionPane.showMessageDialog(null, item);
                     // do something with object
                 }
             }
         });

        setSize(600, 400);
        setVisible(true);
    }

    public void setStringJc(java.util.List<String> jc) {
        jc.set(0,"hujace");
        this.jc.setModel(new DefaultComboBoxModel(jc.toArray()));
    }
}
