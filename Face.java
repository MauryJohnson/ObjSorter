import java.util.ArrayList;

/**
 * Face parsed from the file
 * @author jason
 *
 */
public class Face {
	
	int NumSlashes = 0;
	
	/**
	 * Vertices
	 */
	ArrayList<Integer> VerticesIndexes = new ArrayList<Integer>();
	
	/**
	 * Some unknows value
	 */
	ArrayList<Integer> WhatsIndexes= new ArrayList<Integer>();;
	
	/**
	 * Normal Vertices
	 */
	ArrayList<Integer> NVerticesIndexes= new ArrayList<Integer>();;
	
	public Face(String F,int NS) {
		this.NumSlashes = NS;
		Parse(F.substring(2,F.length()));
	}

	/**
	 * Parsing this face string for ALL vals
	 * @param f
	 */
	private void Parse(String f) {
		// TODO Auto-generated method stub
		String[] Vs = f.split(" ");
		for(int i=0; i<Vs.length;i+=1) {
			String[] Vs2 = Vs[i].split(Slashes());
			
			int j=0;
			while(j<Vs2.length) {
				switch(NumSlashes) {
				case 1:
					VerticesIndexes.add(Integer.parseInt(Vs2[j]));
					WhatsIndexes.add(Integer.parseInt(Vs2[j+1]));
					NVerticesIndexes.add(Integer.parseInt(Vs2[j+2]));
					j+=3;
					break;
				case 2:
					VerticesIndexes.add(Integer.parseInt(Vs2[j]));
					NVerticesIndexes.add(Integer.parseInt(Vs2[j+1]));
					j+=2;
					break;
				default:
						System.err.println("Bad Num of Vertices");
						System.exit(-1);
				}
			}
			
		}
		
		System.out.println("PARSED\n"+toString());
	}
	
	/**
	 * Return Corresponding Slashes based upon NumSlashes
	 * @return
	 */
	private String Slashes() {
		// TODO Auto-generated method stub
		switch(NumSlashes) {
		
		case 1:
			return "/";
		
		case 2:
			return "//";
		
		default:
				System.err.println("Bad Num of Vertices");
				System.exit(-1);
		}
		
		return null;
	}

	/**
	 * Convert to readable face string
	 * Assumes that Vertices.size()==Whats.size()==NVertices.size()
	 */
	public String toString() {
		String Res = "f ";
		String v = "";
		String w = "";
		String nv = "";
		
		for(int i=0;i<VerticesIndexes.size();i+=1) {
			switch(NumSlashes) {
			
			case 1:
				Res+=VerticesIndexes.get(i)+"/"+WhatsIndexes.get(i)+"/"+NVerticesIndexes.get(i)+" ";
				break;
			
			case 2:
				Res+=VerticesIndexes.get(i)+"/"+/*Whats.get(i)+*/"/"+NVerticesIndexes.get(i)+" ";
				break;
			
			default:
					System.err.println("Bad Num of Vertices");
					System.exit(-1);
			}
		}
		
		return Res+"\n";
	}
	
}
