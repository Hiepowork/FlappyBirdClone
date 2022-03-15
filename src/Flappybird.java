import javax.swing.JFrame;

public class Flappybird{
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gameplay gameplay = new Gameplay();
		frame.add(gameplay);
		frame.setVisible(true);
		frame.setBounds(0,0,500,700);
		frame.setResizable(false);
	}
}

