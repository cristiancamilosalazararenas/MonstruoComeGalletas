// Este es el paquete donde se ubica la clase Cookie.
package autonoma.monstruocomegalletas.elements;

// Importamos las clases necesarias para los gráficos
import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que representa una galleta que puede ser comida por el monstruo. Cada
 * galleta tiene una posición, tamaño y estado (comida o no comida).
 *
 * @author Cristian Camilo Salazar Arenas
 * @version 1.0
 * @since 2025.05.04
 */
public class Cookie {

    /**
     * Coordenada x (horizontal) donde se ubica la galleta.
     */
    private int x;

    /**
     * Coordenada y (vertical) donde se ubica la galleta.
     */
    private int y;

    /**
     * Diámetro de la galleta en píxeles.
     */
    private final int size;

    /**
     * Indica si la galleta ha sido comida (true) o no (false).
     */
    private boolean eaten;

    /**
     * Constructor que crea una nueva galleta en la posición especificada.
     *
     * @param x La coordenada horizontal inicial de la galleta
     * @param y La coordenada vertical inicial de la galleta
     * @param size El tamaño en píxeles de la galleta
     * @since 2025.05.04
     */
    public Cookie(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eaten = false;
    }

    /**
     * Obtiene la coordenada x actual de la galleta.
     *
     * @return La posición horizontal de la galleta
     * @since 2025.05.04
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada y actual de la galleta.
     *
     * @return La posición vertical de la galleta
     * @since 2025.05.04
     */
    public int getY() {
        return y;
    }

    /**
     * Obtiene el tamaño de la galleta.
     *
     * @return El diámetro en píxeles de la galleta
     * @since 2025.05.04
     */
    public int getSize() {
        return size;
    }

    /**
     * Verifica si la galleta ha sido comida.
     *
     * @return true si la galleta fue comida, false si está disponible
     * @since 2025.05.04
     */
    public boolean isEaten() {
        return eaten;
    }

    /**
     * Marca la galleta como comida, haciéndola desaparecer del juego.
     *
     * @since 2025.05.04
     */
    public void eat() {
        this.eaten = true;
    }

    /**
     * Dibuja la galleta en el contexto gráfico especificado. Solo se dibuja si
     * la galleta no ha sido comida.
     *
     * @param g El objeto Graphics donde se dibujará la galleta
     * @since 2025.05.04
     */
    public void dibujar(Graphics g) {
        if (!eaten) {
            g.setColor(new Color(139, 69, 19)); // Color café
            g.fillOval(x - size / 2, y - size / 2, size, size);
        }
    }
}
