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
  void build(float posx, float posy, boolean horizontal){
    PVector ctrpos;
    this.posx = posx;
    this.posy = posy;
    
    
    for (int i=0; i<ctrlabels.length; i++) {
      ctrpos = new PVector(posx, posy);  
    
      controls[i] = new Button(this.ctrlabels[i],ctrpos, this.ctrh, this.ctrw, this.ctrcolors, this.parent);
      controls[i].display();
      if (horizontal) {
        posx = posx+ctrw;
      }    
      else {
        posy = posy+ctrh+2;
      }
    }
  } // build
  
  // Function used to identified the selected button
  int selectedButton(PApplet p, int btncolors[]) {
	this.parent = p;
  //  int optionSelected=0;
    
    for (int i=0; i<controls.length; i++) {
      if (controls[i].isSelected(this.parent) && parent.mousePressed) {
        controls[i].backcol = btncolors[3];
        controls[i].selected = true;
        controls[i].display();
        keeper = i+1;
      }
      else {
        controls[i].backcol = btncolors[0];
        controls[i].selected = false;
        controls[i].display();
      }
    }
    //parent.fill(255);   
    return keeper;
  } // selectedButton
  
  // Keep the selection of the button
  int keepButton(PApplet p, int[] btncolors){   
	  this.parent = p;
    // keep selected button 
    controls[keeper-1].backcol = btncolors[3];
    controls[keeper-1].display();
  
    return keeper;
  } // keepButton
}  // class