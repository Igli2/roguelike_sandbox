package com.roguelike_sandbox.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.roguelike_sandbox.game.RoguelikeSandbox;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// config.fullscreen = true;
		config.width = 800;
		config.height = 600;
		config.title = "Roguelike Sandbox";
		config.forceExit = true;

		new LwjglApplication(new RoguelikeSandbox(), config);
		System.out.println("Hello World!");
	}
}
