package pw.haze.game.manager.display.component

import org.jsfml.graphics.Color
import org.jsfml.graphics.RectangleShape
import org.jsfml.graphics.Text
import org.jsfml.system.Vector2f
import org.jsfml.system.Vector2i
import org.jsfml.window.event.MouseButtonEvent
import pw.haze.game.window

/**
 * Created by Astigmatism on 11/23/2015.
 */
class Button(val text: Text, val id: Int, val pos: Vector2f, val size: Vector2f = detemine(text), val box: Boolean = false): Component {


    var bbox: RectangleShape

    init {
        text.position = pos
        bbox = RectangleShape(size)
        bbox.fillColor = Color.CYAN
        bbox.outlineColor = Color.BLACK
        bbox.position = Vector2f(pos.x - 2, pos.y)
    }

    override fun draw(x: Int, y: Int)
    {
        if(box)
            window.draw(bbox)
        window.draw(text)
    }

    fun isHovering(x: Int, y: Int): Boolean {
        return x > pos.x && x < pos.x + size.x && y > pos.y && y < pos.y + size.y
    }

    fun isHovering(vec: Vector2i): Boolean = isHovering(vec.x, vec.y)

}

//used to determine size without setting a width and height
fun detemine(txt: Text): Vector2f = Vector2f(txt.localBounds.left + txt.localBounds.width + 3F, txt.localBounds.height + txt.localBounds.top + 2F)
