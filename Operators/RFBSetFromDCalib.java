/*
 * File: RFBSetFromDCalib.java 
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
 *  Revision 1.5  2003/03/12 03:29:52  hammonds
 *  Cleanup imports.
 *
 *  Revision 1.4  2002/02/23 14:00:46  hammonds
 *  Added new import line to account for move of GenericBatch and GenericLoad operators.
 *
 *  Revision 1.3  2001/11/01 20:03:39  chatterjee
 *  Takes empty string for path of the DCalib file and uses default file.
 *
 *  Revision 1.2  2001/08/01 20:57:32  chatter
 *  Added GPL license statement
 *
 *
 */

package IPNS.Operators;

import  java.io.Serializable;
import  java.util.Vector;
import  DataSetTools.operator.Parameter;
import  DataSetTools.operator.Generic.Batch.GenericBatch;
import  IPNS.Runfile.RunfileBuilder;

/**
 * This operator instantiates IPNS.RunfileBuilder.
 * 
 */

public class RFBSetFromDCalib extends  GenericBatch 
                            implements Serializable
{

  /* ------------------------ FULL CONSTRUCTOR -------------------------- */
  /**
   * Construct a default RFBSetFromDCalib operator to to allow scripts to call IPNS.RunfileBuilder.
   */
  public RFBSetFromDCalib( RunfileBuilder rfb, String attr, Object val)
  {
	
    super( "RFBSetFromDCalib" );
    System.out.println("Inside the constructor now");  
    Parameter parameter= new Parameter("Runfilebuilder", rfb );
    addParameter( parameter );
    parameter= new Parameter("Filename", null );
    addParameter( parameter );

  }

public RFBSetFromDCalib()
  { super( "RFBSetFromDCalib" );
	//System.out.println("def constructor");
     setDefaultParameters();
  }

  /* ---------------------------- getCommand ------------------------------- */
  /**
   * @return	the command name to be used with script processor: in this case, Pause
   */
   public String getCommand()
   {
     return "RFBSetFromDCalib";
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
    parameter= new Parameter("File Name", null);
    addParameter( parameter );



  }


  /* ---------------------------- getResult ------------------------------- */

  public Object getResult()
  {
    int rval = 0;
    RunfileBuilder rfb = (RunfileBuilder) (getParameter(0).getValue());
    String val = (String)(getParameter(1).getValue());
    if(val.equalsIgnoreCase(""))
    	rval = rfb.headerSetFromDCalib();  
    else    
    	rval = rfb.headerSetFromDCalib(val);
    return new Integer(rval);
  }
}
