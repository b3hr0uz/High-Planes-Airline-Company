import java.io.*;
import java.util.*;
public class HPAirFlight {
   private ArrayList<Node> list;
   private LinkedList<Vertex> route = new LinkedList<>();
	
	
   public HPAirFlight() {
      list = new ArrayList<>();
   }
	
   public void loadFlightMap(String fileName) throws IOException {
      File infile = new File(fileName);
      try(Scanner in = new Scanner(infile);)
      {
         while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] tokens = line.split(" ");
            Node city = new Node(new Vertex(tokens[0]));
            for(int i = 1; i < tokens.length; ++i) {
               city.addPath(new Vertex(tokens[i]));
            }
            list.add(city);
         }
      }
   }
	
   @SuppressWarnings("unused")
public boolean findFlight(String start, String end) {
	    Stack<Vertex> stack = new Stack<>();
	    Vertex startCity = new Vertex(start, true);
	    Vertex endCity = new Vertex(end, false);
	    stack.push(startCity);
	    ArrayList<Vertex> path;

	    while (!stack.isEmpty()) {
	        Vertex currentCity = stack.peek();
	        String city = currentCity.getCity();

	        if (city.equals(end)) {
	            while (!stack.isEmpty()) {
	                route.addFirst(stack.pop());
	            }
	            System.out.println(route.toString());
	            return true;
	        } else {
	            boolean flightFound = false;
	            path = null;

	            for (Node node : list) {
	                if (node.getCity().equals(currentCity)) {
	                    path = node.getPath();
	                    break;
	                }
	            }

	            if (path != null && !path.isEmpty()) {
	                for (Vertex vertex : path) {
	                    if (!vertex.isVisited()) {
	                        stack.push(vertex);
	                        vertex.setVisited(true);
	                        flightFound = true;
	                        break;
	                    }
	                }
	                if (!flightFound)
	                    stack.pop();
	            } else 
	                stack.pop();
	        }
	    }
	    
	    return false;
	}

	
   public String toString() {
      String result="";
      for(int i = 0; i < list.size(); ++i) {
         result += list.get(i).toString() + "\n";
      }
      return result;
   }

}

class Node{
   private Vertex city;
   private ArrayList<Vertex> path;
   public Node() {
      path = new ArrayList<>();
   }
	
   public Node(Vertex city) {
      this.city = city;
      path = new ArrayList<>();
   }
	
   public void setCity(Vertex city) {
      this.city = city;
   }
	
   public Vertex getCity() {
      return city;
   }
	
   public ArrayList<Vertex> getPath(){
      return path;
   }
	
   public void addPath(Vertex v) {
      path.add(v);
   }
	
   public String toString() {
      return city +"-->"+path;
   }
	
   public boolean equals(Object e) {
      Vertex v = ((Node)e).getCity();
      return this.city.equals(v);
   }
}