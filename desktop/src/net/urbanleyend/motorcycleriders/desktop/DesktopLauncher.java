package net.urbanleyend.motorcycleriders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.urbanleyend.motorcycleriders.MotorcycleRiders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new MotorcycleRiders(), config);
	}
}
