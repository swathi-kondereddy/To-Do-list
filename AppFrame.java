package com.example;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AppFrame extends JFrame {
    private JButton addtask;
    private JButton clear;
    
    TitleBar title = new TitleBar();
    BtnPanel btnpanel = new BtnPanel();
    List list = new List();
    
    public AppFrame() {
        this.setSize(400, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.btnpanel, BorderLayout.SOUTH);
        this.add(this.list, BorderLayout.CENTER);
        
        addtask = btnpanel.getaddtaskbtn();
        clear = btnpanel.getclearbtn(); // Corrected to get the clear button
        
        addlistener();
        this.setVisible(true);
    }
    
    public void addlistener() {
        addtask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.indexnum();
                revalidate();
                
                JButton done = task.getdonej();
                done.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        task.donestatuts();
                        revalidate();
                    }
                });
                
                JButton remove = task.getremovej();
                remove.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        list.remove(task);
                        list.indexnum();
                        revalidate();
                        repaint();
                    }
                });
            }
        });
        
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component[] tasklist = list.getComponents();
                for (Component task : tasklist) {
                    list.remove(task);
                }
                list.indexnum();
                revalidate();
                repaint();
            }
        });
    }
}
