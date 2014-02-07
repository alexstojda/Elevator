import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * A compilation of three people's lack of personal lives. More info to come when we actually know what we are doing.
 * Its probably going to have something to do with an Elevator and multiple references to the Stanley Parable.
 * 
 * @author Alex
 * @author Andrew
 * @version beta0.1
 *
 */
public class Elevator {

	private static Random rand;
	private JFrame frame;
	private static int currentFloor = 1;
	private static int floorSel = 1;
	private static Button exit = new Button("Click to Exit");
	private static JTextArea floorNumber = new JTextArea();
	private static JTextArea narratorText = new JTextArea();

	private static JButton b0 = new JButton("B");
	private static JButton b1 = new JButton("G");
	private static JButton b2 = new JButton("2");
	private static JButton b3 = new JButton("3");
	private static JButton b4 = new JButton("4");
	private static JButton b5 = new JButton("5");
	private static JButton b6 = new JButton("6");
	private static JButton b7 = new JButton("7");
	private static JButton b8 = new JButton("8");
	private static JButton b9 = new JButton("9");
	private static JButton b10 = new JButton("10");
	private static JButton b11 = new JButton("11");
	private static JButton b12 = new JButton("12");
	private static JButton b13 = new JButton("13");
	private static JButton b14 = new JButton("14");
	private static JButton b15 = new JButton("15");
	private static JButton b69 = new JButton("PH");

