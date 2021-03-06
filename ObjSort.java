import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main Function of parsing
 * @author jason
 *
 */
public class ObjSort {

	public static void main(String[] args) {
		String content = null;
		ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
		ArrayList<Normal> NVertices = new ArrayList<Normal>();
		ArrayList<Face> Faces = new ArrayList<Face>();
		
		try {
			///////////////////////////////FILE////////////////////////////////////////
			content = new Scanner(new File("src/RLeg.obj")).useDelimiter("\\Z").next();
			///////////////////////////////////////////////////////////////////////////
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//System.out.println(content);
		
		//int Begin = 0;
		
		int IDX = 0;
		
		//Iterate until reach first vertex
		for(int i=0; i<content.length();i+=1) {
			if(content.charAt(i)=='v')
				break;
			//Begin+=1;
		}
		
		int VIdx = 0;
		int NVIdx = 0;
		
		String[] Data = content.split("\n");
		for(int i=0;i<Data.length;i+=1) {
			String [] s = Data[i].split(" ");
			
			ArrayList<Double> D = new ArrayList<Double>();
		
			if(Data[i].charAt(0)=='v' && Data[i].charAt(1)=='n') {
				for(int j=1;j<s.length;j+=1) {
					D.add(Double.parseDouble(s[j]));
				}
					NVertices.add(new Normal(D,NVIdx+1));
					NVIdx+=1;
			}
			
			else if(Data[i].charAt(0)=='v') {
				for(int j=1;j<s.length;j+=1) {
					D.add(Double.parseDouble(s[j]));
				}
				Vertices.add(new Vertex(D,VIdx+1));
				VIdx+=1;
			}
			
			else if(Data[i].charAt(0)=='f') {
				Faces.add(new Face(Data[i],NumSlashes(Data[i])));
			}
			
		}
		
		///////////////////////////////////// SORTING ///////////////////////////////////////////////
		System.out.println("Vertices\n"+Vertices);
		Vertices.sort((A,B)->B.compareTo(A, 1));
		System.out.println("MAX SORT Vertices\n"+Vertices);
		
		System.out.println("Normal Vertices\n"+NVertices);
		NVertices.sort((A,B)->B.compareTo(A,1));
		System.out.println("MAX SORT Normal Vertices\n"+NVertices);
		//////////////////////////////////// SORTING ///////////////////////////////////////////////
		
		System.out.println("FACES\n"+Faces);
		for(int i=0;i<Vertices.size();i+=1) {
			
			for(int j=0; j<Faces.size();j+=1) {
				
				for(int k=0; k<Faces.get(j).VerticesIndexes.size();k+=1) {
					if(Vertices.get(i).PrevIdx==Faces.get(j).VerticesIndexes.get(k)) {
						System.out.println("Updating V:"+Faces.get(j).VerticesIndexes.get(k)+ " To:"+(i+1));
						Faces.get(j).VerticesIndexes.set(k, i+1);
					}
				}
				
			}
			
		}
		
		for(int i=0;i<NVertices.size();i+=1) {
			
			for(int j=0; j<Faces.size();j+=1) {
				
				for(int k=0; k<Faces.get(j).NVerticesIndexes.size();k+=1) {
					
					if(NVertices.get(i).PrevIdx==Faces.get(j).NVerticesIndexes.get(k)) {
						System.out.println("Updating NV:"+Faces.get(j).NVerticesIndexes.get(k)+ " To:"+(i+1));
						Faces.get(j).NVerticesIndexes.set(k, i+1);
					}
					
				}
				
			}
			
		}
		
		System.out.println("SORTED FACES\n"+Faces);
		
		System.out.println("Number of Vertices:"+Vertices.size()+" Number of Norms:"+NVertices.size()+" Number of Faces:"+Faces.size());
		
		DTF(content,Vertices,NVertices,Faces);
		
	}
	
	
	/**
	 * Dump all updates contents to file
	 * @param content
	 * @param vertices
	 * @param nVertices
	 * @param faces
	 */
	private static void DTF(String content, ArrayList<Vertex> Vertices, ArrayList<Normal> NVertices,
			ArrayList<Face> Faces) {
		
		PrintWriter writer = null;
		try {
			///////////////////////////////FILE////////////////////////////////////////
			writer = new PrintWriter("src/RLegS.obj", "UTF-8");
			///////////////////////////////////////////////////////////////////////////
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//writer.println("The first line");
		//writer.println("The second line");
		//writer.close();
		
		int VIdx = 0;
		int NVIdx = 0;
		int FIdx = 0;
		
		
		//Traditional Storing OBJ File
		
		// TODO Auto-generated method stub
			
			String[] O = content.split("\n");
			for(int i=0; i<O.length;i+=1) {
				if(O[i].charAt(0)=='v' && O[i].charAt(1)=='n') {
					writer.print(NVertices.get(NVIdx));
					NVIdx+=1;
				}
				else if(O[i].charAt(0)=='v' ) {
					writer.print(Vertices.get(VIdx));
					VIdx+=1;
				}
				else if(O[i].charAt(0)=='f') {
					writer.print(Faces.get(FIdx));
					FIdx+=1;
				}
				else {
					String s = "";
					for(int k=0; k<O[i].length();k+=1) {
						s+=O[i].charAt(k);
					}
					writer.println(s);
				}
			}
			
			
			/////////////////////////////// GROUPING  BY//////////////////////////////////////
			/*
			//Store all Vertices grouping by each face!!!
			for(int i=0; i<Faces.size();i+=1) {
				writer.println("//FACE//");
				for(int j=0; j<Faces.get(i).VerticesIndexes.size();j+=1) {
					writer.print(Vertices.get(Faces.get(i).VerticesIndexes.get(j)-1));
				}
				writer.print("//FACE//");
			}
			*/
			/*
			//Store all Normals grouping by each face
			for(int i=0; i<Faces.size();i+=1) {
				for(int j=0; j<Faces.get(i).NVerticesIndexes.size();j+=1)
					writer.print(NVertices.get(Faces.get(i).NVerticesIndexes.get(j)-1));
			}
			
			//Store all faces
			for(int i=0; i<Faces.size();i+=1)
				writer.print(Faces.get(i));
			*/
			
			writer.close();
	}


	static public int NumSlashes(String s) {
		boolean Slash = false;
		int NumSlash = 0;
		for(int i=0; i<s.length();i+=1) {
			if(s.charAt(i)=='/') {
				Slash = true;
				NumSlash+=1;
			}
			else if(Character.isDigit(s.charAt(i)) && Slash) {
				break;
			}
		}
		return NumSlash;
	}
	
}
