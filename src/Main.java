import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		
	    JFrame frame = new JFrame();
	    String text = "";
	    text = JOptionPane.showInputDialog(frame, "Enter your message");
	    if (text == null) {
	      text = "SOS";
	      System.out.println("No message entered, sending for help!!");
	    }
		frame.dispose();
	    
		text = text.toLowerCase();
		
		char[] charList = text.toCharArray();
		
		LetterToMorseTable dictionary = new LetterToMorseTable();
		
		for(int x = 0;x < charList.length;x++) {
			
			//Check for spaces between words and allow for a pause
			if(("" + charList[x]).equals(" ")) {
				System.out.print("   ");
				playSound(1500, 0, 0);
			}
			else if(dictionary.letterTranslator.containsKey(charList[x])){
				
				String morseCode = dictionary.letterTranslator.get(charList[x]);
				
				char[] morseArray = morseCode.toCharArray();
				
				for(int y = 0;y < morseArray.length;y++) {
					
					System.out.print(morseArray[y]);
					
					if(morseArray[y] == '.') {
						playSound(100, 0.5, 750);
					}
					else {
						playSound(250, 0.5, 750);
					}
					
				}
				
				//Take a pause between characters in a word
				System.out.print(" ");
				playSound(750, 0, 0);
				
			}
		}
		
		

	}
	
	public static void playSound(int duration, double volume, int frequency){
		try {
			Sound.tone(frequency, duration, volume);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
