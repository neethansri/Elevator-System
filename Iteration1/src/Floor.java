import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Floor implements Runnable {
	
	boolean flag = true; //when its true its sending and false receiving
	
	public Floor() {
		
	}

	@Override
	public void run() {
		readFile();
		
	}
	
	public void readFile() {
		
		try {
			File f = new File("F:\\SYSSC3303\\New folder\\Iteration1\\floorInfo.txt");
			Scanner read = new Scanner(f);
			while(read.hasNextLine()) {
				String info = read.nextLine();
				String data[] =info.split(" ");
				System.out.println(data[0]);
			}
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
