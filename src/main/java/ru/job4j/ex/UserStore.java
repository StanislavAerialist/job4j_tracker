package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User user = null;
        for (int i = 0; i < users.length; i++) {
            if (login.equals(users[i].getUsername())) {
                user = users[i];
            }
            }
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public static boolean validate(User user) throws UserInvalidException {
        boolean rsl = user.getUsername().length() >= 3 && user.isValid();
         if (!rsl) {
             throw new UserInvalidException();
         }
        return rsl;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException e) {
            System.out.println("Invalid user.");
        } catch (UserNotFoundException e) {
            System.out.println("User not found.");
        }
    }
}