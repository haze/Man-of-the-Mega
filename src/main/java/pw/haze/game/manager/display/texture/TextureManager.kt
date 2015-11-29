package pw.haze.game.manager.display.texture

import org.jsfml.graphics.Font
import org.jsfml.graphics.Texture
import java.nio.file.Paths

/**
 * Created by Astigmatism on 11/28/2015.
 */
class TextureManager {
    public fun loadTex(name: String, end: String): Texture{
        var ctexture = Texture()
        try{
            ctexture.loadFromFile(Paths.get("src/main/resources/sprites/$name.$end"))
        }catch(e: Exception){
            e.printStackTrace();
        }
        return ctexture
    }
}