package pw.haze.game

import org.jsfml.graphics.Color
import org.jsfml.graphics.RectangleShape
import org.jsfml.graphics.RenderWindow
import org.jsfml.system.Clock
import org.jsfml.system.Vector2f
import org.jsfml.window.Mouse
import org.jsfml.window.VideoMode
import org.jsfml.window.event.Event
import pw.haze.game.manager.display.DisplayManager
import pw.haze.game.manager.display.Screen
import pw.haze.game.manager.display.font.FontManager
import pw.haze.game.manager.display.texture.TextureManager

/**
 * Created by Astigmatism on 11/22/2015.
 */

val window: RenderWindow = RenderWindow()
val fontManager: FontManager = FontManager()
val textureManager: TextureManager = TextureManager()
val displayManager: DisplayManager = DisplayManager()
fun main(args: Array<String>) {
    window.create(VideoMode(640, 480), "Man of the Mega")
    fontManager.init()
    displayManager.init()
    window.setFramerateLimit(60)
    window.display()

    main_game@ while (window.isOpen) {
        window.clear(Color.MAGENTA)
        displayManager.curScreen.draw(Mouse.getPosition(window).x, Mouse.getPosition(window).y)
        window.display()
        event_loop@ for (event in window.pollEvents()) {
            if (event.type == Event.Type.CLOSED) {
                endGame()
            }
            displayManager.curScreen.pollEvent(event)
        }
    }
}


fun endGame(){
    window.close()
}