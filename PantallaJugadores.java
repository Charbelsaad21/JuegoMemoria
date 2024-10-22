import javax.imageio.ImageIO;
import javax.swing.*;
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
        setSize(600, 400);
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

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));

        // Añadir los jugadores (incluyendo el que ingresó su nombre)
        agregarJugador(panel, nombreJugador, Color.RED);
        agregarJugador(panel, "Luis", Color.CYAN);
        agregarJugador(panel, "Pedro", Color.ORANGE);
        agregarJugador(panel, "Jose", Color.GREEN);

        // Botón Listo
        JButton botonListo = new JButton("Listo");
        add(botonListo, BorderLayout.SOUTH);

        // Acción del botón "Listo" para abrir la próxima pantalla
        botonListo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ocultar la ventana actual
                PantallaJugadores.this.dispose();

                // Crear la nueva pantalla y mostrarla
                SwingUtilities.invokeLater(() -> {
                    PantallaMemoria pantallaMemoria = new PantallaMemoria();
                    pantallaMemoria.setVisible(true);
                    System.out.println("Pantalla de Memoria Abierta"); // Para verificar si se abre correctamente
                });
            }
        });

        // Establecer el fondo
        panel.setBackground(new Color(34, 139, 34)); // Verde como el fondo de la imagen

        add(panel, BorderLayout.CENTER);
    }

    private void agregarJugador(JPanel panel, String nombre, Color color) {
        JPanel jugadorPanel = new JPanel();
        jugadorPanel.setLayout(new BorderLayout());
        jugadorPanel.setBackground(color);
        

        JLabel nombreJugador = new JLabel(nombre, SwingConstants.CENTER);
        nombreJugador.setFont(new Font("Serif", Font.BOLD, 18));
        jugadorPanel.add(nombreJugador, BorderLayout.CENTER);

        JLabel estadoJugador = new JLabel("¡Listo!", SwingConstants.CENTER);
        jugadorPanel.add(estadoJugador, BorderLayout.SOUTH);

        panel.add(jugadorPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Para probar esta pantalla sola, puedes pasar un nombre de jugador
            PantallaJugadores jugadores = new PantallaJugadores("Charbel");
            jugadores.setVisible(true);
        });
    }
}

