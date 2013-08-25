/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plot;

import java.util.ArrayList;

import aw.gui.chart.Chart2D;
import aw.gui.chart.ITrace2D;
import aw.gui.chart.Trace2DSimple;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
/**
 *
 * @author fau <slp.vld@gmail.com>
 */
public class DrawPlot {
    public DrawPlot(ArrayList<Float> data)
    {
        Chart2D chart = new Chart2D();
        ITrace2D trace = new Trace2DSimple();
        for (int i=0;i<data.size();i++)
            trace.addPoint(i,data.get(i));
        
        chart.addTrace(trace);
        
        JFrame frame = new JFrame("Result");
        
        frame.getContentPane().add(chart);
        frame.setSize(400,300);
        frame.addWindowListener(
        new WindowAdapter(){
          public void windowClosing(WindowEvent e){
              System.exit(0);
          }
        }
      );
     frame.setVisible(true);
        
    }
}
