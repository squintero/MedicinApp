package com.squintero.medicinapp.utilities;


public final class RegularExpressionManager {

    public static boolean validDni(String dni) {

        String regExp = "^[0-9A-Za-z-.]{1,16}$";

        if (dni.length() > 16) {
            return false;
        }

        return dni.matches(regExp);
    }

    public static boolean validEmail(String email) {

        email = email.toLowerCase();

        String regExp = "^[a-z0-9!#$%&\\'*+\\/=?^`{}|~_-]+[.a-z0-9!#$%&\\'*+\\/=?^`{}|~_-]*@[a-z0-9]+[._a-z0-9-]*\\.[a-z0-9]+$";

        if (email.length() > 128) {
            return false;
        }

        return email.matches(regExp);
    }

    public static boolean validNameSurname(String name) {

        String regExp = "^[^0-9!<>,;?=+()@#\"°{}_$%:]*$";

        if (name.length() > 32) {
            return false;
        }

        return name.matches(regExp);
    }

    public static boolean validPassword(String password) {

        String regExp = "^[.a-zA-Z_0-9-!@#$%\\^&*()]{6,32}$";

        return password.matches(regExp);
    }

    public static boolean validPhone(String phone) {

        String regExp = "^[+0-9. ()-]*$";

        if (phone.length() < 9 || phone.length() > 16)
            return false;

        return phone.matches(regExp);
    }
}
