/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import plot.DrawPlot;
import serial.SerialInterface;

/**
 *
 * @author fau <slp.vld@gmail.com>
 */
public class MainWindow extends JFrame implements ActionListener {
    
    private JButton BStartCOM = new JButton("Get data");
    private JButton BStopCOM = new JButton("Stop receiving");
    private JButton BGraph = new JButton("Build plot");
    
    private SerialInterface si;
    
    private TextArea  term = new TextArea(5,40);
    
    private ArrayList<Float> data;
    
    public MainWindow()
    {
        JFrame frame = new JFrame("Temp Anal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(400,120);
        frame.setResizable(false);
        
        GridLayout gridl = new GridLayout(2,3);
        Container frameContainer = frame.getContentPane();
        frameContainer.setLayout(gridl);
        
        frameContainer.add(BStartCOM);
        frameContainer.add(BStopCOM);
        frameContainer.add(BGraph);
        frameContainer.add(term);
        
        this.term.setEditable(false);
        
        this.BStartCOM.addActionListener(this);
        this.BStopCOM.addActionListener(this);
        this.BGraph.addActionListener(this);
        
        COMSelectorDialog sd = new COMSelectorDialog(frame);
        si = new SerialInterface(sd.SetupCOM(),term);
        
        
        frame.setVisible(true);
    }
   
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        
        if (ob == this.BStartCOM)
        {
            this.term.append("Starting com\n");
            si.execute();
        }
        
        if (ob == this.BStopCOM)
        {
            this.term.append("Stopping com\n");
            si.cancel(true);
            data = si.IndiaGet();
        }
        
        if (ob == this.BGraph)
        {
            this.term.append("Building plot\n");
            DrawPlot dp = new DrawPlot(data);
        }
       
    }
}
