package net.helix.core.util;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils {

    private static Pattern namePattern = Pattern.compile("^[a-zA-Z0-9_\\-]+$");

    public static boolean validUsername(String nick) {
        if (nick.length() > 16) {
            return false;
        }
        if (isRandomNick(nick)) {
            return false;
        }
        return namePattern.matcher(nick).matches();
    }

    private static boolean isRandomNick(String str) {
        if (str.length() < 14) {
            return false;
        }
        int maiusculas = 0, minusculas = 0, numeros = 0;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                maiusculas++;
            } else if (Character.isLowerCase(str.charAt(i))) {
                minusculas++;
            } else if (Character.isDigit(str.charAt(i))) {
                numeros++;
            }
        }
        int maiper = maiusculas * (100 / str.length());
        int minper = minusculas * (100 / str.length());
        int numper = numeros * (100 / str.length());

        boolean random = false;
        if (minper >= 50 && numper >= 42) {
            random = true;
        } else if (maiper >= 50 && numper >= 42) {
            random = true;
        } else if (minper >= 80 && numper >= 14) {
            random = true;
        } else if (minper == 63 && numper == 35) {
            random = true;
        } else if (maiper == 0 && minper >= 49 && minper == numper) {
            random = true;
        } else if (minper == 0 && maiper >= 49 && maiper == numper) {
            random = true;
        }
        return random;
    }

    public static String cpuQuality(double cpu) {
        if (cpu <= 60.0D) {
            return "§a" + cpu;
        }
        if ((cpu > 60.0D) && (cpu < 90.0D)) {
            return "§e" + cpu;
        }
        return "§c" + cpu;
    }

    public static String ramQuality(double percentage) {
        if (percentage <= 60.0D) {
            return "§a" + percentage;
        }
        if ((percentage > 60.0D) && (percentage < 90.0D)) {
            return "§e" + percentage;
        }
        return "§c" + percentage;
    }

    public static String toMillis(double d) {
        String string = String.valueOf(d);
        StringBuilder sb = new StringBuilder();
        boolean stop = false;
        for (char c : string.toCharArray()) {
            if (stop)
                return sb.append(c).toString();
            if (c == '.')
                stop = true;
            sb.append(c);
        }
        return sb.toString();
    }

    public static String reformularMegaBytes(Long megaBytes) {
        if (megaBytes <= 999) {
            return megaBytes + " MB";
        }
        long mb = megaBytes;
        long gigas = (megaBytes / 1000);

        mb = (mb - (gigas * 1000));

        if (mb != 0) {
            String mbFormatted = String.valueOf(mb).substring(0, 1);
            return gigas + "." + mbFormatted + " GB";
        } else {
            return gigas + " GB";
        }
    }

    public static String formatArrayToString(List<String> array) {
        return formatArrayToString(array, false);
    }

    public static String formatArrayToString(List<String> array, boolean lowerCase) {
        if (array.size() == 1) {
            return array.get(0);
        }
        final StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            final String string = array.get(i);
            if (lowerCase) {
                toReturn.append(string.toLowerCase()).append(array.size() - i > 1 ? ", " : "");
            } else {
                toReturn.append(string).append(array.size() - i > 1 ? ", " : "");
            }
        }
        return toReturn.toString();
    }

    public static String formatArrayToStringWithoutSpace(List<String> array, boolean lowerCase) {
        if (array.size() == 1) {
            return array.get(0);
        }
        final StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            final String string = array.get(i);
            if (lowerCase) {
                toReturn.append(string.toLowerCase()).append(array.size() - i > 1 ? "," : "");
            } else {
                toReturn.append(string).append(array.size() - i > 1 ? "," : "");
            }
        }
        return toReturn.toString();
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String createArgs(int index, String[] args) {
        return createArgs(index, args, "", false);
    }

    public static String createArgs(int index, String[] args, String defaultArgs, boolean color) {
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < args.length; i++)
            sb.append(args[i]).append((i + 1 >= args.length ? "" : " "));

        if (sb.length() == 0)
            sb.append(defaultArgs);

        return color ? sb.toString().replace("&", "§") : sb.toString();
    }

    public static String replace(String message, String[] old, String[] now) {
        String replaced = message;
        for (int i = 0; i < old.length; i++)
            replaced = replaced.replace(old[i], now[i]);
        return replaced;
    }

    public static String reformularValor(int valor) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        return decimalFormat.format(valor);
    }

    public static List<String> reformuleFormattedWithoutSpace(String formatted) {
        List<String> lista = new ArrayList<>();

        if (formatted.equals("") || (formatted.equals(" ") || (formatted.isEmpty()))) {
            return lista;
        }

        if (formatted.contains(" ")) {
            formatted = formatted.replaceAll(" ", "");
        }

        if (formatted.contains(",")) {
            for (String str : formatted.split(",")) {
                lista.add(str);
            }
        } else {
            lista.add(formatted);
        }
        return lista;
    }

    public static List<String> reformuleFormatted(String formatted) {
        List<String> lista = new ArrayList<>();
        if (formatted.equals("") || (formatted.equals(" ") || (formatted.isEmpty()))) {
            return lista;
        }
        if (formatted.contains(",")) {
            for (String str : formatted.split(",")) {
                lista.add(str);
            }
        } else {
            lista.add(formatted);
        }
        return lista;
    }
}