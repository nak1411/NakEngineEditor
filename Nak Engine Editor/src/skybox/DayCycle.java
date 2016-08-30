package skybox;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Light;
import renderEngine.DisplayManager;

public class DayCycle {

	private long counter = 0;
	private float time = 0;
	private Vector3f fogColor = new Vector3f(0, 0, 0);
	private Vector3f skyColor = new Vector3f(0, 0, 0);

	public void updateCycle(List<Light> lights) {

		time += DisplayManager.getFrameTimeSeconds() * 1000;
		time %= 24000;

		if (time >= 0 && time < 6000) {
			lights.get(0).setPosition(new Vector3f(0, counter, -1000));
			lights.get(0).setColor(new Vector3f(counter * 0.002f, counter * 0.002f, counter * 0.002f));
			setFogColor(new Vector3f(0, 0, 0));
			setSkyColor(new Vector3f(0, 0, 0));
		} else if (time >= 6000 && time < 12000) {
			counter++;
			lights.get(0).setPosition(new Vector3f(0, counter, -1000));
			lights.get(0).setColor(new Vector3f(counter * 0.002f, counter * 0.002f, counter * 0.002f));
			setFogColor(new Vector3f(counter * .00072f, counter * .00072f, counter * .00098f));
			setSkyColor(new Vector3f(counter * .00072f, counter * .00072f, counter * .00098f));
		} else if (time >= 12000 && time < 18000) {
			lights.get(0).setPosition(new Vector3f(0, counter, -1000));
			lights.get(0).setColor(new Vector3f(counter * 0.002f, counter * 0.002f, counter * 0.002f));
			setFogColor(new Vector3f(0.51984f, 0.51984f, 0.70756f));
			setSkyColor(new Vector3f(0.51984f, 0.51984f, 0.70756f));
		} else if (time >= 18000 && time < 24000) {
			counter--;
			lights.get(0).setPosition(new Vector3f(0, counter, -1000));
			lights.get(0).setColor(new Vector3f(counter * 0.002f, counter * 0.002f, counter * 0.002f));
			setFogColor(new Vector3f(counter * .00072f, counter * .00072f, counter * .00098f));
			setSkyColor(new Vector3f(counter * .00072f, counter * .00072f, counter * .00098f));
		}
	}

	public Vector3f getFogColor() {
		return fogColor;
	}

	public void setFogColor(Vector3f fogColor) {
		this.fogColor = fogColor;
	}

	public Vector3f getSkyColor() {
		return skyColor;
	}

	public void setSkyColor(Vector3f skyColor) {
		this.skyColor = skyColor;
	}

}
