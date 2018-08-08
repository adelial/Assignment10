// Menu for selection use button class
// it can be display horizontal or vertical 
import processing.core.*;
import processing.data.*;

class Menu {
  float posx, posy;
  String[] ctrlabels;
  int[] ctrcolors;
  float ctrh, ctrw;
  Button[] controls;
  int keeper=1;
  PApplet parent;
 
  Menu(){
  }
  
  Menu(String[] ctrlabels, int[] ctrcolors){
   this.ctrlabels = ctrlabels;
   this.ctrcolors = ctrcolors;
   controls = new Button[ctrlabels.length];
  }
  
  Menu(String[] ctrlabels, float ctrh, float ctrw, int[] ctrcolors, PApplet p){
   this.ctrlabels = ctrlabels;
   this.ctrh = ctrh;
   this.ctrw = ctrw;
   this.ctrcolors = ctrcolors;
   this.parent = p;
   controls = new Button[ctrlabels.length];
  }
  
  // build function - display the menu in the defined x,y position and orientation (horizontal = true)
  void build(float posx, float posy, boolean horizontal, PApplet p){
    PVector ctrpos;
    this.posx = posx;
    this.posy = posy;
    this.parent = p;
    
    for (int i=0; i<ctrlabels.length; i++) {
      ctrpos = new PVector(posx, posy);  
    
      controls[i] = new Button(this.ctrlabels[i],ctrpos, this.ctrh, this.ctrw, this.ctrcolors, this.parent);
      controls[i].display(this.parent);
      if (horizontal) {
        posx = posx+ctrw;
      }    
      else {
        posy = posy+ctrh;
      }
    }
  } // build
  
  // Function used to identified the selected button
  int selectedButton(PApplet p, int[] btncolors) {
	  this.parent = p;
    String optionSelected="";
    
    for (int i=0; i<controls.length; i++) {
      if (controls[i].isSelected(this.parent) && parent.mousePressed) {
        controls[i].backcol = btncolors[3];
        controls[i].selected = true;
        controls[i].display(this.parent);
        optionSelected = controls[i].label;
      }
      else {
        controls[i].backcol = btncolors[0];
        controls[i].selected = false;
        controls[i].display(this.parent);
      }
    }
    parent.fill(255);
    // 	Year selection to display 2017
    switch(optionSelected) {
          case ("2017"): 
        	keeper = 1;
            break;
          case ("2016"):
        	keeper = 2;
            break;
          case ("2015"):
        	//world.VizData(this.parent, table2015, diameter);  // Use to add the visualization for the scores
            keeper = 3;
            break;
        //  case ("TIME"):
        //    keeper = 4;
         //   break;  
          default:
            keeper = 1;
            parent.fill(0);
    }    
    return keeper;
  } // selectedButton
  
  // Keep the selection of the button
  int keepButton(PApplet p, int[] btncolors){   
	  this.parent = p;
    // keep selected button 
    controls[keeper-1].backcol = btncolors[3];
    controls[keeper-1].display(this.parent);
    switch(keeper) {
          case (1):  // Player Ages graph and table
            keeper = 1;
            break;
          case (2):  // Goal Time graph     
            keeper = 2;
            break;
          case (3):  // Market value graph
            keeper = 3;
            break;
          case (4):
            keeper = 4;
            break;  
          default:
            keeper = 1;
    }   
    return keeper;
  }
}