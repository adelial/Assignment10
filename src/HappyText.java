import processing.core.*;
import processing.data.*;

public class HappyText {
	PApplet parent;
	float x = 1000;
	float y = 800;
	float z = -300;

	HappyText(){		
	}
	
	public HappyText(PApplet p) {
		this.parent = p;
	}

	public void write(PApplet p, String dRegion, Table data) {

		parent.stroke(0);
		parent.strokeWeight(5);
		parent.fill(255);
		parent.textSize(14);
		parent.textAlign(parent.CENTER);
		parent.rotateX(parent.PI/4);
		
		if (dRegion == "Americas") {
			for (TableRow row : data.findRows("Americas", "DispRegion")) {
				parent.text(row.getString("Country")+" - "+row.getFloat("Score"), 1000, y, z);
			}
		}
		else if (dRegion == "Europe") {
			for (TableRow row : data.findRows("Europe", "DispRegion")) {
				parent.println(row.getString("Country") + " - " + row.getFloat("Score"));
			}
		}
		else if (dRegion == "Asia") {
			for (TableRow row : data.findRows("Asia", "DispRegion")) {
				parent.println(row.getString("Country") + " - " + row.getFloat("Score"));
			}
		}
		else if (dRegion == "Africa") { 
			for (TableRow row : data.findRows("Africa", "DispRegion")) { 
				parent.println(row.getString("Country") + " - " + row.getFloat("Score"));
			}
		}
		else {
			for (TableRow row : data.rows()) {
				//parent.rotateX(parent.PI/4);
				//parent.translate(x, y, z);
				parent.println(x, y, z);
				parent.text(row.getString("Country")+" - "+row.getFloat("Score"), 1000, y);
			}
		}
		y += -0.1;
	}

}
