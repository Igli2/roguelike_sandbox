package com.roguelike_sandbox.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.roguelike_sandbox.game.GameClass;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = true;
        config.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        config.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        config.title = "Roguelike Sandbox";
        config.forceExit = true;

        new LwjglApplication(new GameClass(), config);
    }
}
