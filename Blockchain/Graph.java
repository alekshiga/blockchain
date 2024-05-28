package Blockchain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Graph extends JPanel {
    
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private static CoinPrice coinPrice = new CoinPrice();

    public Graph() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.CYAN);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("Current price: " +  String.format("%.2f", coinPrice.getCurrentPrice()), 500, 15);
    } 

    public static void main(String[] args) throws InterruptedException {
        
        JFrame frame = new JFrame("Marketplace");
        frame.setSize(1280, 780);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(250, 100);
        frame.add(new Graph());
        frame.setVisible(true);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
                coinPrice.simulatePriceChange();
            }
        });
        timer.start();
    }

}