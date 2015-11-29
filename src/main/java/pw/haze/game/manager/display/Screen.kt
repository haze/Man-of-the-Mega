package pw.haze.game.manager.display

import org.jsfml.window.Window
import org.jsfml.window.event.Event
import pw.haze.game.manager.display.component.Button

/**
 * Created by Astigmatism on 11/22/2015.
 */
interface Screen {
    var drew: Boolean
    fun draw(x: Int, y: Int)
    fun pollEvent(event: Event)
    fun init()

}