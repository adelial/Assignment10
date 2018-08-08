// Earth/globe class and methods 
import processing.core.*;
import processing.data.*;

public class Globe {
	float x, y;  
	int diameter=200;
	boolean over = false;
	PApplet parent;
	PImage imgEarth; 
	PShape globeEarth;
	
    // constructor
	Globe(){		
	}
	
	Globe(PApplet p){
		parent = p;
		globeEarth = parent.createShape(parent.SPHERE, this.diameter);
	}

	Globe(int diameter, String pathFile, PApplet p) {	
		this.diameter = diameter;
	    parent = p;
	    
		imgEarth = parent.loadImage(pathFile);
		parent.noStroke();
	    globeEarth = parent.createShape(parent.SPHERE, this.diameter);
	    globeEarth.setTexture(imgEarth);
	}
	
	// Draw Globe
	public void draw(PApplet p) {
	
		this.parent = p;				    		   
	    parent.lights();
	    parent.fill(250);
	    parent.noStroke();
	    parent.shape(globeEarth);		    
		    	
	}
	
	// Vizualization of the data in the defined table
	// In this case happy scores for the countries
	public void VizData(PApplet p, Table dataTable, int r) {
		float lat, lon, score;
		this.parent = p;

    	for (TableRow row : dataTable.rows()) { 
     	    lat = row.getFloat("Latitude");
     	    lon = row.getFloat("Longitud");
     	    score = row.getFloat("Score");
     	    
     	    float theta = parent.radians(lat) + parent.PI/2;
     	    float phi = parent.radians(-lon) + parent.PI;
     	    float x = r*parent.sin(theta)*parent.cos(phi);
     	    float z = r*parent.sin(theta)*parent.sin(phi);
     	    float y = r*parent.cos(theta);
     	    int bcolor;
     	    
     	    PVector pos = new PVector(x,y,z);  
     	    float h= parent.pow(10, score);
     	    float maxh = parent.pow(10,8);
     	    h = parent.map(h, 0, maxh, 1, 100);
     	    
     	    PVector xaxis = new PVector(1,0,0);
     	    float angleb = PVector.angleBetween(xaxis, pos);
     	    PVector raxis = xaxis.cross(pos);
     	    
     	    parent.pushMatrix();
     	    	parent.translate(x,y,z);
     	    	parent.rotate(angleb,raxis.x, raxis.y, raxis.z);
     	      
     	    	if (score > 7.0)
     	    		parent.fill(164,229,180);  // ligth green
     	    	else if (score > 6 && score <7)  
     	    		parent.fill(7, 170,90);   // green
     	    	else if (score < 6 && score > 5)
     	    		parent.fill(148,92,149);  //marron
     	    	else if (score < 5 && score >4)
     	    		parent.fill(142,84,7);  //brown
     	    	else
     	    		parent.fill(165,7,20);  // red
     	    	parent.box(h*3,10,10);
     	    parent.popMatrix();
     	  }  // for    	
	}// VizData
}
