import java.util.Scanner;
public class Main {
	
	// Metoda care preia date pentru a crea obiecte de tip Guest
	public static Guest createGuest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti numele: ");
		String nume = sc.nextLine();
		System.out.println("Introduceti prenumele: ");
		String prenume = sc.nextLine();
		System.out.println("Introduceti adresa de e-mail: ");
		String email = sc.nextLine();
		System.out.println("Introduceti numarul de telefon (format \"+0724147649\") ");
		String numarTelefon = sc.nextLine();
		Guest newGuest = new Guest(prenume, nume, email, numarTelefon);
		return newGuest;
	}
	// Metoda care preia obiectul de tip guest pentru autentificare
	public static Guest getGuest(GuestList list) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Alege modul de autentificare, tastand: ");
		System.out.println("\t 1. Nume si prenume \n\t 2. Email \n\t 3. Numar de telefon (format \"+0724147649\")");
		int alegere = sc.nextInt();
		sc.nextLine();
		switch (alegere) {
			case 1:
				System.out.println("Introduceti prenumele: ");
				String firstName = sc.nextLine();
				System.out.println("Introduceti numele: ");
				String lastName = sc.nextLine();
				String fullName = firstName + " " + lastName;
				return list.getGuestByName(fullName);
			case 2:
				System.out.println("Introduceti adresa de email: ");
				String email = sc.nextLine();
				return list.getGuestByEmail(email);
			case 3:
				System.out.println("Introduceti numarul de telefon (format \"+0724147649\")");
				String phoneNo = sc.nextLine();
				return list.getGuestByPhoneNo(phoneNo);
			default:
				System.out.println("Comanda introdusa nu este valida. \nIncercati inca o data.");
				break;
		}
		return null;
	}
	// Metoda aux pentru updatare care faciliteaza introducerea datelor de modificat
	private static String[] getCredentialsForUpdate() {
		Scanner sc = new Scanner(System.in);
		String[] newCredentials = new String[2];
		System.out.println("Alege ce vrei sa modifici: ");
		System.out.println("\t 1. Nume \n\t 2. Prenume \n\t 3. Email \n\t 4. Numar de telefon (format \"+0724147649\")");
		int comanda = sc.nextInt();
		sc.nextLine();
		switch (comanda) {
			case 1:
				System.out.println("Te rog sa introduci numele de familie nou: ");
				String newLastName = sc.nextLine();
				newCredentials[0] = newLastName;
				newCredentials[1] = "lastName";
				break;
			case 2:
				System.out.println("Te rog sa introduci prenumele nou: ");
				String newFirstName = sc.nextLine();
				newCredentials[0] = newFirstName;
				newCredentials[1] = "firstName";
				break;
			case 3:
				System.out.println("Te rog sa introduci noul email: ");
				String newEmail = sc.nextLine();
				newCredentials[0] = newEmail;
				newCredentials[1] = "email";
				break;
			case 4:
				System.out.println("Te rog sa introduci noul numar de telefon: ");
				String newPhoneNo = sc.nextLine();
				newCredentials[0] = newPhoneNo;
				newCredentials[1] = "phoneNo";
				break;
			default:
				System.out.println("Comanda introdusa nu este valida. \nIncercati inca o data.");
				break;
		}
		return newCredentials;
	}
	
	// Metoda de afisare a comenzilor din consola
	public static void help() {
		System.out.println("helo             - Afiseaza aceasta lista de comenzi");
		System.out.println("add              - Adauga o noua persoana (inscriere)");
		System.out.println("check            - Verifica daca o persoana este inscrisa la eveniment");
		System.out.println("remove           - Sterge o persoana existenta din lista");
		System.out.println("update           - Actualizeaza detaliile unei persoane");
		System.out.println("guests           - Lista de persoane care participa la eveniment");
		System.out.println("waitlist         - Persoanele din lista de asteptare");
		System.out.println("available        - Numarul de locuri libere");
		System.out.println("guests-no        - Numarul de persoane care participa la eveniment");
		System.out.println("waitlist_no      - Numarul de persoane din lista de asteptare");
		System.out.println("subscribe_no     - Numarul total de persoane inscrise");
		System.out.println("search           - Cauta toti invitatii conform sirului de caractere introdus");
		System.out.println("quit             - Inchide aplicatia");
	}	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Bine ati venit in aplicatia noastra de gestionare a invitatilor!");
		System.out.println("Introduceti numarul de locuri disponibile: ");
		while (!sc.hasNextInt()) {
			   System.out.println("Introduceti un numar!");
			   sc.nextLine();
		}
		int locuri = sc.nextInt();
		GuestList guestList= new GuestList(locuri);
		boolean turnOff = true;
		sc.nextLine();
		while (turnOff) {
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi");
			String comanda = sc.nextLine();
			switch(comanda) {
				case "help":
					help();
					break;
				case "add":
					guestList.addGuest(createGuest());
					break;
				case "check":
					guestList.checkIfSubscribed(getGuest(guestList));
					break;
				case "remove":
					guestList.remove(getGuest(guestList));
					break;
				case "update":
					Guest guestForUpdate = getGuest(guestList);
					if (guestForUpdate != null) {
						guestList.update(guestForUpdate, getCredentialsForUpdate());
					}
					break;
				case "guests":
					guestList.printGuestList();
					break;
				case "waitlist":
					guestList.printWaitList();
					break;
				case "available" :
					System.out.println("Numarul de locuri libere este de: " + guestList.getAvailableSeatsNo());
					break;
				case "guests_no":
					System.out.println("Numarul de oameni care participa la eveniment este: " + guestList.getRegistredParticipantsNumber());
					break;
				case "waitlist_no":
					System.out.println("Numarul de oameni care sunt in asteptare: " + guestList.getQueuedNumber());
					break;
				case "subscribe_no":
					System.out.println("Numarul total de persoane inscrise: " + guestList.getInterestedPersons());
					break;
				case "search":
					System.out.println("Introduceti sirul de caractere dupa care vreti sa faceti cautarea: ");
					String search = sc.nextLine();
					guestList.printPartialSearchReg(guestList.partialSearchReg(search));
					break;
				case "quit":
					turnOff = false;
					System.out.println("Aplicatia se inchide...");
					break;
				default:
					System.out.println("Comanda introdusa nu este valida. \nIncercati inca o data.");
					break;
				}
			}
		}
		
	

}
