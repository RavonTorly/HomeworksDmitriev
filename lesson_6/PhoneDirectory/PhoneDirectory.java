package PhoneDirectory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneDirectory {
    private Map<String, List<String>> phoneDirectory;

    public PhoneDirectory() {
        this.phoneDirectory = new HashMap<>();
    }

    public void addPhones(String lastName, String phoneNumber) {
        if (!phoneDirectory.containsKey(lastName)) {
            phoneDirectory.put(lastName, new ArrayList<>());
        }
        phoneDirectory.get(lastName).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return phoneDirectory.getOrDefault(lastName, new ArrayList<>());
    }
}
