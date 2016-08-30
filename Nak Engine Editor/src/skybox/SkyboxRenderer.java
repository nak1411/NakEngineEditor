package skybox;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import models.RawModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;

public class SkyboxRenderer {

	private static final float SIZE = 500f;
	private float density = 0.003f;
	private float gradient = 2.0f;

	private static final float[] VERTICES = { -SIZE, SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE,

			-SIZE, -SIZE, SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, -SIZE, SIZE,

			SIZE, -SIZE, -SIZE, SIZE, -SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, -SIZE, SIZE, -SIZE, -SIZE,

			-SIZE, -SIZE, SIZE, -SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE,

			-SIZE, SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE, -SIZE,

			-SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE };

	private static String[] TEXTURE_FILES = { "right", "left", "top", "bottom", "back", "front" };
	private static String[] NIGHT_TEXTURE_FILES = { "nightRight", "nightLeft", "nightTop", "nightBottom", "nightBack", "nightFront" };

	private RawModel cube;
	private int texture;
	private int nightTexture;
	private SkyboxShader shader;
	private float time = 0;

	public SkyboxRenderer(Loader loader, Matrix4f projectionMatrix) {
		cube = loader.loadToVAO(VERTICES, 3);
		texture = loader.loadCubeMap(TEXTURE_FILES);
		nightTexture = loader.loadCubeMap(NIGHT_TEXTURE_FILES);
		shader = new SkyboxShader();
		shader.start();
		shader.connectTextureUnits();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Camera camera, Vector3f fogColor) {
		shader.start();
		shader.loadViewMatrix(camera);
		shader.loadFogColor(fogColor);
		GL30.glBindVertexArray(cube.getVaoId());
		GL20.glEnableVertexAttribArray(0);
		bindTextures();
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	private void bindTextures() {
		time += DisplayManager.getFrameTimeSeconds() * 1000;
		time %= 24000;
		int texture1;
		int texture2;
		float blendFactor;
		if (time >= 0 && time < 6000) {
			texture1 = nightTexture;
			texture2 = nightTexture;
			blendFactor = (time - 0) / (6000 - 0);
		} else if (time >= 6000 && time < 12000) {
			texture1 = nightTexture;
			texture2 = texture;
			blendFactor = (time - 6000) / (12000 - 6000);
		} else if (time >= 12000 && time < 18000) {
			texture1 = texture;
			texture2 = texture;
			blendFactor = (time - 12000) / (18000 - 12000);
		} else {
			texture1 = texture;
			texture2 = nightTexture;
			blendFactor = (time - 18000) / (24000 - 18000);
		}

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture1);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture2);
		shader.loadBlendFactor(blendFactor);
	}

	public float getDensity() {
		return density;
	}

	public float getGradient() {
		return gradient;
	}

}
