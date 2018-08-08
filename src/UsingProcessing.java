//******************************************** //
// MSDS 6390 - Visualization of Information    // 
// Assignment 10 - Final 3D Java Visualization //
// Names:  Alma Lopez and George Sturrock      //
//******************************************** //

//Credit To:
//The coding train https://www.youtube.com/watch?v=dbs4IYGfAXc

import processing.core.*;
import processing.data.*;


public class UsingProcessing extends PApplet {

	//Class Declarations
	Globe world;
	Menu optionMenu;
	Legend boxLabels;

	float angle;
    Table data2015, data2016, data2017;
    int diameter=300, a;
    int[] btncolors = new int[4];
    String labels[] = {"2017", "2016", "2015"};
    String captions[] = {" > 7.0","6.01 - 7.0","5.01 - 6.0","4.01 - 5.0","> 5.0"};
    // colors: light green, green, marron, brown, red
    int graphcolors[] = {color(164,229,180),color(7, 170,90),color(148,92,149),color(142,84,7),color(165,7,20)};
    
   
	//Static Variable Used to Toggle between A and G folder paths
	public static String localPath = "C:\\Users\\Alma\\eclipse-workspace\\Assignment10\\data\\";
	//public static String localPath = "C:\\Users\\Sturrock\\Documents\\SMU Data Science\\Vizualization\\Viz_Assignment9\\data\\";
	
	
	public static void main(String[] args) { 
		PApplet.main(new String[] {"UsingProcessing"} );
	}
	
	public void settings(){
		size(1500,1000, P3D);   // Size of the window
	}

    public void setup(){
   	    	
    	btncolors[0] = color(92,183,178);  // brackground - Russian Flag Blue
    	btncolors[1] = color(0);  // label
    	btncolors[2] = color(53,50,137);  // mouseover
    	btncolors[3] = color(46,98,137);  // selected
    	  
    	// Creation of objects 
    	world = new Globe(diameter,localPath+"earth.jpg", this);
    	//ui = new Interaction();
    	optionMenu = new Menu(labels, 30, 100, btncolors, this);
    	fill(0);
    	boxLabels = new Legend(150, 175, graphcolors, captions,	color(255), color(255), false, this);
    	
    	// load data   	
    	data2017=loadTable(localPath+"2017_happy.csv","header");
    	data2016=loadTable(localPath+"2016_happy.csv","header");
    	data2015=loadTable(localPath+"2015_happy.csv","header");
    	  	    	    
    } //setup

    public void draw(){
    	int selYear=0;
    	background(51);
    	
    	optionMenu.build(1000, 280, true, this);
    	boxLabels.draw(this, 1000,500, "Happiness Score");
    	if (mouseX > 800 && mouseX <1500 && mouseY > 280 && mouseY<320 && mousePressed && mouseButton == LEFT){
    	    selYear = optionMenu.selectedButton(this, btncolors);
    	  }
    	else {
    	    selYear= optionMenu.keepButton(this,btncolors);
    	}
    	
    	//lights();
    	lightSpecular(0, 0, 0);
    	fill(250);
    	noStroke();
    	pushMatrix();
    	translate(500,500,0);
    	rotateY(angle);  // use for the rotation in Y 
    	angle+= 0.005;
    	world.draw(this);  // draw the globe
    	switch(selYear) {
        	case (1): 
        		world.VizData(this, data2017, diameter);  // Use to add the visualization for the scores
        		break;
        	case (2):
        		world.VizData(this, data2016, diameter);  // Use to add the visualization for the scores
        		break;
        	case (3):
        		world.VizData(this, data2015, diameter);  // Use to add the visualization for the scores
                break;
        	//default:
        		//world.VizData(this, data2017, diameter);  // Use to add the visualization for the scores
        		
    	}    
    	//world.VizData(this, data2017, diameter);  // Use to add the visualization for the scores
    	popMatrix();
    	
    	
    } 
} // class
