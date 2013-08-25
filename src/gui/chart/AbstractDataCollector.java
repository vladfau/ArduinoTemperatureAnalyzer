/*
 * 
 *  AbstractDataCollector.java  jchart2d
 *  Copyright (C) Achim Westermann, created on 10.12.2004, 14:48:09  
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *  
 *  If you modify or optimize the code in a useful way please let me know.
 *  Achim.Westermann@gmx.de
 *	
 */
package aw.gui.chart;

/**
 * <p>
 * A simple Runnable that contiuously collects data every 
 * latency time period and adds it to the internal ITrace2D instance.
 * </p>
 * <p>
 *  Set it up with code like: 
 *  <pre>
 *   Chart2D chart = new Chart2D();
 *   ITrace2D trace = &lt;initializatioin&gt;
 *   chart.addTrace(trace);
 *   // Put the chart in your UI... 
 *   // ... 
 *   AbstractDataCollector collector = new &lt;subtypename&gt;(200,trace); 
 *   new Thread(collector).start();
 *  </pre>
 * </p> 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 *
 */
public abstract class AbstractDataCollector implements Runnable {

  private boolean stop = false;
  
  private long latency = 400;
  private ITrace2D trace;
  /**
   * 
   */
  public AbstractDataCollector(ITrace2D trace,int latency) {
    super();
    this.latency = latency;
    this.trace = trace;
  }

  /* (non-Javadoc)
   * @see java.lang.Runnable#run()
   */
  public void run() {
    long lasttime;
    
    while(!stop){
      lasttime = System.currentTimeMillis();
      this.trace.addPoint(this.collectData());
      try {
        Thread.sleep(Math.max(this.latency - System.currentTimeMillis() + lasttime,0));
      } catch (InterruptedException e) {
        this.stop = true;
      }
      if(Thread.interrupted()){
        this.stop = true;
      }
    }
    // Allow restart (by assingment to a new Thread from outside) if this instance is cached!
    this.stop = false;
  }
  
  public void stop(){
    this.stop = true;
  }
  
  
  
  /* (non-Javadoc)
   * @see java.lang.Object#finalize()
   */
  public void finalize() throws Throwable {
    super.finalize();
    this.stop();
  }
  
  public abstract TracePoint2D collectData();

  /**
   * @return Returns the trace.
   */
  public ITrace2D getTrace() {
    return trace;
  }
  /**
   * @return Returns the latency.
   */
  public long getLatency() {
    return latency;
  }
  /**
   * @param latency The latency to set.
   */
  public void setLatency(long latency) {
    this.latency = latency;
  }
}
