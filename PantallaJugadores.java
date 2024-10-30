import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class PantallaJugadores extends JFrame {
    private BufferedImage backgroundImage;

    public PantallaJugadores(String nombreJugador) {
        setTitle("Jugadores Listos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/Fondo.jpg"));
            float scaleFactor = 0.5f; // Ajustar el brillo del fondo (0.5f lo oscurece)
            RescaleOp op = new RescaleOp(scaleFactor, 0, null);
            backgroundImage = op.filter(backgroundImage, null); // Aplicar el efecto
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear el panel principal con la imagen de fondo
        JPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel con GridLayout
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 4));
        agregarJugador(panel1, nombreJugador, new Color(255, 0, 0, 128)); // Rojo semi-transparente
        agregarJugador(panel1, "Luis", new Color(0, 255, 255, 128)); // Cian semi-transparente
        agregarJugador(panel1, "Pedro", new Color(255, 165, 0, 128)); // Naranja semi-transparente
        agregarJugador(panel1, "Jose", new Color(0, 255, 0, 128));

        // Añadir márgenes al panel con GridLayout
        panel1.setBorder(new EmptyBorder(40, 60, 40, 60)); // Márgenes: arriba, izquierda, abajo, derecha

        // Añadir el panel con GridLayout centrado dentro del mainPanel
        mainPanel.add(panel1, BorderLayout.CENTER);
        mainPanel.setOpaque(false);

        // Botón Listo
        JButton botonListo = new JButton("Listo");
        mainPanel.add(botonListo, BorderLayout.SOUTH);
        panel1.setOpaque(false);

        // Acción del botón "Listo" para abrir la próxima pantalla
        botonListo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaJugadores.this.dispose();
                SwingUtilities.invokeLater(() -> {
                    PantallaMemoria pantallaMemoria = new PantallaMemoria();
                    pantallaMemoria.setVisible(true);
                    System.out.println("Pantalla de Memoria Abierta"); // Para verificar si se abre correctamente
                });
            }
        });

        // Añadir el panel principal al frame
        add(mainPanel, BorderLayout.CENTER);
    }

    private void agregarJugador(JPanel panel, String nombre, Color color) {
        JPanel jugadorPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jugadorPanel.setOpaque(false);
        jugadorPanel.setLayout(new BorderLayout());
        jugadorPanel.setBackground(color);
        JLabel nombreJugador = new JLabel(nombre, SwingConstants.CENTER);
        nombreJugador.setFont(new Font("Serif", Font.BOLD, 18));
        jugadorPanel.add(nombreJugador, BorderLayout.CENTER);
        JLabel estadoJugador = new JLabel("¡Listo!", SwingConstants.CENTER);
        jugadorPanel.add(estadoJugador, BorderLayout.SOUTH);
        panel.add(jugadorPanel);
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaJugadores jugadores = new PantallaJugadores("Charbel");
            jugadores.setVisible(true);
        });
    }
}


