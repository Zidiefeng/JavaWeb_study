package com.kaitan.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame("Happy Happy");//新建一个窗体
        Panel panel = new Panel(null);//新建一个面板

        frame.setLayout(null);//设置窗体的布局
        frame.setBounds(300,300,500,500);
        frame.setBackground(new Color(0,0,255));

        panel.setBounds(50,50,300,300);
        panel.setBackground(new Color(0,255,0));

        frame.add(panel);
        frame.setVisible(true);

        //listen event, to close
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing");
                System.exit(0);
            }
        });
    }
}
