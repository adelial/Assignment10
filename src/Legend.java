// Menu for selection use button class
// it can be display horizontal or vertical 
import processing.core.*;

public class Legend {
	int swidth;
	int sheight;
	int graphcolors[]= {0,0};
	String captions[] = {"One","Two"};
	int backcolor;
	int strokeColor;
	boolean drawStroke;
	PApplet parent;
	
	Legend(){
	}
	Legend(PApplet p){
		this.parent = p;
	}
	Legend(int swidth, int sheight,	int graphcolors[], String captions[],	int backcolor, int strokeColor,	boolean drawStroke, PApplet p){
		this.swidth = swidth;
		this.sheight = sheight;
		this.graphcolors = graphcolors;
		this.captions = captions;
		this.backcolor = backcolor;
		this.strokeColor = strokeColor;
		this.drawStroke = drawStroke;
		this.parent = p;
	}

	void draw(PApplet p, int posx, int posy, String title) {
		int j=20;
		this.parent = p;
		
		parent.fill(backcolor);
		parent.pushMatrix();
			parent.translate(posx, posy, 0);
			parent.rect(0,0, this.swidth, this.sheight);
			parent.fill(0);
			parent.text(title,25, 10);
			for (int i=0; i<graphcolors.length; i++) {
				parent.fill(parent.color(graphcolors[i]));
				parent.rect(15,16+j,10,12);
				parent.fill(0);
				parent.text(captions[i], 30, 22+j);
				j+=25;
			}			
		parent.popMatrix();		
	}
}
