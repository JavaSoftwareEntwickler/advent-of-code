package main.java.com.adventofcode.snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeMovement extends JPanel implements ActionListener, KeyListener {

    private int x = 50; // Posizione iniziale della "x"
    private int y = 50;
    private final int STEP = 10; // Incremento di movimento
    private final int WIDTH = 400; // Larghezza della finestra
    private final int HEIGHT = 400; // Altezza della finestra

    private int dx = 0; // Direzione orizzontale
    private int dy = 0; // Direzione verticale

    private int score = 0; // Punteggio del gioco
    private Cibo cibo; // Oggetto che rappresenta il cibo

    public SnakeMovement() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);

        cibo = new Cibo(WIDTH, HEIGHT); // Inizializza il cibo

        Timer timer = new Timer(100, this); // Timer per aggiornare il movimento
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        checkCollisionWithFood(); // Verifica la collisione con il cibo
        repaint();
    }

    private void move() {
        x += dx;
        y += dy;

        // Controlla i confini della finestra
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > WIDTH - 10) x = WIDTH - 10; // Evita che esca dalla finestra
        if (y > HEIGHT - 10) y = HEIGHT - 10;
    }

    private void checkCollisionWithFood() {
        // Verifica se la testa del serpente mangia il cibo
        if (x == cibo.getX() && y == cibo.getY()) {
            score++; // Incrementa il punteggio
            cibo.reset(WIDTH, HEIGHT); // Rigenera il cibo in una nuova posizione
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        //g.drawString("X", x, y); // Disegna la "x"
        g.fillRect(x, y, 10, 10); // Usa un quadrato per la testa del serpente
        // Disegna il cibo
        cibo.draw(g); // Disegna il cibo


        // Mostra il punteggio
        g.setColor(Color.BLACK);
        g.drawString("Punteggio: " + score, 10, 20); // Mostra il punteggio in alto a sinistra
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dx = 0;
            dy = -STEP;
        } else if (key == KeyEvent.VK_DOWN) {
            dx = 0;
            dy = STEP;
        } else if (key == KeyEvent.VK_LEFT) {
            dx = -STEP;
            dy = 0;
        } else if (key == KeyEvent.VK_RIGHT) {
            dx = STEP;
            dy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non serve per questo esempio
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non serve per questo esempio
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Movement");
        SnakeMovement panel = new SnakeMovement();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
