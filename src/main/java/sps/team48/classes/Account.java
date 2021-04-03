package sps.team48.classes;

// A user Account
public final class Account {
	private final long id;
	private final String email;
  private final String firstName;
  private final String lastName;
  private final String age;
  private final String caughtCovid;
  private final String vaccinationStatus;
  private final String password;

	public Account(long id, String email, String firstName, String lastName, String age, String caughtCovid, String vaccinationStatus, String password) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.caughtCovid = caughtCovid;
    this.vaccinationStatus = vaccinationStatus;
    this.password = password;
	}
}