package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(300, 100, 0);
	private float pitch = 0;
	private float yaw = 0;
	private float roll;

	public Camera() {

	}

	public void move() {
		calculatePitch();
		calculateCameraPosition();

	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	private void calculateCameraPosition() {
		checkInputs();
	}

	private void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.3f;
			pitch -= pitchChange;
		}
		if (Mouse.isButtonDown(1)) {
			float yawChange = Mouse.getDX() * 0.3f;
			yaw += yawChange;
		}
	}
	
	private void checkInputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.x += (float)Math.sin(Math.toRadians(yaw));
		    position.z -= (float)Math.cos(Math.toRadians(yaw));
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.x -= (float)Math.sin(Math.toRadians(yaw));
		    position.z += (float)Math.cos(Math.toRadians(yaw));
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x -= (float)Math.sin(Math.toRadians(yaw-90));
			position.z += (float)Math.cos(Math.toRadians(yaw-90));
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x += (float)Math.sin(Math.toRadians(yaw-90));
			position.z -= (float)Math.cos(Math.toRadians(yaw-90));
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += 1;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 1;
		}
	}

}
