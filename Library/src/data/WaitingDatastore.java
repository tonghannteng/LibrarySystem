package data;

import java.util.List;

import book.WaitingEntry;

public class WaitingDatastore extends DataAccessBase implements IWaiting {
	@Override
	public List<WaitingEntry> getList() {
		List<WaitingEntry> allWaitingEntries = getAllItems();
		return allWaitingEntries;
	}

	@Override
	public void add(String bookISBN, int memberId) {
		WaitingEntry waitingEntry = new WaitingEntry(bookISBN, memberId);
		List<WaitingEntry> allWaitingEntries = getAllItems();
		allWaitingEntries.add(waitingEntry);
		save(allWaitingEntries);
	}

	@Override
	public void delete(String bookISBN, int memberId) {
		List<WaitingEntry> allWaitingEntries = getAllItems();

		boolean deleted = false;
		int i = 0;
		while (!deleted && i < allWaitingEntries.size()) {
			if (allWaitingEntries.get(i).getMemberId() == memberId
					&& allWaitingEntries.get(i).getISBN().equals(bookISBN)) {
				allWaitingEntries.remove(i);
				save(allWaitingEntries);
				deleted = true;
			}
			i++;
		}
	}
}
