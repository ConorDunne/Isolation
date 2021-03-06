import util.UnitTests;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */


public class MainWindow {
	private static final int FrameWidth = 960;
	private static final int FrameHeight = 680;

	private static final JFrame frame = new JFrame("Isolation");   // Change to the name of your game
	private static Model gameworld;
	private static Viewer gameCanvas;
	private static final int TargetFPS = 100;
	private static int gameState = -3;
	private static JLabel Credits;
	private static JLabel BackgroundImageForStartMenu;
	private static JLabel Title;
	private static Clip menuMusic;
	private static JButton playButton;
	private static JButton exitButton;
	private static Clip gameMusic;
	private static Clip deathMusic;
	private final KeyListener Controller = new Controller();

	public MainWindow() {
		frame.setSize(FrameWidth, FrameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);

		File Background = new File("res/GUI/Menu/Stars.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		File title = new File("res/GUI/Menu/Title.png");
		File play = new File("res/GUI/Menu/play.png");
		File exit = new File("res/GUI/Menu/exit.png");
		File cr = new File("res/GUI/Menu/Credits.png");

		File menu = new File("res/Audio/Music/Isolation.wav");
		File game = new File("res/Audio/Music/Go_Alone.wav");
		File death = new File("res/Audio/Effects/Death.wav");

		try {
			BufferedImage playImage = ImageIO.read(play);

			Image dimg = playImage.getScaledInstance(200, 80, Image.SCALE_SMOOTH);

			playButton = new JButton(new ImageIcon(dimg));
			playButton.setBorder(BorderFactory.createEmptyBorder());
			playButton.setContentAreaFilled(false);
			playButton.setBounds(98, 400, 288, 80);
			frame.add(playButton);
		}  catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedImage exitImage = ImageIO.read(exit);

			Image dimg = exitImage.getScaledInstance(200, 80, Image.SCALE_SMOOTH);

			exitButton = new JButton(new ImageIcon(dimg));
			exitButton.setBorder(BorderFactory.createEmptyBorder());
			exitButton.setContentAreaFilled(false);
			exitButton.setBounds(576, 400, 288, 80);
			frame.add(exitButton);
		}  catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedImage titleImage = ImageIO.read(title);
			Image dimg = titleImage.getScaledInstance(780, 200, Image.SCALE_SMOOTH);

			Title = new JLabel(new ImageIcon(dimg));
			Title.setBounds(100, 100, 780, 200);
			frame.add(Title);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedImage img = ImageIO.read(cr);
			Image dimg = img.getScaledInstance(800, 900, Image.SCALE_SMOOTH);

			Credits = new JLabel(new ImageIcon(dimg));
			frame.add(Credits);
			Credits.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedImage myPicture = ImageIO.read(Background);
			BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
			BackgroundImageForStartMenu.setBounds(0, 0, FrameWidth, FrameHeight);
			frame.add(BackgroundImageForStartMenu);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			AudioInputStream menuAudio = AudioSystem.getAudioInputStream(menu);
			DataLine.Info mInfo = new DataLine.Info(Clip.class, menuAudio.getFormat());
			menuMusic = (Clip) AudioSystem.getLine(mInfo);
			menuMusic.open(menuAudio);
			FloatControl menuControl = (FloatControl) menuMusic.getControl(FloatControl.Type.MASTER_GAIN);
			menuControl.setValue(-5.0f);

			AudioInputStream gameAudio = AudioSystem.getAudioInputStream(game);
			DataLine.Info gInfo = new DataLine.Info(Clip.class, gameAudio.getFormat());
			gameMusic = (Clip) AudioSystem.getLine(gInfo);
			gameMusic.open(gameAudio);
			FloatControl gameControl = (FloatControl) gameMusic.getControl(FloatControl.Type.MASTER_GAIN);
			gameControl.setValue(-5.0f);

			AudioInputStream deathAudio = AudioSystem.getAudioInputStream(death);
			DataLine.Info dInfo = new DataLine.Info(Clip.class, deathAudio.getFormat());
			deathMusic = (Clip) AudioSystem.getLine(dInfo);
			deathMusic.open(deathAudio);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playButton.setVisible(false);
				exitButton.setVisible(false);
				BackgroundImageForStartMenu.setVisible(false);
				Title.setVisible(false);

				startGame();
			}
		});

		frame.setVisible(true);
		menuMusic.setMicrosecondPosition(0);
		menuMusic.start();
	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow();

		while(true) {
			int TimeBetweenFrames = 1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames;

			//wait till next time step
			while (FrameCheck > System.currentTimeMillis()) {
			}

			if (gameState == 0) {
				gameloop();
			} else if (gameState == -1) {
				gameCanvas.setVisible(false);
				playButton.setVisible(true);
				exitButton.setVisible(true);
				BackgroundImageForStartMenu.setVisible(true);
				Title.setVisible(true);

				gameMusic.stop();
				menuMusic.loop(1000);
				menuMusic.start();
			} else if (gameState == -2) {
				deathMusic.setMicrosecondPosition(0);
				deathMusic.start();
				gameState = -1;
			} else if (gameState == 1) {
				gameCanvas.setVisible(false);
				BackgroundImageForStartMenu.setVisible(true);

				gameMusic.stop();
				menuMusic.loop(1000);
				menuMusic.setMicrosecondPosition(0);
				menuMusic.start();

				rollCredits();
				gameState = -1;
			}

			//UNIT test to see if framerate matches
			UnitTests.CheckFrameRate(System.currentTimeMillis(),FrameCheck, TargetFPS);
		}
	}

	private static void rollCredits() {
		int height = 900;
		int width = 800;

		int X = (960 - width) / 2;

		Credits.setBounds(X, 680, width, height);
		Credits.setVisible(true);

		for (float Y = 680; Y > -height; Y -= 0.5) {
			int TimeBetweenFrames = 1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames;

			//wait till next time step
			while (FrameCheck > System.currentTimeMillis()) {
			}

			Credits.setBounds(X, (int) Y, width, height);

			//UNIT test to see if framerate matches
			UnitTests.CheckFrameRate(System.currentTimeMillis(), FrameCheck, TargetFPS);
		}

		Credits.setVisible(false);
	}

	private void startGame() {
		gameworld = new Model();
		gameCanvas = new Viewer(gameworld);

		frame.add(gameCanvas);

		menuMusic.stop();
		gameMusic.loop(1000);
		gameMusic.setMicrosecondPosition(0);
		gameMusic.start();

		gameCanvas.setBounds(0, 0, FrameWidth, FrameHeight);
		gameCanvas.setBackground(new Color(255, 255, 255)); //white background  replaced by Space background but if you remove the background method this will draw a white screen
		gameCanvas.setVisible(true);
		gameCanvas.addKeyListener(Controller);    //adding the controller to the Canvas
		gameCanvas.requestFocusInWindow();   // making sure that the Canvas is in focus so keyboard input will be taking in .
		gameState = 0;
	}

	//Basic Model-View-Controller pattern
	private static void gameloop() {
		// GAMELOOP

		// controller input  will happen on its own thread
		// So no need to call it explicitly

		// model update
		gameworld.gamelogic();
		gameState = gameworld.gameState();
		// view update
		gameCanvas.updateview();

		// Both these calls could be setup as  a thread but we want to simplify the game logic for you.
		//score update
		frame.setTitle("Isolation");
	}
}

