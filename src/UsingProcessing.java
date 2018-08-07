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
	//Interaction ui;
	Menu optionMenu;

	float angle;
    Table dataCountries;
    int diameter=300, a;
    int[] btncolors = new int[4];
    String labels[] = {"2017", "2016", "2015"}; 
	
	
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
    	
    	// load data   	
    	dataCountries=loadTable(localPath+"2017_happy.csv","header");
    	
    	  	    	    
    } //setup

    public void draw(){
    	background(51);
    	
    	optionMenu.build(800, 280, true, this);
    	
    	lights();
    	fill(250);
    	noStroke();
    	pushMatrix();
    	translate(500,500,0);
    	rotateY(angle);  // use for the rotation in Y 
    	angle+= 0.01;
    	world.draw(this);  // draw the globe
    	
    	world.VizData(this, dataCountries, diameter);  // Use to add the visualization for the scores
    	popMatrix();
    	
    	if (mouseX > 800 && mouseX <1500 && mouseY > 280 && mouseY<320 && mousePressed && mouseButton == LEFT){
    	    optionMenu.selectedButton(this, btncolors);
    	  }
    	else {
    	    optionMenu.keepButton(this,btncolors);
    	}
    } 
} // class
