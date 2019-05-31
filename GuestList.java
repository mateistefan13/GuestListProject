import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestList {

	private int noOfSeats;
	private ArrayList<Guest> registredParticipants;
	private ArrayList<Guest> queuedParticipants;
	
	GuestList(int noOfSeats) {
		this.noOfSeats = noOfSeats;
		this.registredParticipants = new ArrayList<Guest>();
		this.queuedParticipants  = new ArrayList<Guest>();
	}
	// Metoda auxiliara ce verifica daca credentialele sunt folosite
	private boolean isUsed(ArrayList<Guest> list, Guest ob) {
		for (int i = 0; i < list.size(); i++) {
			Guest registredParticipant = list.get(i);
			if (registredParticipant.isUsed(ob)){
				return true;
			}
		}
		return false;
	}
	// 1. Definirea unei metode pentru adaugarea unei persoane in lista 
	public int addGuest(Guest ob) {
		boolean isInQueued = this.isUsed(this.queuedParticipants, ob);
		boolean isInRegistred = this.isUsed(this.registredParticipants, ob);
		if (isInQueued == true || isInRegistred == true) {
			System.out.println(ob.getName() +  " este deja inscris la eveniment.");
			return -1;
		}
		if (this.getAvailableSeatsNo() > 0) {
			this.registredParticipants.add(ob);
			System.out.println(ob.getName() + ", felicitari! Locul tau la eveniment este confirmat. Te asteptam!\"");
			return 0;
			
		} else {
			this.queuedParticipants.add(ob);
			int pozitie = this.checkQueuePos(ob) + 1;
			System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine <" 
			+ pozitie + ">. Te vom notifica daca un loc devine disponibil!");
			return checkQueuePos(ob);
		} 
	}
	// Metoda aux care afiseaza pozitia unei persoane in lista de asteptare
	private int checkQueuePos(Guest ob) {
		return this.queuedParticipants.indexOf(ob);	
	}
	// 2. Determinarea daca o persoana este inscrisa la eveniment
	public boolean checkIfSubscribed(Guest ob) {
		if (ob != null) {
			System.out.println("Persoana este inscrisa la eveniment");
			return true;
		} 
		System.out.println("Persoana nu este inscrisa la eveniment");
		return false;
	}
	// 3. Eliminarea unei persoane
	public boolean remove(Guest ob) {
		if (ob != null) {
			this.removeAuxRegistred(ob);
			System.out.println("Persoana a fost stearsa");
			return true;
		}
		System.out.println("Persoana nu a putut fi stearsa");
		return false;
	}
	// Metoda aux pentru stergere care verifica daca sunt persoane in lista de asteptare pe care sa le mute
	public void removeAuxRegistred(Guest ob) {
		this.registredParticipants.remove(ob);
		if (this.queuedParticipants.isEmpty() == false) {
			Guest movedPerson = this.queuedParticipants.get(0);
			this.queuedParticipants.remove(movedPerson);
			this.registredParticipants.add(movedPerson);
			System.out.println(movedPerson.getName() + ", felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
		}
	}
	
	// 4. Metoda de update
	public boolean update(Guest ob, String[] string) {
		// String[0] este valoarea, String[1] este comanda
		if (ob == null ) {
			return false;
		}
		switch (string[1]) {
			case "lastName":
				if (this.searchByLastName(string[0]) == true) {
					System.out.println("Numele de familie este deja existent. Te rog sa incerci din nou");
				} else if (string[0].equals("")) {
					System.out.println("Nu poti introduce un sir vid. Te rog sa incerci din nou");
				} else {
					ob.setLastName(string[0]);
					System.out.println("Persoana a fost updatata");
					return true;
				}
				break;
			case "firstName":
				if (string[0].equals("")) {
					System.out.println("Nu poti introduce un sir vid. Te rog sa incerci din nou");
				} else {
					ob.setFirstName(string[0]);
					System.out.println("Persoana a fost updatata");
					return true;
				}
				break;
			case "email":
				if (this.searchByEmail(string[0]) == true) {
					System.out.println("Emailul este deja existent. Te rog sa incerci din nou");
				} else if (string[0].equals("")) {
					System.out.println("Nu poti introduce un sir vid. Te rog sa incerci din nou");
				} else {
					ob.setEmail(string[0]);
					System.out.println("Persoana a fost updatata");
					return true;
				}
				break;
			case "phoneNo":
				if (this.searchByPhoneNo(string[0]) == true) {
					System.out.println("Numarul de telefon este deja existent. Te rog sa incerci din nou");
				} else if (string[0].equals("")) {
					System.out.println("Nu poti introduce un sir vid. Te rog sa incerci din nou");
				} else {
					ob.setPhoneNo(string[0]);
					System.out.println("Persoana a fost updatata");
					return true;
				}
				break;
			default:
				System.out.println("Comanda introdusa este invalida");
				return false;
		}
		return false;
	}
	// 5. Metoda pentru afisarea persoanelor care au primit loc
	public void printGuestList() {
		if (this.getRegistredParticipantsNumber() <= 0) {
			System.out.println("Nu s-a inscris niciun participant...");
		} else {
			for (int i = 0; i < this.getRegistredParticipantsNumber(); i++) {
				System.out.println(i + 1 + ". Nume: " + this.getRegistredParticipants().get(i).getName() + ", Email: " + this.getRegistredParticipants().get(i).getEmail() + ", Telefon: " + this.getRegistredParticipants().get(i).getPhoneNo());
			} 
		}
	}
	// 6. Metoda pentru afisarea persoanelor care asteapta un loc
	public void printWaitList() {
		if (this.getQueuedNumber() <= 0) {
			System.out.println("Lista de asteptare este goala...");
		} else {
			for (int i = 0; i < this.getQueuedNumber(); i++) {
				System.out.println(i + 1 + ". Nume:" + this.getQueuedParticipants().get(i).getName() + ", Email: " + this.getQueuedParticipants().get(i).getEmail() + ", Telefon: " + this.getQueuedParticipants().get(i).getPhoneNo());
			}
		}
	}
	// 7. Metoda care afiseaza locurile ramase disponiobile
	public int getAvailableSeatsNo() {
		return this.noOfSeats - getRegistredParticipantsNumber();
	}
	// 8. Metoda care afiseaza cate persoane sunt in lista de asteptare
	public int getQueuedNumber() {
		return this.queuedParticipants.size();
	}
	// 9. Metoda care afiseaza cate persoane sunt inscrise si au loc pentru participare
	public int getRegistredParticipantsNumber() {
		return this.registredParticipants.size();
	}
	// 10. Metoda care afiseaza numarul total al doritorilor de a participa
	public int getInterestedPersons() {
		return getRegistredParticipantsNumber() + getQueuedNumber();
	}
	// 11. Partial search for Registred ppl (invitatii)
	public ArrayList<Guest> partialSearchReg(String searchingString) {
		searchingString = searchingString.toLowerCase();
		ArrayList<Guest> contacteInvitate = new ArrayList<Guest>();
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getName().toLowerCase().contains(searchingString) ||registredParticipant.getEmail().toLowerCase().contains(searchingString) ||
				registredParticipant.getPhoneNo().toLowerCase().contains(searchingString)) {
				contacteInvitate.add(registredParticipant);
			}
		}
		return contacteInvitate;
	}
	public void printPartialSearchReg(ArrayList<Guest> contactsList) {
		if (contactsList.size() > 0) {
			System.out.println("In functie de sirul de caractere oferit, in lista celor invitati, am gasit: ");
			for (int i = 0; i < contactsList.size(); i++) {
				System.out.println(i + 1 + ". Nume:" + contactsList.get(i).getName() + ", Email: " + contactsList.get(i).getEmail() + ", Telefon: " + contactsList.get(i).getPhoneNo());
			}
		} else {
			System.out.println("In functie de sirul de caractere oferit, nu am gasit nimic relevant in lista celor invitati.");
		}
		
	}
	//Metode care returneaza listele de participanti
	public ArrayList<Guest> getRegistredParticipants() {
		return this.registredParticipants;
	}
	
	public ArrayList<Guest> getQueuedParticipants() {
		return this.queuedParticipants;
	}
	// Trei metode auxiliare care facilieaza cautarea dupa nume, telefon si email
	// Metode aux care returneaza persoane inscrise
	public Guest getGuestByName(String fullName) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getName().equalsIgnoreCase(fullName)) {
				return registredParticipant;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getName().equalsIgnoreCase(fullName)) {
				return queuedParticipant;
			}
		}
		System.out.println("Persoana " + fullName + " nu este inscrisa la eveniment");
		return null;
	}
		
	public Guest getGuestByEmail(String email) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getEmail().equalsIgnoreCase(email)) {
				return registredParticipant;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getEmail().equalsIgnoreCase(email)) {
				return queuedParticipant;
			}
		} 
		System.out.println("Persoana cu emailul " + email + " nu este inscrisa la eveniment");
		return null;
	}
		
	public Guest getGuestByPhoneNo(String phoneNo) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getPhoneNo().equalsIgnoreCase(phoneNo)) {	
				return registredParticipant;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getPhoneNo().equalsIgnoreCase(phoneNo)) {
				return queuedParticipant;
			}
		} 
		System.out.println("Persoana cu numarul de telefon " + phoneNo + " nu este inscrisa la eveniment");
		return null;
	}
		
	// Trei metode auxiliare care facilieaza cautarea dupa nume, telefon si email
	public boolean searchByName(String name) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean searchByEmail(String email) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
		
	public boolean searchByPhoneNo(String phoneNo) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getPhoneNo().equalsIgnoreCase(phoneNo)) {
				return true;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getPhoneNo().equalsIgnoreCase(phoneNo)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean searchByLastName(String name) {
		for (int i = 0; i < this.registredParticipants.size(); i++) {
			Guest registredParticipant = this.registredParticipants.get(i);
			if (registredParticipant.getLastName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		for (int i = 0; i < this.queuedParticipants.size(); i++) {
			Guest queuedParticipant = this.queuedParticipants.get(i);
			if (queuedParticipant.getLastName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
}
