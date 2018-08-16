// Button - UI control to be used in the menu
import processing.core.*;

class Button{
  PVector pos;
  int backcol, labelcolor, mousecolor, selcolor;
  int[] controlColors;
  float bh, bw; 
  String label;
  Boolean selected=false;
  PApplet parent;

  Button() {
  }
  
  Button(String label, PVector pos, float bh, float bw, int[] controlColors, PApplet p)  {
    this.pos = pos;
    this.label = label;
    this.bh = bh;
    this.bw = bw;
    this.controlColors = controlColors;
    this.backcol = controlColors[0];
    this.labelcolor = controlColors[1];
    this.mousecolor = controlColors[2];
    this.selcolor = controlColors[3];
    this.parent = p;
  }
  
  // display the button  
  void display() {
	parent.fill(parent.color(this.backcol));
    
	parent.noStroke();
	parent.rect(this.pos.x, this.pos.y, this.bw, this.bh, 7); //round border
    
	parent.fill(parent.color(this.labelcolor));
    float wlabel= parent.textWidth(this.label);
    parent.textAlign(parent.LEFT,parent.CENTER);
    parent.text(this.label, this.pos.x+(this.bw-wlabel)/2, this.pos.y+15);       
  } // display

  // function to determine if the button is selected
  boolean isSelected(PApplet p) {
	  this.parent = p;
    if (parent.mouseX > this.pos.x && parent.mouseX < this.pos.x + bw && parent.mouseY > this.pos.y && parent.mouseY < this.pos.y + bh ) 
      return true;
    else return false;
  } // isSelected

} // class