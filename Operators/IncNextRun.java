/*
 * File: IncNextRun.java 
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
 * Contact : John Hammonds jhammonds@anl.gov>
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
 *  Revision 1.7  2006/01/01 04:52:13  hammonds
 *  Remove unused variables.
 *
 *  Revision 1.6  2003/09/17 14:42:27  hammonds
 *  Added bell and extra formatting to point out messages from caught exceptions
 *
 *  Revision 1.5  2003/08/02 14:46:07  hammonds
 *  Fix constructor with iName argument.  Fix main method
 *
 *  Revision 1.4  2003/03/12 02:30:26  hammonds
 *  Trim up imports.
 *
 *  Revision 1.3  2002/02/23 13:50:32  hammonds
 *  Added import statement for new location of GenericBatch and Generic Load Operators which are the base class for these operators.
 *
 *  Revision 1.2  2001/11/26 15:18:02  hammonds
 *  Deleted println which clutter output.
 *
 *  Revision 1.1  2001/11/02 19:14:57  hammonds
 *  Incremement the last run number in parameter file.
 *
 *
 *
 */

package IPNS.Operators;

import  java.io.Serializable;
import  java.io.FileInputStream;
import  java.io.FileOutputStream;
import  java.io.IOException;
import  java.util.Properties;
import  java.util.Vector;
import  DataSetTools.operator.Parameter;
import  DataSetTools.operator.Generic.Batch.GenericBatch;
//import  IPNS.Runfile.*;

/**
 * This operator instantiates IPNS.RunfileBuilder.
 * 
 */

public class IncNextRun extends  GenericBatch 
                            implements Serializable
{

  /* ------------------------ FULL CONSTRUCTOR -------------------------- */
  /**
   * Construct a default IncNextRun operator to to allow scripts to call IPNS.RunfileBuilder.
   */
  String infileName;
  public IncNextRun( String iName  )
  {
    super( "IncNextRun" );
    getParameter(0).setValue(iName);
  }

public IncNextRun()
  { super( "IncNextRun" );
     setDefaultParameters();
  }

  /* ---------------------------- getCommand ------------------------------- */
  /**
   * @return	the command name to be used with script processor: in this case, RFBWrapper
   */
   public String getCommand()
   {
     return "IncNextRun";
   }

 /* -------------------------- setDefaultParmeters ------------------------- */
 /**
  *  Set the default parameters.
  */
  public void setDefaultParameters()
  {
     parameters = new Vector();  // must do this to create empty list of 
                                 // parameters
     Parameter parameter= new Parameter("iName", new String());
     addParameter( parameter );

  }


  /* ---------------------------- getResult ------------------------------- */

  public Object getResult()
  {
      int runNum = 0;
      FileInputStream datFile, paramFile;
      Properties iDat = new Properties();
      Properties params = new Properties();
      String paramFileName = new String();
      String instDir = new String();
      String iName = (String) (getParameter(0).getValue());
	  if (iName != null)
	      {
		  String home = System.getProperty("user.home");
		  String fileSep = System.getProperty("file.separator");
		  String datFileName = new String(home + fileSep + "inst" + 
						  fileSep + iName + ".dat");
		  
		  try {
		      datFile = new FileInputStream(datFileName);
		      iDat.load(datFile);
		      instDir = iDat.getProperty("instDir");
		      datFile.close();
		  }
		  catch (IOException e) {
		      System.out.println("/007/007/007/007");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>     Can't open file " + 
					 datFileName);
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.exit(0);
		  }
		  paramFileName = new String(instDir + fileSep + iName + 
					     "__V5.par");
		  try {
		      paramFile = new FileInputStream(paramFileName);
		      params = new Properties();
		      params.load(paramFile);
		      paramFile.close();
		  }
		  catch (IOException e) {
		      System.out.println("/007/007/007/007");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>      Can't open file " + 
					 paramFileName);
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.exit(0);
		  }
		  try {
		      runNum = (
				new Integer(params.getProperty("LastRun"))).
			  intValue() + 1;
		  }
		  catch (NumberFormatException e) {
		      System.out.println("/007/007/007/007");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>       Improper LastRun number " +
					 "in parameter file");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.exit(0);
		  }
		  params.put("LastRun", Integer.toString(runNum));
		   
		  try {
		      FileOutputStream paramFileOut = 
			  new FileOutputStream(paramFileName);
		      String paramFileHeader = new
			  String(iName + 
				 " instrument parameter " + 
				 "file.  Saved By Newrun.");
		      params.store(paramFileOut, 
				   paramFileHeader);
		      paramFileOut.close();
		  }
		  catch (IOException e) {
		      System.out.println("/007/007/007/007");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>     Can't open file " + 
					 paramFileName);
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		      System.exit(0);
		  }
	      }
	  
	  
	  return (new Integer(runNum));
	  
  }  
    

  public static void main(String[] arg)
  {

    IncNextRun op = new IncNextRun(arg[0]);
    System.out.println( op.getResult().toString());
  }

}
