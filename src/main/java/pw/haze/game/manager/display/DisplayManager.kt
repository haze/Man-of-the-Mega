package pw.haze.game.manager.display

import org.jsfml.graphics.Color
import org.jsfml.graphics.RectangleShape
import org.jsfml.system.Clock
import org.jsfml.system.Vector2f
import pw.haze.game.displayManager
import pw.haze.game.manager.display.screens.MainMenuScreen
import pw.haze.game.window

/**
 * Created by Astigmatism on 11/22/2015.
 */
class DisplayManager {

    var curScreen: Screen

    init {
        curScreen = MainMenuScreen()
    }

    fun init(){
        curScreen.init()
    }

}