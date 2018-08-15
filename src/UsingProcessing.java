//******************************************** //
// MSDS 6390 - Visualization of Information    // 
// Assignment 10 - Final 3D Java Visualization //
// Names:  Alma Lopez and George Sturrock      //
//******************************************** //

// Credit To:
// The coding train https://www.youtube.com/watch?v=dbs4IYGfAXc
// Data set for happiness score is available in kaggle.com https://www.kaggle.com/unsdsn/world-happiness
/* The happiness scores and rankings use data from the Gallup World Poll. The scores are based on answers 
 * to the main life evaluation question asked in the poll. This question, known as the Cantril ladder, 
 * asks respondents to think of a ladder with the best possible life for them being a 10 and the worst possible 
 * life being a 0 and to rate their own current lives on that scale. The scores are from nationally representative 
 * samples for the years 2013-2016 and use the Gallup weights to make the estimates representative. 
 */

import processing.core.*;
import processing.data.*; 


public class UsingProcessing extends PApplet {

	//Class Declarations
	Globe world;
	Menu yearMenu,optionMenu;
	Legend boxLabels;
	HappyText happyText;
	Graphs barGraph;

	float angle;
    Table data2015, data2016, data2017;
    int diameter=300, a;
    int[] btncolors = new int[4];
    String labels[] = {"2017", "2016", "2015"};
    String labels2[] = {"World","Americas", "Europe", "Asia","Africa"};
    String captions[] = {" > 7.0","6.01 - 7.0","5.01 - 6.0","4.01 - 5.0","< 4.01"};
    String dispR;
    // colors: light green, green, maroon, brown, red
    int graphcolors[] = {color(164,229,180),color(7, 170,90),color(148,92,149),color(142,84,7),color(165,7,20)};
     
	//Static Variable Used to Toggle between A and G folder paths
	//public static String localPath = "C:\\Users\\Alma\\eclipse-workspace\\Assignment10\\data\\";
	public static String localPath = "C:\\Users\\Sturrock\\Documents\\SMU Data Science\\Vizualization\\Assignment10\\Data\\";
	
	
	public static void main(String[] args) { 
		PApplet.main(new String[] {"UsingProcessing"} );
	} // main
	
	public void settings(){
		size(1500,1000, P3D);   // Size of the window
	} // settings

    public void setup(){
   	    
    	btncolors[0] = color(92,183,178);  
    	btncolors[1] = color(0);  
    	btncolors[2] = color(53,50,137);  
    	btncolors[3] = color(46,98,137);  // selected
    	  
    	// Creation of objects 
    	world = new Globe(diameter,localPath+"earth.jpg", this);  // earth globe
    	yearMenu = new Menu(labels, 30, 100, btncolors, this);  // Menu buttons for year selection
    	optionMenu = new Menu(labels2, 25, 120, btncolors, this);  // Menu buttons for year selection
    	fill(0);  // Font color
    	boxLabels = new Legend(150, 175, graphcolors, captions,	color(255), color(255), false, this);
    	happyText = new HappyText(this);
    	barGraph = new Graphs(this);
    	
    	// load data   	
    	data2017=loadTable(localPath+"2017_happy2.csv","header");
    	data2016=loadTable(localPath+"2016_happy2.csv","header");
    	data2015=loadTable(localPath+"2015_happy2.csv","header");
     	  	    	    
    } //setup

    public void draw(){
    	
    	int selYear=0;
    	int selArea=0;
    	background(20);
    	String title="";
    	
    	textSize(35);
    	fill(255);
    	title = "Measurement of Happiness in the World";
    	textAlign(CENTER);
    	text(title, width/2, 35f, 10f);
    	textSize(15);
    	yearMenu.build(1000, 180, true);
    	optionMenu.build(1000, 250, false);
    	boxLabels.draw(1200,250, "Happiness Score");  // Legend box 
    	
    	if (mouseX > 800 && mouseX <1500 && mouseY > 180 && mouseY<240 && mousePressed && mouseButton == LEFT){
    	    selYear = yearMenu.selectedButton(this, btncolors); 
    	  }
    	else {
    	    selYear= yearMenu.keepButton(this,btncolors);
    	} 
    	if (mouseX > 1000 && mouseX <1150 && mouseY > 241 && mouseY<500 && mousePressed && mouseButton == LEFT){
    	    selArea = optionMenu.selectedButton(this, btncolors); 
    	  }
    	else {
    	    selArea= optionMenu.keepButton(this,btncolors);
    	}
    	
    	lightSpecular(0, 0, 0);
		directionalLight(0, 0, 0, 0, 750, -10);
	    lights();
    	//directionalLight(255, 255, 255, 0, 0, 100);
    	fill(250);
    	noStroke();
    	pushMatrix();
    		translate(500,500,0);
    		switch(selArea) {
            	case (1):  // World
            		dispR = "World"; 
            		rotateY(angle);  // use for the rotation in Y 
        			angle+= 0.005;
        			world.draw(this);  // draw the globe
            		break;
            	case (2):  // Americas
            		dispR = "Americas";
            		rotateY(15.8f);
            		rotateX(0.3f);
            		rotateZ(0.1f);
            		world.draw(this);  // draw the globe
            		break;
            	case (3):  // Europe
            		dispR = "Europe";
            		rotateY(14f);
            		rotateX(-0.3f);
            		rotateZ(-0.4f);
            		world.draw(this);  // draw the globe
            		break;
            	case (4):  // Asia
            		dispR = "Asia";
            		rotateY(19.5f);
            		rotateX(-0.45f);
            		rotateZ(-0.2f);
            		world.draw(this);  // draw the globe
                	break;
            	case (5):  // Africa
            		dispR = "Africa";
            		rotateY(14f);
            		world.draw(this);  // draw the globe
                	break;
            	default:            		
    		}  
    		
    		if (selYear==1) {
    			world.VizData(this, data2017);  // Use to add the visualization for the scores
    			//happyText.write(this, dispR, data2017);
    		}
    		else if (selYear==2) {
    			world.VizData(this, data2016);  // Use to add the visualization for the scores
    			//happyText.write(this, dispR, data2016);
    		}
    		else if (selYear==3) {
        		world.VizData(this, data2015);  // Use to add the visualization for the scores
        		//happyText.write(this, dispR, data2015);
            }    
    	popMatrix();
    	
    	//
    	pushMatrix();
			if (selYear==1) {
				//happyText.write(this, dispR, data2017);
				barGraph.drawTop(this, dispR, data2017, 0);
				barGraph.drawTop(this, dispR, data2017, 1);
			}
			else if (selYear==2) {
				//happyText.write(this, dispR, data2016);
				barGraph.drawTop(this, dispR, data2016, 0);
				barGraph.drawTop(this, dispR, data2016, 1);
			}
			else if (selYear==3) {
	    		//happyText.write(this, dispR, data2015);
				barGraph.drawTop(this, dispR, data2015, 0);
				barGraph.drawTop(this, dispR, data2015, 1);			
	        }        	
    	popMatrix();
    } // draw
} // class
