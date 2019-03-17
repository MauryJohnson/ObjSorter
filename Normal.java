import java.util.ArrayList;

/**
 * Normal Vertex
 * @author jason
 *
 */
public class Normal {
	
	int PrevIdx;
	
	/**
	 * All coordinates of norm
	 */
	ArrayList<Double> Data;
	
	public Normal(ArrayList<Double> Data,int PIdx) {
		this.Data=Data;
		this.PrevIdx=PIdx;
	}
	
	public String toString() {
		String s = "vn ";
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
	
	public int compareTo(Normal v,int Axis) {
		if(this.Data.get(Axis)>v.Data.get(Axis)) {
			return 1;
		}
		else if(this.Data.get(Axis)<v.Data.get(Axis)) {
			return -1;
		}
		return 0;
	}
}
