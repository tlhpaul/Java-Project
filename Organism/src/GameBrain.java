

/**
 * This is the class extending game configuration that is publicly known.
 * @author paulhsu
 *
 */
public class GameBrain implements GameConfig {

	@Override
	public int s() {
		return 1;
	}

	@Override
	public int v() {
		return 16;
	}

	@Override
	public int u() {
		return 200;
	}

	@Override
	public int M() {
		return 800;
	}

	@Override
	public int K() {
		return 30;
	}

}
