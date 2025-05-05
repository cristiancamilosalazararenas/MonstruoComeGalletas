// Este es el paquete donde se ubica la clase Monstruo.
package autonoma.monstruocomegalletas.elements;

// Importamos las clases necesarias
import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.awt.Graphics;

/**
 * Clase que representa al monstruo que come galletas en el juego. Controla su
 * movimiento, detección de galletas cercanas y comportamiento.
 *
 * @author Cristian Camilo Salazar Arenas
 * @version 1.0
 * @since 2025.05.04
 */
public class Monstruo {

    /**
     * Punto que representa la posición actual del monstruo (coordenadas x,y).
     */
    private Point posicion;

    /**
     * Velocidad de movimiento del monstruo en píxeles por actualización.
     */
    private final int velocidad;

    /**
     * Diámetro del monstruo en píxeles.
     */
    private final int size;

    /**
     * Constructor que crea un nuevo monstruo en la posición especificada.
     *
     * @param x La coordenada horizontal inicial del monstruo
     * @param y La coordenada vertical inicial del monstruo
     * @param size El tamaño en píxeles del monstruo
     * @param velocidad La velocidad de movimiento del monstruo
     * @since 2025.05.04
     */
    public Monstruo(int x, int y, int size, int velocidad) {
        this.posicion = new Point(x, y);
        this.size = size;
        this.velocidad = velocidad;
    }

    /**
     * Busca y devuelve la galleta no comida más cercana al monstruo.
     *
     * @param galletas Lista de todas las galletas disponibles
     * @return La galleta más cercana no comida, o null si no hay galletas
     * disponibles
     * @since 2025.05.04
     */
    public Cookie encontrarGalletaCercana(List<Cookie> galletas) {
        Cookie masCercana = null;
        double minDistancia = Double.MAX_VALUE;

        for (Cookie g : galletas) {
            if (!g.isEaten()) {
                double distancia = posicion.distance(g.getX(), g.getY());
                if (distancia < minDistancia) {
                    minDistancia = distancia;
                    masCercana = g;
                }
            }
        }
        return masCercana;
    }

    /**
     * Mueve al monstruo hacia la galleta objetivo especificada. Si el monstruo
     * llega suficientemente cerca de la galleta, la come.
     *
     * @param objetivo La galleta hacia la que debe moverse el monstruo
     * @since 2025.05.04
     */
    public void moverHacia(Cookie objetivo) {
        if (objetivo != null) {
            // Calcula dirección
            double dx = objetivo.getX() - posicion.x;
            double dy = objetivo.getY() - posicion.y;
            double distancia = Math.hypot(dx, dy);

            // Mueve si está lejos
            if (distancia > velocidad) {
                posicion.x += (dx / distancia) * velocidad;
                posicion.y += (dy / distancia) * velocidad;
            } else {
                // Si está suficientemente cerca, come la galleta
                objetivo.eat();
            }
        }
    }

    /**
     * Dibuja al monstruo en el contexto gráfico especificado.
     *
     * @param g El objeto Graphics donde se dibujará el monstruo
     * @since 2025.05.04
     */
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(posicion.x - size / 2, posicion.y - size / 2, size, size);
    }

    /**
     * Obtiene la posición actual del monstruo.
     *
     * @return Punto con las coordenadas (x,y) del monstruo
     * @since 2025.05.04
     */
    public Point getPosicion() {
        return posicion;
    }

    /**
     * Obtiene el tamaño del monstruo.
     *
     * @return El diámetro en píxeles del monstruo
     * @since 2025.05.04
     */
    public int getSize() {
        return size;
    }

    /**
     * Obtiene la velocidad del monstruo.
     *
     * @return La velocidad en píxeles por actualización
     * @since 2025.05.04
     */
    public int getVelocidad() {
        return velocidad;
    }
}