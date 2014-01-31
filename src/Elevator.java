import java.awt.*;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Elevator implements ActionListener{

	private JFrame frame;
	private static int currentFloor = 1;
	private static int floorSel = 1;
	private static JTextField floorInput = new JTextField();
	private static Button exit = new Button("Click to Exit");
	private static JTextArea floorNumber = new JTextArea();
	private static JTextArea narratorText = new JTextArea();
	private static JButton btnGo = new JButton("Go!");

	public static void main(String[] args) throws InterruptedException {
		initializeElevator();
		startElevator();	
	}
	
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void initializeElevator() throws InterruptedException {
		
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
	
	public static void startElevator() {
		
		narratorSay(false, "Stanely awoke inside an elevator, 15 buttons, 1 for each floor glowed, before him. A voice prompted him to"+
					" select a floor.\n\n");	
		narratorSay(false, "Please select a floor");
		
		while (true) {
			if (currentFloor < floorSel) { //If the floor selection is above the current floor, invokes the goUp() method
				soundPlay(true, "src/DING.WAV");
				soundPlay(true, "src/ELEVATOR.WAV");
				goUp();
			}
			else if(currentFloor > floorSel) { //If the floor selection is below the current floor, invokes the goDown() method
				soundPlay(true, "src/DING.WAV");
				goDown();
			}
			else if(currentFloor == 13) {
				theTwilightZone();
				break;
			}
			else
				try {
				    TimeUnit.SECONDS.sleep(1);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
		}
		
		
	}
	
	/**
	 * Easter Egg  :D
	 */
	private static void theTwilightZone() {
		
		soundPlay(true, "src/TZONE.WAV");
		narratorSay(true,   "     Out of nowhere, a storm rolled in and a beam of lightning struck the top of the hotel. Then, and ominous voice "+
							"began to speak....\n\n");
		narratorSay(false,  "     There is a fifth dimension beyond that which is known to man. It is a dimension as vast as space and as "+
							"timeless as infinity. It is the middle ground between light and shadow, between science and superstition, "+
							"and it lies between the pit of man's fears and the summit of his knowledge. This is the dimension of "+
							"imagination. It is an area which we call the Twilight Zone.");
		try {
		    TimeUnit.SECONDS.sleep(25);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		floorSel = -1; 
		for(;currentFloor > floorSel; --currentFloor) {
			floorNumber.setText(""+currentFloor);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		narratorText.setEnabled(false);
		btnGo.setEnabled(false);
		floorNumber.setEnabled(false);
		JOptionPane.showMessageDialog(null, ("It appears you died."), "A message from hell", JOptionPane.ERROR_MESSAGE);
		
	}
	
	private static void narratorSay(boolean clear, String message) {
		if (clear == true)
			narratorText.setText(message);
		else
			narratorText.append(message);
	}

	public void actionPerformed(ActionEvent e) {
		
		if ("go".equals(e.getActionCommand())){
			
			String input = floorInput.getText();
	
			
			try {
				floorSel = Integer.parseInt(input);
			}
			catch (NumberFormatException ef) {
				narratorSay(true, "Floor number invalid. Please try Again.");
			}

		}
	}
	
	public static void soundPlay(boolean play, String fileSource) {
		String source = new File(fileSource).toURI().toString();
		Media media = null;
		media = new Media(source);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		if (play == false)
			mediaPlayer.stop();
		else	
			mediaPlayer.play();
	}
	/**
	 * Create the application.
	 */
	public Elevator() {
		initialize();
	}

	private static void goDown() {
		narratorSay(true, "The elevator stated the obvious fact that it was going down, and made that annoying 'ding' sound.");
		for(;currentFloor > floorSel; --currentFloor) {
			floorNumber.setText(""+currentFloor);
			try {
			    TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}	
		}
		floorNumber.setText(""+currentFloor);
		narratorSay(true, "The doors opened and Stanely was on floor "+currentFloor);
	}
	
	
	private static void goUp() {
		narratorSay(true, "The elevator stated the obvious fact that it was going up, and made that annoying 'ding' sound.");
		for(;currentFloor < floorSel; ++currentFloor) {
			floorNumber.setText(""+currentFloor);
			try {
			    TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}	
		}
		floorNumber.setText(""+currentFloor);
		narratorSay(true, "The doors opened and Stanely was on floor "+currentFloor);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 834, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel MainWindow = new JPanel();
		frame.getContentPane().add(MainWindow, BorderLayout.CENTER);
		SpringLayout sl_MainWindow = new SpringLayout();
		sl_MainWindow.putConstraint(SpringLayout.NORTH, floorNumber, 10, SpringLayout.NORTH, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.WEST, floorNumber, 374, SpringLayout.WEST, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.SOUTH, floorNumber, 64, SpringLayout.NORTH, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.EAST, floorNumber, -392, SpringLayout.EAST, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.WEST, btnGo, 450, SpringLayout.WEST, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.EAST, floorInput, -6, SpringLayout.WEST, btnGo);
		MainWindow.setLayout(sl_MainWindow);
		
		
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
		sl_MainWindow.putConstraint(SpringLayout.SOUTH, floorInput, 0, SpringLayout.SOUTH, checkbox);
		sl_MainWindow.putConstraint(SpringLayout.NORTH, btnGo, 0, SpringLayout.NORTH, checkbox);
		sl_MainWindow.putConstraint(SpringLayout.SOUTH, checkbox, -10, SpringLayout.SOUTH, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.EAST, checkbox, -10, SpringLayout.EAST, MainWindow);
		MainWindow.add(checkbox);
		
		ScrollPane Narrator = new ScrollPane();
		sl_MainWindow.putConstraint(SpringLayout.NORTH, Narrator, 79, SpringLayout.NORTH, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.SOUTH, Narrator, -20, SpringLayout.NORTH, checkbox);
		sl_MainWindow.putConstraint(SpringLayout.WEST, Narrator, 10, SpringLayout.WEST, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.EAST, Narrator, 0, SpringLayout.EAST, checkbox);
		MainWindow.add(Narrator);
		narratorText.setAutoscrolls(false);
		narratorText.setLineWrap(true);
		
		
		narratorText.setToolTipText("Narrator text and instructions");
		narratorText.setFont(new Font("Times New Roman", Font.BOLD, 18));
		narratorText.setWrapStyleWord(true);
		narratorText.setEditable(false);
		Narrator.add(narratorText);
		
		sl_MainWindow.putConstraint(SpringLayout.WEST, exit, 10, SpringLayout.WEST, MainWindow);
		sl_MainWindow.putConstraint(SpringLayout.SOUTH, exit, 0, SpringLayout.SOUTH, checkbox);
		exit.setEnabled(false);
		MainWindow.add(exit);
		MainWindow.add(floorInput);
		floorInput.setColumns(10);
		
		JLabel lblFloorSelection = new JLabel("Floor Selection: ");
		sl_MainWindow.putConstraint(SpringLayout.SOUTH, lblFloorSelection, 0, SpringLayout.SOUTH, checkbox);
		sl_MainWindow.putConstraint(SpringLayout.EAST, lblFloorSelection, -6, SpringLayout.WEST, floorInput);
		MainWindow.add(lblFloorSelection);
		MainWindow.add(btnGo);
		btnGo.setActionCommand("go");
		btnGo.addActionListener(this);
	}
}
