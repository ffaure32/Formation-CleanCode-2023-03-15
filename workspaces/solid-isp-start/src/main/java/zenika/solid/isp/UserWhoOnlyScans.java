package zenika.solid.isp;

public class UserWhoOnlyScans {

	private final Machine scanner;

	public UserWhoOnlyScans(Machine scanner) {
		this.scanner = scanner;
	}

	public void scanDocument() {
		scanner.scan();
	}
}
