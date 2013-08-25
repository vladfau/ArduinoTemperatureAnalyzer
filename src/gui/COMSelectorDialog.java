/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author fau <slp.vld@gmail.com>
 */
public class COMSelectorDialog {
    private String[] COMs;
    private String result;
    
    public COMSelectorDialog(JFrame frame)
    {  
       CollectCOMs();
       result = (String)JOptionPane.showInputDialog(
                    frame,
                    "Select COM to read from:\n",
                    "COM Selector",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    COMs,
                    COMs[0]);
    }
    
    public SerialPort SetupCOM()   {
       
        SerialPort serialPort = new SerialPort(result);
            
        try {
            
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);//Set params.
        } catch (SerialPortException ex) {
            System.out.println(ex);
            System.exit(-1);
        }
        return serialPort;
    }
        
    private void CollectCOMs()
    {
        this.COMs = SerialPortList.getPortNames();
        
    }
}
