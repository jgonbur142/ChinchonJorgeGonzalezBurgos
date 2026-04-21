package app;

public class Match implements IMatch{
	
	public IMatch createMatch(TypeOfMatch type) {
		switch (type) {
		case LESSPLAYERS -> {
			return new LessPlayers();
		}
		case MOREPLAYERS ->{
			return new MorePlayers();
		}
		default -> {
			return null;
		}
		}
	}

	@Override
	public void start() {
	}

	@Override
	public void distributeCards() {
	}

	@Override
	public void playNewRound() {
	}

	@Override
	public void playTurn() {
	}

	@Override
	public void showRoundResults() {
	}	
	
	
}
