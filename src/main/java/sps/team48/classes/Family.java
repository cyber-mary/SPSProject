package sps.team48.classes;

import com.google.cloud.datastore.Key;
import java.util.List;
import java.util.ArrayList;

// A Family made up of different User Accounts
public  class Family {
	private  long id;
	private  String name;
	private  List<Key> members;

	public Family(long id, String name) {
    this.id = id;
    this.name = name;
		List<Key> members = new ArrayList<>();
	}
}