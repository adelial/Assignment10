import org.gicentre.utils.stat.BarChart;
import processing.core.*;
import processing.data.*;

public class Graphs {
	
	PApplet parent;
	BarChart barChart;
	int count = 0;

	Graphs() {
		
	}
	
	Graphs(PApplet p){
		this.parent = p;
	}
	
	public void drawTop(PApplet p, String dRegion, Table data, int direction) {
		String[] bgCountry = new String[5];
		float[] bgScore = new float[5];
		count = 0;

		if (direction == 0) {
			data.sortReverse("Score");
		}
		else {
			data.sort("Score");
		}

		if (dRegion == "Americas") {
			for (int r=0; count < 5; r++) {
				//String d = data.getString(r, "DispRegion");
				int d2 = data.getInt(r, "DispRegion2");
				if (d2 == 2) {
					String c = data.getString(r, "Country");
					float s = data.getFloat(r, "Score");
					bgCountry[count] = c;
					bgScore[count] = s;
					count += 1;
				}
			}
		}
		else if (dRegion == "Europe") {
			for (int r=0; count < 5; r++) {
				int d2 = data.getInt(r, "DispRegion2");
				if (d2 == 4) {
					String c = data.getString(r, "Country");
					float s = data.getFloat(r, "Score");
					bgCountry[count] = c;
					bgScore[count] = s;
					count += 1;
				}
			}
		}
		else if (dRegion == "Asia") {
			for (int r=0; count < 5; r++) {
				int d2 = data.getInt(r, "DispRegion2");
				if (d2 == 3) {
					String c = data.getString(r, "Country");
					float s = data.getFloat(r, "Score");
					bgCountry[count] = c;
					bgScore[count] = s;
					count += 1;
				}
			}
		}
		else if (dRegion == "Africa") {
			for (int r=0; count < 5; r++) {
				int d2 = data.getInt(r, "DispRegion2");
				if (d2 == 1) {
					String c = data.getString(r, "Country");
					float s = data.getFloat(r, "Score");
					bgCountry[count] = c;
					bgScore[count] = s;
					count += 1;
				}
			}
		}
		else {
				for (int r=0; r < 5; r++) {
					String c = data.getString(r, "Country");
					float s = data.getFloat(r, "Score");
					bgCountry[r] = c;
					bgScore[r] = s;
				}
		}
		//Display Top 5 Bar Chart
		this.parent.fill(255);
		barChart = new BarChart(this.parent);	
		barChart.setData(bgScore);
		barChart.setBarLabels(bgCountry);
		barChart.setBarGap(10); 
		barChart.showValueAxis(true); 
		barChart.showCategoryAxis(true); 
		barChart.setAxisLabelColour(0);
		barChart.transposeAxes(true);
		if (direction == 0) {
			parent.text("Top 5 Happiest", this.parent.width/1.35f, this.parent.height/2.2f);
			barChart.setBarColour(this.parent.color(7, 170,90));
			barChart.draw(this.parent.width/1.7f, this.parent.height/2.15f, this.parent.width/2.7f, this.parent.height/4.1f);
		}
		else {
			parent.text("Bottom 5 Happiest", this.parent.width/1.35f, this.parent.height/1.37f);
			barChart.setBarColour(this.parent.color(142,84,7));
			barChart.draw(this.parent.width/1.7f, this.parent.height/1.36f, this.parent.width/2.7f, this.parent.height/4.1f);			
		}		
	} //end drawTop	
}//end Graphs Class
