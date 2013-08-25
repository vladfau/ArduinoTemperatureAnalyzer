package serial;


import java.awt.TextArea;
import jssc.SerialPort;
import jssc.SerialPortException;
import javax.swing.SwingWorker;
import java.util.ArrayList;
/**
 *
 * @author fau <slp.vld@gmail.com>
 */
public class SerialInterface extends SwingWorker<ArrayList<Float>,Float>{
    private SerialPort sp;
    ArrayList<Float> aData = new ArrayList<>();
    
    private TextArea ta;
    public SerialInterface(SerialPort sp, TextArea ta)
    {
        this.sp = sp;
        this.ta = ta;
    }

    @Override
    protected ArrayList<Float> doInBackground() throws SerialPortException {
        
        while (!isCancelled())
        {
            float result = Float.parseFloat(sp.readString(4));
            aData.add(result);         
            ta.setText("");
            ta.append("Temps read: "+GetSizeStat()+"\n");
            
        }
        return null;
    }
    
    public ArrayList<Float> IndiaGet()
    {
        return this.aData;
    }
    
    private int GetSizeStat()
    {
        return this.aData.size()+1;
    }
    
}
