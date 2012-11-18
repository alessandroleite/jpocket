package cc.alessandro.jpocket;

public enum State {

	ARCHIVED(1), DELETED(2);

	private final int id;

	private State(int id) {
		this.id = id;
	}

	public static State valueOf(int id) {
		for (State state : State.values()) {
			if (state.getId() == id) {
				return state;
			}
		}
		return null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}