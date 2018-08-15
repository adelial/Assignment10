// Earth-globe class and methods 
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
		//parent.directionalLight(0, 0, 0, 0, 0, 100);
	    //parent.lights();
	    parent.fill(250);
	    parent.noStroke();
	    parent.shape(globeEarth);		    		    	
	} // draw
	
	// Visualization of the data in the defined table - happy scores for the countries
	public void VizData(PApplet p, Table dataTable) {
		float lat, lon, score;
		this.parent = p;

    	for (TableRow row : dataTable.rows()) { 
     	    lat = row.getFloat("Latitude");
     	    lon = row.getFloat("Longitud");
     	    score = row.getFloat("Score");
     	    
     	    float theta = parent.radians(lat) + parent.PI/2;
     	    float phi = parent.radians(-lon) + parent.PI;
     	    float x = this.diameter*parent.sin(theta)*parent.cos(phi);
     	    float z = this.diameter*parent.sin(theta)*parent.sin(phi);
     	    float y = this.diameter*parent.cos(theta);
     	    int bcolor;
     	    
     	    PVector pos = new PVector(x,y,z);
     	    // the following calculations are done for better visualization in percentages 
     	    float h= parent.pow(10, score);
     	    float maxh = parent.pow(10,8);
     	    // mapping for min and max values 
     	    h = parent.map(h, 0, maxh, 1, 100);
     	    
     	    PVector xaxis = new PVector(1,0,0);
     	    float angleb = PVector.angleBetween(xaxis, pos);
     	    PVector raxis = xaxis.cross(pos);
     	    
     	    parent.pushMatrix();
     	    	parent.translate(x,y,z);
     	    	parent.rotate(angleb,raxis.x, raxis.y, raxis.z);
                // depends of the score value a color for the box is determined    	      
     	    	if (score > 7.0)
     	    		parent.fill(164,229,180);  // ligth green
     	    	else if (score > 6 && score <7)  
     	    		parent.fill(7, 170,90);   // green
     	    	else if (score < 6 && score > 5)
     	    		parent.fill(148,92,149);  //maroon
     	    	else if (score < 5 && score >4)
     	    		parent.fill(142,84,7);  //brown
     	    	else
     	    		parent.fill(165,7,20);  // red
     	    	parent.box(h*3,10,10);
     	    parent.popMatrix();
     	  }  // for    	
	}// VizData
} // class