/*
 * 
 * 

Hand shake agreement 
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,=+++
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,:::::,=+++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,:++++????+??
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,:,,:,:,,,,,,,,,,,,,,,,,,,,++++++?+++++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=++?+++++++++++??????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++?+++?++?++++++++++?????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++++++++++++++????+++++++???????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,:===+=++++++++++++++++++++?+++????????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,~=~~~======++++++++++++++++++++++++++????????????????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,::::,,,,,,=~.,,,,,,,+===~~~~~~====++++++++++++++++++++++++++++???????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,~~.~??++~.,~~~~~======~=======++++++++++++++++++++++++++????????????????II
:::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,:=+++??=====~~~~~~====================+++++++++++++++++++++?????????????????III
:::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,++~~~=+=~~~~~~==~~~::::~~==+++++++==++++++++++++++++++++++++++?????????????????IIIII
::::::::::::::::::::::::::::::::::::::::::::::::,:,,,:++++==+??+=======~~~~=~::~~===++=+??++++++++++++++++++++++++?????????????????I?IIIIIII
::::::::::::::::::::::::::::::::::::::::::::::::,,:+????+==??+++++?++====~~~~~:~~~++??+=+++++++++?++++++++++??+???????????????I?IIIIIIII7I77
::::::::::::::::::::::::::::::::::::::::::::,,,,+???????++?+?+++???7?++======~~+=====??+???++++++??+?+++???????????????????IIIIIIIIIIIIIII77
:::::::::::::::::::::::::::::::::::::::,,,,,,=??????IIII7???+?+II$Z77??+++?+=+++++=~==?++?+?++?????????????III?II?IIIIIIIIIIIIIIIIIIIIIIIIII
::::::::::::::::::::::::::::::,,,,,,~=======++++???III7$???+++++Z77ZDZI?????I?777I+~~+=7+?II??????????????IIIIIIIIIIIIIIIIIIIIII??=:,,,,,,,,
::::::::,:,:,,,,,,,:::~==+=++++++++++++=+=+++++++???I7$7I?+~~~I$I??++??I78DDDO$7?++==~I+7I7IIIIIIIIIIIIIIIIII777I?=:,,,,,,,,,,,,,,,,,,,,,,,,
++=++=++++++++++++++?+????+??????????+===+++++????I7$$ZZ$I+=~$7I???++++++===~~==7??++==7II?~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+++++++++++++?+++?++????????????IIIII?I+??I???????I7$ZOOZ7+=~7II?+++?II?I?+++=+=~~~7?++:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+?+++++????????????????I?I??I??IIIIIIII???II7II??I77$ZO8ZZ?~~7I?+==++?O7II??+??+=====.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
?????????????III?II?????I?????IIIII???????II777IIII7$ZOO7?+~+7I?+=~~+???7NNN7II?+=+=++,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
????????????IIIIIIIIII?IIIIIIIIIIII????II?III7I7777$ZZOO7++=$77I???==+++????7ZDN87I??=~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIII?II??IIIIIIIIIIIIIIIIIIIIIIIIIII???+??II7777II7$$OZZI?+$$$$77IIII?????????++=+.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII?+++?IIIII7777$$$$$$7$$$$7IIII7I$IIIIII???I+=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII???????IIIIII77I7777$7$$$II????I??I7Z87IIII?=,,,,,,,,,,,:,,::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777777777777I7I777777777~,,,,,,,+77IIIIIIIIIII7II7$$$Z$?I????III???II?,,,,,,,,,,::,::::::::,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777$77777777777+::::::::::::::,,,,,,,=7IIIII78ZI?II78$7++D7?7O777II??:,,,:,,,::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$$$$$$$$$$$77=:,:::::::::::::::::::::::::::,,7II$,,8ZZI++$8ZZ?+=ZI==IIII,+7:,,,,:::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$I~::::::::::::::::::::::::::::::::::::::::::II+,,,OOO7?$DOZII$I$I7=77?,,,,,,:::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::+ZZ?,$ZZ$77ZZ$?,,,,,::::::::::::::::::::::::::,::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::I$:::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
                                                                                                                             GlassGiant.com
 * 
 * 
 */
