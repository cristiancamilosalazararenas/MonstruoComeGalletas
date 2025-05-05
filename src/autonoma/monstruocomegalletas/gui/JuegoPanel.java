// Este es el paquete donde se ubica la clase JuegoPanel.
package autonoma.monstruocomegalletas.gui;

// Importamos las clases necesarias
import autonoma.monstruocomegalletas.elements.Cookie;
import autonoma.monstruocomegalletas.elements.Monstruo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ventana principal del juego Monstruo Come Galletas. Maneja la interfaz
 * gráfica y la lógica principal del juego.
 *
 * @author Cristian Camilo Salazar Arenas
 * @version 1.0
 * @since 2025.05.04
 */
public class JuegoPanel extends JFrame {

    /**
     * Lista que contiene todas las galletas del juego.
     */
    private final ArrayList<Cookie> galletas = new ArrayList<>();

    /**
     * Instancia del monstruo que come las galletas.
     */
    private final Monstruo monstruo;

    /**
     * Generador de números aleatorios para posicionar galletas.
     */
    private final Random rand = new Random();

    /**
     * Constructor que inicializa la ventana del juego. Configura los elementos
     * gráficos y el hilo de animación.
     *
     * @since 2025.05.04
     */
    public JuegoPanel() {
        // ========== CONFIGURACIÓN BÁSICA DE VENTANA ==========
        setTitle("Monstruo Come Galletas");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra aplicación al salir

        // ========== INICIALIZACIÓN DE ELEMENTOS DEL JUEGO ==========
        monstruo = new Monstruo(400, 300, 40, 3); // Monstruo en el centro
        agregarGalletaAleatoria(); // Añade primera galleta

        // ========== CONFIGURACIÓN DE EVENTOS DE TECLADO ==========
        addKeyListener(new KeyAdapter() {
            /**
             * Maneja el evento de tecla presionada. Agrega nueva galleta cuando
             * se presiona la tecla Q.
             *
             * @param e El evento de tecla generado
             * @since 2025.05.04
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    agregarGalletaAleatoria();
                }
            }
        });

        // ========== HILO DE ANIMACIÓN PRINCIPAL ==========
        new Thread(() -> {
            while (true) {
                // Encuentra y mueve hacia la galleta más cercana
                Cookie objetivo = monstruo.encontrarGalletaCercana(galletas);
                monstruo.moverHacia(objetivo);

                repaint(); // Redibuja la escena

                try {
                    Thread.sleep(30); // Controla la velocidad de actualización (~33 FPS)
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    // ========== MÉTODOS AUXILIARES ==========
    /**
     * Agrega una nueva galleta en una posición aleatoria dentro del área
     * visible.
     *
     * @since 2025.05.04
     */
    private void agregarGalletaAleatoria() {
        galletas.add(new Cookie(
                rand.nextInt(getWidth() - 30) + 15, // Posición X aleatoria
                rand.nextInt(getHeight() - 30) + 15, // Posición Y aleatoria
                20 // Tamaño fijo de galleta
        ));
    }

    // ========== MÉTODOS DE DIBUJO ==========
    /**
     * Dibuja todos los elementos del juego en la ventana.
     *
     * @param g El contexto gráfico para dibujar
     * @since 2025.05.04
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Dibuja fondo negro
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibuja todas las galletas no comidas
        for (Cookie c : galletas) {
            c.dibujar(g);
        }

        // Dibuja el monstruo
        monstruo.dibujar(g);
    }
    
    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     * @since 2025.05.04
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JuegoPanel juego = new JuegoPanel();
            juego.setVisible(true); // Muestra la ventana del juego
        });
    }
}