	public static void main(String[] args) throws InterruptedException {
		initializeElevator();
		startElevator();	
	}
	
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	private static void initializeElevator() throws InterruptedException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Elevator window = new Elevator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		    }
		});
		latch.await();
	}
	
	private static void startElevator() {
		
		narratorSay("Stanley awoke inside an elevator, 15 buttons, 1 for each floor glowed, before him. A voice prompted him to"+
					" select a floor.\n\n");	
		narratorSay("Please select a floor");
		
		while (true) {
			if (currentFloor < floorSel) { //If the floor selection is above the current floor, invokes the goUp() method
				if (floorSel==69)
					goUp(100);
				else
					goUp();
			}
			else if(currentFloor > floorSel) { //If the floor selection is below the current floor, invokes the goDown() method
				if (currentFloor==69)
					goDown(100, true);
				else
					goDown();
				soundPlay("DING.WAV");
				soundPlay("ELEVATOR.WAV");
				goUp();
			}
			else if(currentFloor == 13) {
				theTwilightZone();
				break;
			}
			else if(currentFloor == 69)
				theCrimsonFuckr();
			else
				delay(1000);
		}
		
		
	}
	
	/**
	 * Easter Egg  :D
	 */
	private static void theTwilightZone() {
		delay(500);
		soundPlay("TZONE.WAV");
		narratorSay(true,   "     Out of nowhere, a storm rolled in and a beam of lightning struck the top of the hotel. Then, and ominous voice "+
							"began to speak....\n\n");
		delay(5000);
		narratorSay("     There is a fifth dimension beyond that which is known to man. It is a dimension as vast as space and as "+
							"timeless as infinity. It is the middle ground between light and shadow, between science and superstition, "+
							"and it lies between the pit of man's fears and the summit of his knowledge. This is the dimension of "+
							"imagination. It is an area which we call the Twilight Zone.");
		delay(4000);
		floorSel = 0;
		goDown(10, false);
		death();
	}
	
	/**
	 * Disables everything because you are dead...
	 */
	private static void death() {
		narratorText.setEnabled(false);
		floorNumber.setEnabled(false);
		b0.setEnabled(false);
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		b6.setEnabled(false);
		b7.setEnabled(false);
		b8.setEnabled(false);
		b9.setEnabled(false);
		b10.setEnabled(false);
		b11.setEnabled(false);
		b12.setEnabled(false);
		b13.setEnabled(false);
		b14.setEnabled(false);
		b15.setEnabled(false);
		b69.setEnabled(false);
		JOptionPane.showMessageDialog(null, ("It appears you died."), "A message from hell", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Easter Egg 2
	 */
	private static void theCrimsonFuckr() {
		soundPlay("DISCO.WAV");
		narratorSay("\n\nWOOHOOOO PARTAAAAAAAAAAAAAAY!!!!!!!!!!!!!!");
		delay(20000);
	}

	/**
	 * Writes text into the narratorText box char by char, creating a typing effect.
	 @param text Text to write in the box
	 */
	private static void narratorSay(String text) {narratorSay(false, text);}
	
	/**
	 * Writes text into the narratorText box char by char, creating a typing effect
	 * @param clear boolean to clear textBox
	 * @param text Text to write in the box
	 */
	private static void narratorSay(boolean clear, String text) {
		
		if (clear) {
			narratorText.setText("");
			delay(300);
		}
		
		int length = text.length();
		for (int pos = 0; pos < length; pos++) {
			narratorText.append(Character.toString(text.charAt(pos)));
			delay(30);
		}
	}

	/**
	 * Delays all output.
	 * @param millis delay in milliseconds
	 */
	private static void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	

	/**
	 * **NOT IMPLEMENTED**
	 * Randomizes and plays a typing noise. To be used by narratorSay()
	 */
	@SuppressWarnings("unused")
	private static void typingSound() { //Can't properly implement in narratorSay, causes error/crash...
		String Sound;
		if (rand.nextInt(2)==0)
			Sound = "TYPE";
		else
			Sound = "TYPE2";
		soundPlay(Sound+".WAV");
	}
		
	/**
	 * Media player implemented using JavaFX libraries, play .WAV and .MP3 without problems
	 * @param fileSource name of media file to play 
	 */
	public static void soundPlay(String fileSource) {
		String source = new File("src/"+fileSource).toURI().toString();
		Media media = null;
		media = new Media(source);
		MediaPlayer mediaPlayer = new MediaPlayer(media);	
		mediaPlayer.play();
	}
	
	/**
	 * Create the application.
	 */
	public Elevator() {
		initialize();
	}

	/**
	 * Used to find the ending string for a specific floor.
	 * @return ending for specific floor, example 1st, 2nd, 7th, etc.
	 */
	private static String end() {
		String ending = "st ";
		switch (currentFloor) {
		case 1:
			ending = "st ";
			break;
		case 2:
			ending = "nd ";
			break;
		case 3:
			ending = "rd ";
			break;
		default:
			ending = "th ";
			break;
		}
		return ending;

	}
	
	/**
	 * Makes the elevator go up, sends a second delay between floor change
	 */
	private static void goUp() {goUp(1000);}
	
	/**
	 * Makes elevator go up
	 * @param delay delay between the floor change
	 */
	private static void goUp(int delay) {
		narratorSay(true, "The elevator stated the obvious fact that it was going up, and made that annoying 'ding' sound.");
		soundPlay("DING.WAV");
		soundPlay("ELEVATOR.WAV");
		for(;currentFloor < floorSel; ++currentFloor) {
			floorNumber.setText(""+currentFloor);
			delay(delay); }
		floorNumber.setText(""+currentFloor);
		soundPlay("DING.WAV");
		narratorSay(true, "The doors opened and Stanley was on the "+ currentFloor + end() + "floor.");
	}
	
	/**
	 * Makes the elevator go down, sets a one second delay between floor change with elevator prompts
	 * enabled.
	 */
	private static void goDown() {goDown(1000, true);}
	
	/**
	 * Makes the elevator go down, sets a one second delay between floor change.
	 * @param showMessage If true, will display the elevator messages
	 */
	@SuppressWarnings("unused")
	private static void goDown(boolean showMessage) {goDown(1000, showMessage);}
	
	/**
	 * Makes elevator go down
	 * @param delay delay between the floor change
	 * @param showMessage If true, will display the elevator messages
	 */
	private static void goDown(int delay, boolean showMessage) {
		if (floorSel!=-1) {
			if (showMessage == true)
				narratorSay(true, "The elevator stated the obvious fact that it was going down, and made that annoying 'ding' sound.");
			soundPlay("DING.WAV");
			soundPlay("ELEVATOR.WAV");
		}
		for(;currentFloor > floorSel; --currentFloor) {
			floorNumber.setText(""+currentFloor);
			delay(delay); }
		floorNumber.setText(""+currentFloor);
		if (floorSel!=-1) {
			soundPlay("DING.WAV");
			if (showMessage == true)
				narratorSay(true, "The doors opened and Stanley was on the "+ currentFloor + end() + "floor.");
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Elevator Adventures");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel MainWindow = new JPanel();
		frame.getContentPane().add(MainWindow, BorderLayout.CENTER);
		MainWindow.setLayout(null);
		floorNumber.setBounds(261, 25, 49, 48);
		
		
		floorNumber.setToolTipText("This is your current floor");
		floorNumber.setText(""+currentFloor);
		floorNumber.setBackground(SystemColor.control);
		MainWindow.add(floorNumber);
		floorNumber.setEditable(false);
		floorNumber.setFont(new Font("Monospaced", Font.BOLD, 37));
		floorNumber.setLineWrap(true);
		floorNumber.setForeground(Color.BLACK);
		
		Checkbox checkbox = new Checkbox("Enable Sound");
		checkbox.setVisible(false);
		checkbox.setBounds(632, 500, 100, 22);
		MainWindow.add(checkbox);
		
		ScrollPane Narrator = new ScrollPane();
		Narrator.setBounds(10, 79, 550, 483);
		MainWindow.add(Narrator);
		narratorText.setAutoscrolls(false);
		narratorText.setLineWrap(true);
		
		
		narratorText.setToolTipText("Narrator text and instructions");
		narratorText.setFont(new Font("Times New Roman", Font.BOLD, 18));
		narratorText.setWrapStyleWord(true);
		narratorText.setEditable(false);
		Narrator.add(narratorText);
		exit.setVisible(false);
		exit.setBounds(630, 472, 78, 22);
		MainWindow.add(exit);
		
		
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=0;
			}
		});
		b0.setBounds(580, 385, 61, 61);
		b0.setAlignmentX(Component.CENTER_ALIGNMENT);
		b0.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b0);
		
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=1;
			}
		});
		b1.setBounds(647, 385, 61, 61);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b1);
		
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=2;
			}
		});
		b2.setBounds(712, 385, 61, 61);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b2);
		
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=3;
			}
		});
		b3.setBounds(580, 318, 61, 61);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b3);
		
		
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=4;
			}
		});
		b4.setBounds(647, 318, 61, 61);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b4);
		
		
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=5;
			}
		});
		b5.setBounds(712, 318, 61, 61);
		b5.setAlignmentX(Component.CENTER_ALIGNMENT);
		b5.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b5);
		
		
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=6;
			}
		});
		b6.setBounds(580, 251, 61, 61);
		b6.setAlignmentX(Component.CENTER_ALIGNMENT);
		b6.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b6);
				
		
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=7;
			}
		});
		b7.setBounds(647, 251, 61, 61);
		b7.setAlignmentX(Component.CENTER_ALIGNMENT);
		b7.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b7);
		
		
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=8;
			}
		});
		b8.setBounds(712, 251, 61, 61);
		b8.setAlignmentX(Component.CENTER_ALIGNMENT);
		b8.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b8);
		

		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=9;
			}
		});
		b9.setBounds(580, 184, 61, 61);
		b9.setAlignmentX(Component.CENTER_ALIGNMENT);
		b9.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b9);
		

		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=10;
			}
		});
		b10.setBounds(647, 184, 61, 61);
		b10.setAlignmentX(Component.CENTER_ALIGNMENT);
		b10.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b10);
		

		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=11;
			}
		});
		b11.setBounds(712, 184, 61, 61);
		b11.setAlignmentX(Component.CENTER_ALIGNMENT);
		b11.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b11);
		

		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=12;
			}
		});
		b12.setBounds(580, 117, 61, 61);
		b12.setAlignmentX(Component.CENTER_ALIGNMENT);
		b12.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b12);
		

		b13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=13;
			}
		});
		b13.setBounds(647, 117, 61, 61);
		b13.setAlignmentX(Component.CENTER_ALIGNMENT);
		b13.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b13);
		

		b14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=14;
			}
		});
		b14.setBounds(712, 117, 61, 61);
		b14.setAlignmentX(Component.CENTER_ALIGNMENT);
		b14.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b14);
		
		
		b15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=15;
			}
		});
		b15.setBounds(580, 50, 61, 61);
		b15.setAlignmentX(Component.CENTER_ALIGNMENT);
		b15.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b15);
		
		
		b69.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floorSel=69;
			}
		});
		b69.setBounds(688, 50, 85, 61);
		b69.setAlignmentX(Component.CENTER_ALIGNMENT);
		b69.setFont(new Font("Tahoma", Font.BOLD, 20));
		MainWindow.add(b69);
	}
}
