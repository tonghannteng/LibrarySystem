package book;

import java.util.ArrayList;
import java.util.List;

public class WaitingQueue {
	private List<WaitingEntry> waitingEntries = new ArrayList<WaitingEntry>();

	public List<WaitingEntry> getWaitingEntries() {
		return waitingEntries;
	}

	public void setWaitingEntries(List<WaitingEntry> waitingEntries) {
		this.waitingEntries = waitingEntries;
	}
}
