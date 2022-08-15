package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User user = null;
        for (User u : users) {
            if (login.equals(u.getUsername())) {
                user = u;
                break;
            }
            }
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return user;
    }

    public static boolean validate(User user) throws UserInvalidException {
         if (!(user.getUsername().length() >= 3 && user.isValid())) {
             throw new UserInvalidException("Invalid user.");
         }
        return user.getUsername().length() >= 3 && user.isValid();
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
        } catch (UserInvalidException uie) {
            uie.printStackTrace();
        } catch (UserNotFoundException unfe) {
            unfe.printStackTrace();
        }
    }
}