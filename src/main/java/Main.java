import menus.GameMenu;

public class Main {
    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        boolean keepPlaying = gameMenu.startGame();

        while (keepPlaying) {
            keepPlaying = gameMenu.startGame();
        }
    }
}
