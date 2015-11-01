package net.urbanleyend.motorcycleriders;

import com.badlogic.gdx.Game;
import net.urbanleyend.helpers.AssetLoader;
import net.urbanleyend.screens.SplashScreen;

public class MotorcycleRiders extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
