import java.io.*;
public class TestFlight {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		HPAirFlight hp = new HPAirFlight();
		hp.loadFlightMap("flights");
		System.out.println(hp);
		if(hp.findFlight("S", "X")) 
			System.out.println("Path found");
		else
			System.out.println("No path found");
	}
}
