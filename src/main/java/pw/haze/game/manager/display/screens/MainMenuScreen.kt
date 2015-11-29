package pw.haze.game.manager.display.screens
import javafx.scene.input.MouseButton
import org.jsfml.graphics.Color
import org.jsfml.graphics.Sprite
import org.jsfml.graphics.Texture
import org.jsfml.system.Vector2f
import org.jsfml.window.Keyboard
import org.jsfml.window.Mouse
import org.jsfml.window.event.Event
import org.jsfml.window.event.KeyEvent
import org.jsfml.window.event.MouseButtonEvent
import pw.haze.game.*
import pw.haze.game.manager.display.Screen
import pw.haze.game.manager.display.component.Button
import pw.haze.game.manager.display.font.FontManager
import java.util.*

/**
 * Created by Astigmatism on 11/23/2015.
 */
class MainMenuScreen(): Screen {

    val buttonList: ArrayList<Button>
    override var drew: Boolean = false
    val backgroundSprite: Sprite
    val cursorSprite: Sprite
    var selectedButtonID: Int = 0
    var selectedButton: Optional<Button>

    init {
        buttonList = arrayListOf<Button>()
        backgroundSprite = Sprite(textureManager.loadTex("mainmenu", "png"))
        backgroundSprite.setPosition(0F, 0F)
        cursorSprite = Sprite(textureManager.loadTex("cursor", "png"))
        cursorSprite.color = Color.YELLOW
        selectedButton = getButtonFromID(selectedButtonID)
    }

    override fun init(){
        val btnOne = Button(fontManager.createText("exit", fontManager.megaman, 17), 0, Vector2f(25F, window.view.size.y - 25F))
        val btnTwo = Button(fontManager.createText("test", fontManager.megaman, 17), 1, Vector2f(25F, window.view.size.y - 45F))
        val btnThree = Button(fontManager.createText("ayy lmao", fontManager.megaman, 17), 2, Vector2f(25F, window.view.size.y - 65F))
        buttonList.addAll(arrayOf<Button>(btnOne, btnTwo, btnThree))
        updateSelectedButton()
        selectedButton.get().text.color = Color.YELLOW
    }

    fun getButtonFromID(id: Int): Optional<Button> {
        for(btn in buttonList){
            if(btn.id == id) return Optional.of(btn)
        }
        return Optional.empty<Button>()
    }

    override fun draw(x: Int, y: Int) {
        drew = false
        window.draw(backgroundSprite)
        window.draw(cursorSprite)
        drew = true
        buttonList.forEach { btn -> btn.draw(x, y) }
        for(btn in buttonList){
            if(btn.isHovering(Mouse.getPosition(window))){
                selectedButtonID = btn.id
                updateSelectedButton()
            }
        }
        if(!getButtonFromID(selectedButtonID).isPresent)
            selectedButtonID = 0
        updateSelectedButton()
        cursorSprite.setPosition(selectedButton.get().pos.x - 23, selectedButton.get().pos.y)
    }

    fun click(id: Int){
        when(id){
            0 -> endGame()
            1 -> println("You selected a test button?")
            // 2 -> transition(MainMenuScreen())
            else -> {}
        }
    }

    override fun pollEvent(event: Event) {

        when(event.type){
            Event.Type.MOUSE_BUTTON_RELEASED -> buttonList.filter { btn -> btn.isHovering(Mouse.getPosition(window)) }.forEach { btn -> click(btn.id) }
            Event.Type.KEY_PRESSED -> selectionMoved(event.asKeyEvent())
        }

    }

    private fun updateSelectedButton(){
        selectedButton = getButtonFromID(selectedButtonID)
        selectedButton.get().text.color = Color.YELLOW
        for(btn in buttonList){
            if(selectedButton.get() == btn) btn.text.color = Color.YELLOW
            else btn.text.color = Color.WHITE
        }
    }

    private fun getLastButtonID(): Int {
        return buttonList[buttonList.size - 1].id
    }

    private fun selectionMoved(e: KeyEvent) {
        when(e.key){
            Keyboard.Key.DOWN -> selectedButtonID -= 1
            Keyboard.Key.UP -> selectedButtonID += 1
            Keyboard.Key.RETURN -> click(selectedButtonID)
        }

        if(selectedButtonID > getLastButtonID())
            selectedButtonID = 0
        else if(selectedButtonID < 0)
            selectedButtonID = getLastButtonID()

        updateSelectedButton()
    }
}