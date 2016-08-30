package terrainEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;

public class TerrainEditor {

	public static void main(String[] args) {
		/*
		 * INIT STUFF
		 */
		EditorWindow editor = new EditorWindow();
		Loader loader = new Loader();
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer(loader, camera);

		/*
		 * TERRAIN SETUP
		 */
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

		List<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(terrain);

		/*
		 * MODEL SETUP
		 */
		TexturedModel fern01 = new TexturedModel(OBJLoader.loadObjModel("fern", "blend", loader), new ModelTexture(loader.loadTexture("fern")));
		fern01.getModelTexture().setHasTransparency(true);
		ModelTexture fern01Texture = fern01.getModelTexture();
		fern01Texture.setNumberOfRows(2);

		TexturedModel grass01 = new TexturedModel(OBJLoader.loadObjModel("grassModel", "blend", loader), new ModelTexture(loader.loadTexture("grassTexture")));
		grass01.getModelTexture().setHasTransparency(true);
		
		TexturedModel box01 = new TexturedModel(OBJLoader.loadObjModel("box", "blend", loader), new ModelTexture(loader.loadTexture("box")));
		box01.getModelTexture().setHasTransparency(true);

		/*
		 * ENTITY SETUP
		 */
		
		List<Entity> entities = new ArrayList<Entity>();
		Random rand = new Random(783355);
		//Fern Entities Random Placement
		for (int i = 0; i < 800; i++) {
			if (i % 1 == 0) {
				float x = rand.nextFloat() * 800;
				float z = rand.nextFloat() * -800;
				float y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(fern01, rand.nextInt(4), new Vector3f(x, y, z), 0, rand.nextFloat() * 360, 0, rand.nextFloat() * 0.3f + 0.4f));
			}
		}

		/*
		 * CREATE LIGHTS
		 * "lights.add(new Light(new Vector3f(posX, posY, posZ), new Vector3f(R, G, B), new Vector3f(attenX, attenY, attenZ)));"
		 */
		List<Light> lights = new ArrayList<Light>();
		Light sun = new Light(new Vector3f(1000, 1000, -400), new Vector3f(1.3f, 1.3f, 1.3f));
		lights.add(sun);
		
		entities.add(0, new Entity(box01, 0, new Vector3f(lights.get(0).getPosition().x, lights.get(0).getPosition().y, lights.get(0).getPosition().z), 0, 0, 0, 20));


		/*
		 * CREATE GUI
		 */
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		//GuiTexture shadowMap = new GuiTexture(renderer.getShadowMapTexture(), new Vector2f(0, 0), new Vector2f(0.5f, 0.5f));
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);

		/****************
		 ***START LOOP***
		 ****************/
		int fps = 0;
		long lastFps;
		lastFps = DisplayManager.getCurrentTime();
		boolean prevState = false;

		while (!Display.isCloseRequested()) {
		/**
		 * FPS Counter
		 */
			if (DisplayManager.getCurrentTime() - lastFps > 1000) {
				editor.updateFps(fps);
				fps = 0;
				lastFps += 1000;
			}
			fps++;
		/********************************/ 	

		/**
		 * UPDATES
		 */
			camera.move();
			picker.update();
			renderer.renderShadowMap(entities, sun);
			renderer.renderScene(entities, terrains, lights, camera, new Vector4f(0, 1, 0, 0));
		/**
		 * UPDATE STATS
		 */
			Vector3f terrainPoint = picker.getCurrentTerrainPoint();

			if (terrainPoint != null) {
				editor.updateStats(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z, terrainPoint.x, terrainPoint.y, terrainPoint.z);
			}
			
		/**
		 * LIGHT PLACEMENT
		 */
			if (terrainPoint != null && Mouse.isButtonDown(0) && !prevState) {
				lights.add(new Light(terrainPoint.translate(0, editor.getLightHeight(), 0), editor.getLightColor(), editor.getAttenuation()));
			}
			prevState = Mouse.isButtonDown(0);

			entities.get(0).setPosition(new Vector3f(lights.get(0).getPosition().x, lights.get(0).getPosition().y, lights.get(0).getPosition().z));
		/**
		 * FINAL TASKS	
		 */
			renderer.renderScene(entities, terrains, lights, camera, new Vector4f(0, -1, 0, 100000));
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
		}
		
	/**
	 * CLEANUP
	 */
		renderer.cleanUp();
		loader.cleanUp();
		guiRenderer.cleanUp();
		entities.clear();
		DisplayManager.closeDisplay();
	}
}
