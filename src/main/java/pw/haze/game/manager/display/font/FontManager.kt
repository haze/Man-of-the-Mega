package pw.haze.game.manager.display.font

import org.jsfml.graphics.Font
import org.jsfml.graphics.Text
import org.jsfml.system.Vector2f
import org.jsfml.system.Vector2i
import java.nio.file.Paths

/**
 * Created by Astigmatism on 11/23/2015.
 */
class FontManager {

    var megaman: Font = Font()

    fun init(){
        megaman = loadFont("megaman_2")
    }

    public fun loadFont(name: String): Font {
        var cfont = Font()
        try{
           cfont.loadFromFile(Paths.get("src/main/resources/fonts/$name.ttf"))
        }catch(e: Exception){
            e.printStackTrace();
        }
        return cfont
    }

    public fun createText(str: String, font: Font, size: Int, pos: Vector2f = Vector2f(0F, 0F)): Text {
        var txt = Text(str, font, size)
        txt.position = pos
        return txt
    }

}