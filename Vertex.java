import java.util.ArrayList;

/**
 * Vertex from beginning
 * @author jason
 *
 */
public class Vertex {
	
	int PrevIdx;
	
	/**
	 * All Coordinates of V
	 */
	ArrayList<Double> Data;
	
	public Vertex(ArrayList<Double> Data, int PIdx) {
		this.Data=Data;
		this.PrevIdx=PIdx;
	}
	
	public String toString() {
		String s = "v ";
		for(int i=0; i<Data.size();i+=1) {
			s+=Data.get(i);
			if(i!=Data.size()-1) {
				s+=" ";
			}
			else {
				s+="\n";
			}
		}
		return s;
	}
	
	public int compareTo(Vertex v,int Axis) {
		if(this.Data.get(Axis)>v.Data.get(Axis)) {
			return 1;
		}
		else if(Data.get(Axis)<v.Data.get(Axis)) {
			return -1;
		}
		return 0;
	}
}
