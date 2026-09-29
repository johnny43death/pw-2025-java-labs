package pl.edu.pw.elka.prm2t.lab5;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

public class AddressBook {

    private final Map<String, Set<String>> addresses;

    public AddressBook() {
        this.addresses = new HashMap<>();
    }


    public void readFile(String inFile){
        Path path = Paths.get(inFile);
        try {
            List<String> lines = Files.readAllLines(path);
            for(int i=0; i<lines.size(); i++) {
                String[] parts = lines.get(i).split(";");
                String userName = parts[0];
                Set<String> userAddresses = new HashSet<>(Arrays.asList(parts).subList(1, parts.length));
                addresses.put(userName, userAddresses);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void writeFile(String outFile){
        try (FileWriter writer = new FileWriter(outFile)) {
            for (Map.Entry<String, Set<String>> entry : addresses.entrySet()) {
                StringBuilder line = new StringBuilder(entry.getKey());
                for (String address : entry.getValue()) {
                    line.append(";").append(address);
                }
                writer.write(line.toString() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressBook that)) return false;
        return Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresses);
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "addresses=" + addresses +
                '}';
    }

    public String[] getAddresses(String userName){
        Set<String> userAddresses = addresses.get(userName);
        if (userAddresses != null) {
            return userAddresses.toArray(new String[0]);
        }
        return new String[0];
    }

    public void addAddress(String userName, String newAddress) {
        Set<String> userAddresses = addresses.computeIfAbsent(userName, k -> new HashSet<>());
        userAddresses.add(newAddress);
    }


    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        addressBook.readFile("resource/input_addressbook.txt");
        System.out.println(addressBook);
        System.out.println(Arrays.toString(addressBook.getAddresses("Anna Acka")));

        addressBook.addAddress("Marcin","123@123.pl");

        addressBook.writeFile("resource/output_addressbook.txt");



    }
}