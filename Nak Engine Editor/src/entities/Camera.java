package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(100, 20, -100);
	private float pitch = 0;
	private float yaw = 0;
	private float roll;
	private float speed = 0.3f;

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
			position.x += (float) Math.sin(Math.toRadians(yaw)) * speed;
			position.z -= (float) Math.cos(Math.toRadians(yaw)) * speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.x -= (float) Math.sin(Math.toRadians(yaw)) * speed;
			position.z += (float) Math.cos(Math.toRadians(yaw)) * speed;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x -= (float) Math.sin(Math.toRadians(yaw - 90)) * speed;
			position.z += (float) Math.cos(Math.toRadians(yaw - 90)) * speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x += (float) Math.sin(Math.toRadians(yaw - 90)) * speed;
			position.z -= (float) Math.cos(Math.toRadians(yaw - 90)) * speed;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speed;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= speed;
		}
	}

}
