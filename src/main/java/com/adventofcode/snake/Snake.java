package main.java.com.adventofcode.snake;

import javazoom.jl.player.Player;
import main.java.com.adventofcode.utils.FileProperties;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;

import javax.swing.*;

public class Snake extends JPanel implements ActionListener, KeyListener {

    private int x = 50; // Posizione iniziale della "x"
    private int y = 50;
    private int dx = 0; // Direzione orizzontale
    private int dy = 0; // Direzione verticale
    private final int STEP = 10; // Incremento di movimento
    private final int WIDTH = 160; // Larghezza della finestra
    private final int HEIGHT = 160; // Altezza della finestra

    private int score = 0; // Punteggio del gioco
    private boolean gameOver = false; // Flag per determinare se il gioco è finito
    private JButton restartButton; // Pulsante per rigiocare
    private LinkedList<Point> body; // Corpo del serpente

    private Cibo cibo; // Oggetto che rappresenta il cibo
    private Classifica classifica; // Oggetto Classifica
    private String giocatoreNome; // Nome del giocatore
    private FileProperties fileProperties;

    public Snake() {
        init();
    }
    void init(){
        fileProperties = new FileProperties();
        // Chiedi il nome del giocatore
        if(giocatoreNome == null || giocatoreNome.isBlank()){
            giocatoreNome = JOptionPane.showInputDialog("Inserisci il tuo nome:");
            if (giocatoreNome == null || giocatoreNome.trim().isEmpty()) {
                giocatoreNome = "Anonimo";
            }
            classifica = new Classifica();
        }

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);

        cibo = new Cibo(WIDTH, HEIGHT); // Inizializza il cibo

        body = new LinkedList<>(); // Corpo iniziale del serpente
        body.add(new Point(x, y)); // Aggiungi il primo segmento (la testa)

        restartButton = new JButton("Rigioca");
        restartButton.setBounds(WIDTH / 4, HEIGHT / 2, 100, 30);
        restartButton.setVisible(false); // Impostiamo invisibile inizialmente
        this.add(restartButton);
        restartButton.addActionListener(e -> restartGame());

        Timer timer = new Timer(100, this); // Timer per aggiornare il movimento
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            move();
            checkCollisionWithFood(); // Verifica la collisione con il cibo
            checkCollisionWithWalls(); // Verifica la collisione con i bordi
            checkCollisionWithSelf(); // Verifica la collisione con il corpo
            repaint();
        }
        else{
            if (!restartButton.isVisible()) {
                restartButton.setVisible(true); // Rendi visibile il pulsante per il riavvio
                // Salva il punteggio del giocatore
                Giocatore giocatore = new Giocatore(giocatoreNome, score);
                classifica.aggiungiGiocatore(giocatore);
                // Mostra la classifica
                String classificaTesto= classifica.mostraClassifica();
                JOptionPane.showMessageDialog(this, classificaTesto, "Classifica", JOptionPane.INFORMATION_MESSAGE);

            }
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
            // Riproduco il suono
            playSound(fileProperties.getPath("audio")+"/ciboMangiato.mp3");

        }
        for (int i = 1; i < body.size(); i++) {
            Point p = body.get(i);
            // Incrementa il punteggio se il corpo del serpente tocca il cibo
            if (cibo.getX() == p.x && cibo.getY() == p.y) {
                score++;
                cibo.reset(WIDTH, HEIGHT); // Rigenera il cibo in una nuova posizione
                // Riproduco il suono
                playSound(fileProperties.getPath("audio")+"/ciboMangiato.mp3");
                break;
            }
        }
    }

    private void playSound(String filePath) {
        new Thread(()-> {
            try {
                File mp3File = new File(filePath);
                if (mp3File.exists()) {
                    // Crea il lettore MP3
                    Player player = new Player(new FileInputStream(mp3File));
                    player.play(); // Riproduci il suon}}te
                }
            } catch (Exception e) {
                e.printStackTrace(); // Gestione degli errori
            }
        }).start();
    }

    private void checkCollisionWithWalls() {
        // Verifica la collisione con i bordi della finestra
        if (x < 0 || y < 0 || x  >= WIDTH || y  >= HEIGHT) {
            gameOver = true;
            playSound(fileProperties.getPath("audio")+"/gameOver.mp3");
        }
    }

    private void checkCollisionWithSelf() {
        // Verifica se la testa del serpente tocca uno dei segmenti del corpo
        for (int i = 1; i < body.size(); i++) {
            Point p = body.get(i);
            if (x == p.x && y == p.y) {
                gameOver = true; // Se il serpente si tocca, game over
                playSound(fileProperties.getPath("audio")+"/gameOver.mp3");
                break;
            }
        }
    }

    private void restartGame() {
        // Chiudi la finestra corrente
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Ottieni la finestra che contiene il pannello
        if (topFrame != null) {
            topFrame.dispose(); // Chiudi la finestra
        }

        // Avvia una nuova finestra di gioco
        startGame();  // Avvia una nuova finestra di gioco
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            // Mostra il messaggio "Game Over" se il gioco è finito
            g.setColor(Color.BLACK);
            // Imposta il font in modo responsivo
            int fontSize = Math.max(WIDTH / 15, 20);
            g.setFont(new Font("Arial", Font.BOLD, fontSize));

            g.drawString("Game Over", WIDTH / 8, HEIGHT / 8);
            g.drawString("Punteggio: " + score, WIDTH / 5 - 20, HEIGHT / 5 );
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
    public void startGame() {
        JFrame frame = new JFrame("Snake Movement");
        Snake panel = new Snake();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        new Snake().startGame();
    }
}
