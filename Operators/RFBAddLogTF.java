/*
 * File: RFBAddLogTF.java 
 *
 * Copyright (C) 2001, Alok Chatterjee,
 *                     Ruth Mikkelson, 
 *                     John Hammonds
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * Contact : Alok Chatterjee achatterjee@anl.gov>
 *           Intense Pulsed Neutron Source Division
 *           Argonne National Laboratory
 *           9700 S. Cass Avenue, Bldg 360
 *           Argonne, IL 60440
 *           USA
 *
 * This work was supported by the Intense Pulsed Neutron Source Division
 * of Argonne National Laboratory, Argonne, IL 60439-4845, USA.
 *
 * For further information, see <http://www.pns.anl.gov/ISAW/>
 *
 * Modified:
 *
 *  $Log$
 *  Revision 1.1  2002/08/21 17:39:15  hammonds
 *  New method for dt/t binning.
 *
 *
 *
 */

package IPNS.Operators;

import  java.io.*;
import  java.util.*;
import  DataSetTools.operator.*;
import  DataSetTools.operator.Generic.Batch.*;
import  IPNS.Runfile.*;

/**
 * This operator instantiates IPNS.RunfileBuilder.
 * 
 */

public class RFBAddLogTF extends  GenericBatch 
                            implements Serializable
{

  /* ------------------------ FULL CONSTRUCTOR -------------------------- */
  /**
   * Construct a default RFBAddLogTF operator to to allow scripts to call IPNS.RunfileBuilder.
   */
  public RFBAddLogTF( RunfileBuilder rfb, Float val1, Float val2, Float val3, Integer val4)
  {
	
    super( "RFBAddLogTF" );
    System.out.println("Inside the constructor now");  
    Parameter parameter= new Parameter("Runfilebuilder", rfb );
    addParameter( parameter );
    parameter= new Parameter("Min", null );
    addParameter( parameter );
    parameter= new Parameter("Max", null );
    addParameter( parameter );
    parameter= new Parameter("Step", null );
    addParameter( parameter );
    parameter= new Parameter("TFNum", null );
    addParameter( parameter );

  }

public RFBAddLogTF()
  { super( "RFBAddLogTF" );
	//System.out.println("def constructor");
     setDefaultParameters();
  }

  /* ---------------------------- getCommand ------------------------------- */
  /**
   * @return	the command name to be used with script processor: in this case, Pause
   */
   public String getCommand()
   {
     return "RFBAddLogTF";
   }

 /* -------------------------- setDefaultParmeters ------------------------- */
 /**
  *  Set the default parameters.
  */
  public void setDefaultParameters()
  {
     parameters = new Vector();  // must do this to create empty list of 
                                 // parameters
    Parameter parameter= new Parameter("New RunfileBuilder", new RunfileBuilder() );
    addParameter( parameter );
    parameter= new Parameter("Min", null );
    addParameter( parameter );
    parameter= new Parameter("Max", null );
    addParameter( parameter );
    parameter= new Parameter("Step", null );
    addParameter( parameter );
    parameter= new Parameter("TFNum", null );
    addParameter( parameter );


  }


  /* ---------------------------- getResult ------------------------------- */

  public Object getResult()
  {
    int rval = 0;
    RunfileBuilder rfb = (RunfileBuilder) (getParameter(0).getValue());
    float val1 = ( (Float)(getParameter(1).getValue()) ).floatValue();
    float val2 = ( (Float)(getParameter(2).getValue()) ).floatValue();
    float val3 = ( (Float)(getParameter(3).getValue()) ).floatValue();
    int val4   = ( (Integer)(getParameter(4).getValue())).intValue();
    rval = rfb.addLogTimeField(val1, val2, val3, val4);    
    return new Integer(rval);
         
  }  

}
