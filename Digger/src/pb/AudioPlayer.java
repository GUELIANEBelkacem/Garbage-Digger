package pb;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		try {
			soundMap.put("coin", new Sound("coin.ogg"));
			soundMap.put("lost", new Sound("lost.ogg"));
			soundMap.put("victory", new Sound("victory.ogg"));
			musicMap.put("music", new Music("backgroundmusic.ogg"));
			musicMap.put("run", new Music("run.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	public static void stopMusic(String key) {
		musicMap.get("music").stop();
	}
	
	public static Sound getSoud(String key) {
		return soundMap.get(key);
	}

	
}
