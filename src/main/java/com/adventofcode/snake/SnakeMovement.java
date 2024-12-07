package main.java.com.adventofcode.snake;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
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
    private LinkedList<Point> body; // Corpo del serpente

    private boolean gameOver = false; // Flag per determinare se il gioco è finito

    public SnakeMovement() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);

        cibo = new Cibo(WIDTH, HEIGHT); // Inizializza il cibo

        body = new LinkedList<>(); // Corpo iniziale del serpente
        body.add(new Point(x, y)); // Aggiungi il primo segmento (la testa)

        Timer timer = new Timer(100, this); // Timer per aggiornare il movimento
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            move();
            checkCollisionWithFood(); // Verifica la collisione con il cibo
            checkCollisionWithWalls(); // Verifica la collisione con i bordi
            repaint();
        }
    }

    private void move() {
        // Aggiungi un nuovo segmento all'inizio della lista, che rappresenta la testa del serpente
        body.addFirst(new Point(x + dx, y + dy));
        // Se il serpente non ha mangiato il cibo, rimuovi l'ultimo segmento
        if (body.size() > score + 1) {
            body.removeLast();
        }

        // Imposta la nuova posizione della testa
        x = body.getFirst().x;
        y = body.getFirst().y;

        //x += dx;
        //y += dy;

//        // Controlla i confini della finestra
//        if (x < 0) x = 0;
//        if (y < 0) y = 0;
//
//        if (x > WIDTH - 10) x = WIDTH - 10; // Evita che esca dalla finestra
//        if (y > HEIGHT - 10) y = HEIGHT - 10;
    }

    private void checkCollisionWithFood() {
        // Verifica se la testa del serpente mangia il cibo
        if (x == cibo.getX() && y == cibo.getY()) {
            score++; // Incrementa il punteggio
            cibo.reset(WIDTH, HEIGHT); // Rigenera il cibo in una nuova posizione
        }
    }

    private void checkCollisionWithWalls() {
        // Verifica la collisione con i bordi della finestra
        if (x < 0 || y < 0 || x  >= WIDTH || y  >= HEIGHT) {
            gameOver = true; // Attiva il flag di game over
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            // Mostra il messaggio "Game Over" se il gioco è finito
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", WIDTH / 4, HEIGHT / 2);
            g.drawString("Punteggio: " + score, WIDTH / 3, HEIGHT / 2 + 40);
            return;
        }

        // Disegna il corpo del serpente
        g.setColor(Color.RED);
        for (Point p : body) {
            g.fillRect(p.x, p.y, 10, 10); // Disegna ogni segmento del corpo
        }
        //Commento perchè ho disegnato il corpo del serpente
        //g.setColor(Color.RED);
        //g.drawString("X", x, y); // Disegna la "x"
        //g.fillRect(x, y, 10, 10); // Usa un quadrato per la testa del serpente

        // Disegna il cibo
        cibo.draw(g); // Disegna il cibo


        // Mostra il punteggio
        g.setColor(Color.BLACK);
        g.drawString("Punteggio: " + score, 10, 20); // Mostra il punteggio in alto a sinistra
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) return; // Non permette di muoversi se il gioco è finito
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
