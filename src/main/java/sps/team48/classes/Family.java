package sps.team48.classes;

import com.google.cloud.datastore.Key;
import java.util.List;

// A Family made up of different User Accounts
public final class Family {
	private final long id;
	private final String name;
	private final List<Key> members;

	public Family(long id, String name) {
    this.id = id;
    this.name = name;
		List<Key> members = null;
	}
}