import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Window extends JPanel {
    private void doDrawing(Graphics g) throws IOException {
        File file = new File("OUTPOINTS.txt");
        System.out.println(file.getAbsolutePath());
        InputStream is = new FileInputStream(file);
        BufferedReader sr = new BufferedReader(new InputStreamReader(is));

        BufferedImage bImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bImg.getGraphics();

        for (String line = sr.readLine(); line != null; line = sr.readLine()) {
            int x = new Double((line.split(" ")[0])).intValue();
            int y = new Double((line.split(" ")[1])).intValue();
            g2d.drawLine(x + 200, y + 200, x + 200, y + 200);
        }

        try {
            if (ImageIO.write(bImg, "PNG", new File("./image.png")))  {
                System.out.println("Image enregistrÃ©e !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            doDrawing(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Starter extends JFrame {

    public Starter() {
        initUI();
        setVisible(true);
    }

    private void initUI() {
        add(new Window());

        setTitle("Lines");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Starter();
            }
        });
    }
}