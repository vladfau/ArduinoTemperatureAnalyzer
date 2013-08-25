/*
 * 
 *  RandomDataCollector.java  jchart2d
 *  Copyright (C) Achim Westermann, created on 10.12.2004, 15:04:16  
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
 * A proof of concept dummy implementation for the supertype. 
 * Only collects random values with timestamp on the x axis. 
 * </p>
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 *
 */
public class RandomDataCollector extends AbstractDataCollector {

  private double y = 0.0;
  private long starttime = System.currentTimeMillis();
 
  public RandomDataCollector(ITrace2D trace,int latency) {
    super(trace,latency);
  }
  
  /* (non-Javadoc)
   * @see aw.gui.chart.AbstractDataCollector#collectData()
   */
  public TracePoint2D collectData() {
    double rand = Math.random();
    boolean add = (rand>=0.5)?true:false;
    this.y = (add)? this.y+ Math.random():this.y- Math.random();
    return new TracePoint2D(this.getTrace(),((double)System.currentTimeMillis()-this.starttime),this.y);
  }
}
