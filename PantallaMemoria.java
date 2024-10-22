import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.Random;
import java.util.ArrayList;

class PantallaMemoria extends JFrame {

    private BufferedImage backgroundImage;
    private BufferedImage nuevaImagenFondo; // Variable de instancia para la nueva imagen de fondo
    private JLabel temporizadorLabel;
    private int tiempoRestante; // Tiempo restante en segundos

    public PantallaMemoria() {
        setTitle("Juego de Memoria");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        tiempoRestante = 2; // 2 segundos para la cuenta regresiva

        try {
            // Cargar la imagen de fondo desde la ruta correcta y oscurecerla
            backgroundImage = ImageIO.read(getClass().getResource("/Images/Fondo.jpg"));
            float scaleFactor = 0.5f; // 1.0f es el brillo original, menos de 1.0f lo oscurece
            RescaleOp op = new RescaleOp(scaleFactor, 0, null);
            backgroundImage = op.filter(backgroundImage, null); // Aplica el filtro de oscurecimiento
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Panel principal con fondo oscurecido
        JPanel panelConFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen de fondo oscurecida
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConFondo.setLayout(new GridBagLayout()); // Usamos GridBagLayout para centrar los cuadros

        // Crear un GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        JLabel titulo = new JLabel("¡Apréndete esto!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0); // Relleno superior
        panelConFondo.add(titulo, gbc);

        // Crear un panel para los cuadros de imágenes más pequeños
        JPanel panelCuadros = new JPanel();
        panelCuadros.setLayout(new GridLayout(1, 5, 10, 10)); // 1 fila, 5 columnas, con espaciado entre cuadros
        panelCuadros.setOpaque(false);

        // Ruta de las imágenes
        String rutaImagenes = "Images/";
        String[] nombresImagenes = {
            "imagen_ODS1.png", "imagen_ODS2.png", "imagen_ODS3.png", "imagen_ODS4.png",
            "imagen_ODS5.png", "imagen_ODS6.png", "imagen_ODS7.png", "imagen_ODS8.png",
            "imagen_ODS9.png", "imagen_ODS10.png", "imagen_ODS11.png", "imagen_ODS12.png",
            "imagen_ODS13.png", "imagen_ODS14.png", "imagen_ODS15.png", "imagen_ODS16.png", "imagen_ODS17.png"
        };

        ArrayList<ImageIcon> imagenes = new ArrayList<>();

        // Cargar todas las imágenes
        for (String nombreImagen : nombresImagenes) {
            imagenes.add(new ImageIcon(rutaImagenes + nombreImagen));
        }

        Random random = new Random();
        // Añadir cuadros más pequeños
        for (int i = 1; i <= 5; i++) {
            JPanel cuadro = new JPanel() {
                // Sobrescribir paintComponent para dibujar la imagen de fondo
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Seleccionar una imagen aleatoria
                    ImageIcon imagenFondo = imagenes.get(random.nextInt(imagenes.size()));
                    // Dibujar la imagen
                    g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            
            cuadro.setPreferredSize(new Dimension(100, 100)); 
            cuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            panelCuadros.add(cuadro);
        }

        // Configurar restricciones para centrar el panel de cuadros
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panelConFondo.add(panelCuadros, gbc);

        // Crear y añadir el temporizador
        temporizadorLabel = new JLabel("Tiempo: 2", SwingConstants.CENTER);
        temporizadorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        temporizadorLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // Espaciado extra
        panelConFondo.add(temporizadorLabel, gbc);

        // Iniciar el temporizador
        iniciarTemporizador();

        // Añadir el panel con fondo a la ventana
        add(panelConFondo);
    }

    // Método para iniciar el temporizador
    private void iniciarTemporizador() {
        Timer timer = new Timer(1000, new ActionListener() { // Se ejecuta cada 1000 ms (1 segundo)
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    temporizadorLabel.setText("Tiempo: " + tiempoRestante);
                } else {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador cuando llegue a 0
                    cambiarPantalla(); // Llamar al método para cambiar de pantalla
                }
            }
        });
        timer.start(); // Iniciar el temporizador
    }

    // Método para cambiar de pantalla
    private void cambiarPantalla() {
        // Cierra la ventana actual
        this.dispose();

        // Crear la nueva ventana (puedes personalizar la nueva pantalla aquí)
        JFrame nuevaPantalla = new JFrame("Nueva Pantalla");
        nuevaPantalla.setSize(800, 600);
        nuevaPantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaPantalla.setLocationRelativeTo(null);

        // Cargar y oscurecer la imagen de fondo para la nueva pantalla
        try {
            nuevaImagenFondo = ImageIO.read(getClass().getResource("/Images/Fondo.jpg"));
            float scaleFactor = 0.5f; // 1.0f es el brillo original, menos de 1.0f lo oscurece
            RescaleOp op = new RescaleOp(scaleFactor, 0, null);
            nuevaImagenFondo = op.filter(nuevaImagenFondo, null); // Aplica el filtro de oscurecimiento
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear el panel con fondo para la nueva pantalla
        JPanel panelConFondoNueva = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (nuevaImagenFondo != null) {
                    g.drawImage(nuevaImagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelConFondoNueva.setLayout(new BorderLayout());

        // Mensaje en la nueva pantalla
        JLabel mensaje = new JLabel("¡Has cambiado de pantalla!", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.BOLD, 36));
        mensaje.setForeground(Color.WHITE);
        panelConFondoNueva.add(mensaje, BorderLayout.CENTER);

        nuevaPantalla.add(panelConFondoNueva);
        nuevaPantalla.setVisible(true); // Mostrar la nueva ventana
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaMemoria pantallaMemoria = new PantallaMemoria();
            pantallaMemoria.setVisible(true);
        });
    }
}
