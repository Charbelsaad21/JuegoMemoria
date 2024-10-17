import javax.swing.*;
import java.awt.*;

public class PantallaJugadores extends JFrame {

    public PantallaJugadores(String nombreJugador) {
        setTitle("Jugadores Listos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4)); // 4 columnas

        // Añadir los jugadores (incluyendo el que ingresó su nombre)
        agregarJugador(panel, nombreJugador, Color.RED);
        agregarJugador(panel, "Luis", Color.CYAN);
        agregarJugador(panel, "Pedro", Color.ORANGE);
        agregarJugador(panel, "Jose", Color.GREEN);

        // Botón Listo
        JButton botonListo = new JButton("Listo");
        add(botonListo, BorderLayout.SOUTH);

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
