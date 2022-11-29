import java.util.*;

public class calc {
    public static int functionToCalculation(String s) {
            try {
                String str = "";
                int i = 0;
                while (s.charAt(i) == ' ') {
                    i++;
                }
                str = str.concat(String.valueOf(s.charAt(i)));
                i++;
                if (s.charAt(i) == '0') {
                    str = str.concat(String.valueOf(s.charAt(i)));
                    i++;
                }
                int a = Integer.parseInt(str);
                while (s.charAt(i) == ' ') {
                    i++;
                }
                char sign = s.charAt(i);
                i++;
                while (s.charAt(i) == ' ') {
                    i++;
                }
                str = "";
                str = str.concat(String.valueOf(s.charAt(i)));
                i++;
                if (i < s.length())
                    str = str.concat(String.valueOf(s.charAt(i)));
                int b = Integer.parseInt(str);
                if (a > 0 && a <= 10 && b > 0 && b <= 10) {
                    if (sign == '+') {
                        return ((a + b));
                    } else if (sign == '-') {
                        return ((a - b));
                    } else if (sign == '*') {
                        return ((a * b));
                    } else if (sign == '/') {
                        int r = a / b;
                        return (r);
                    } else
                        return (404);
                } else
                    return (404);
            } catch (NumberFormatException | InputMismatchException | StringIndexOutOfBoundsException e) {
                return (404);
        }
    }

    public static String inputRoman(String s) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        String s1 = "";
        String s2 = "";
        try {
            int i = 0;
            while (s.charAt(i) == ' ') {
                i++;
            }
            while (map.containsKey(s.charAt(i))) {
                s1 = s1.concat(String.valueOf(s.charAt(i)));
                i++;
            }
            while (s.charAt(i) == ' ') {
                i++;
            }
            char sym = s.charAt(i);
            i++;
            while (s.charAt(i) == ' ') {
                i++;
            }
            while (i < s.length()) {
                if (map.containsKey(s.charAt(i))) {
                    s2 = s2.concat(String.valueOf(s.charAt(i)));
                    i++;
                }
                else
                    return ("-");
            }
            int sum = map.get(s1.charAt(s1.length()-1));
            if (s1.length() > 1) {
                for (i = s1.length() - 1; i > 0; i--) {
                    if (map.get(s1.charAt(i)) > map.get(s1.charAt(i - 1))) {
                        sum -= map.get(s1.charAt(i - 1));
                    } else {
                        sum += map.get(s1.charAt(i - 1));
                    }
                }
            }
            String str = String.valueOf(sum) + sym;
            sum = map.get(s2.charAt(s2.length()-1));
            if (s2.length() > 1) {
                for (i = s2.length() - 1; i > 0; i--) {
                    if (map.get(s2.charAt(i)) > map.get(s2.charAt(i - 1))) {
                        sum -= map.get(s2.charAt(i - 1));
                    } else {
                        sum += map.get(s2.charAt(i - 1));
                    }
                }
            }
            str = str.concat(String.valueOf(sum));
            return (str);
        } catch (StringIndexOutOfBoundsException e) {
            return ("-");
        }
    }

    public static String outputRoman(int Res) {
        String result = "";
        String[] str = {"","",""};
        float res = Res;
        TreeMap<Integer, Character> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(100, 'C');
        map.put(50, 'L');
        map.put(10, 'X');
        map.put(5, 'V');
        map.put(1, 'I');
        for (int i = 10, j = 0; res > 0; i *= 10, j++) {
            while ((res % i) != 0) {
                for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                    if ((res % i) / entry.getKey() == 0.9f) {
                        res -= entry.getKey() * 0.9;
                        str[j] = str[j].concat(String.valueOf(map.get(entry.getKey() / 10)));
                        str[j] = str[j].concat(String.valueOf(map.get(entry.getKey())));
                        break;
                    } else if ((res % i) / entry.getKey() == 0.8f && res % i != 8*i/10f) {
                        res -= entry.getKey() * 0.8;
                        str[j] = str[j].concat(String.valueOf(map.get(entry.getKey() / 5)));
                        str[j] = str[j].concat(String.valueOf(map.get(entry.getKey())));
                        break;
                    } else if ((res % i) / entry.getKey() >= 1) {
                        res -= entry.getKey();
                        str[j] = str[j].concat(String.valueOf(entry.getValue()));
                        break;
                    }
                }
            }
        }
        for (int i = 2; i >= 0; i--) {
            result = result.concat(str[i]);
        }
        return (result);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean finish = false;
        boolean check;
        while (!finish) {
            System.out.println(" ========Меню========");
            System.out.println(" 1. Калькулятор с арабскими числами");
            System.out.println(" 2. Калькулятор с римскими числами");
            System.out.println(" 3. Выход");
            try {
                int sw = Integer.parseInt(scan.nextLine());
                switch (sw) {
                    case 1:
                        System.out.println(" Введите пример формата 'a + b', где а и b - арабские числа от 1 до 10, \n используемые знаки арифметических операций: +, -, *, /: ");
                        check = false;
                        while (!check) {
                            String s = scan.nextLine();
                            int result = functionToCalculation(s);
                            if (result == 404)
                                System.out.println("Введите пример формата 'a + b', где а и b - арабские числа от 1 до 10, \n используемые знаки арифметических операций: +, -, *, /.  \n Попробуйте снова: ");
                            else {
                                System.out.println("Ответ: " + result);
                                check = true;
                            }
                        }
                        continue;
                case 2:
                    System.out.println(" Введите пример формата 'a + b', где а и b - римские числа от 1 до 10, \n используемые арабские числа: I, V, X. \n используемые знаки арифметических операций: +, -, *, /: ");
                    check = false;
                    while (!check) {
                        String s = scan.nextLine();
                        int result = functionToCalculation(inputRoman(s));
                        if (result == 404)
                            System.out.println(" Введите пример формата 'a + b', где а и b - римские числа от 1 до 10, \n используемые арабские числа: I, V, X. \n используемые знаки арифметических операций: +, -, *, /: \n Попробуйте снова: ");
                        else if (result < 1) {
                            System.out.println(" Ответ меньше I. \n В римских числах нет чисел меньше единицы \n Введите другой пример: ");
                        }
                        else {
                            System.out.println("Ответ: " + outputRoman(result));
                            check = true;
                        }
                    }
                    continue;
                case 3:
                    finish = true;
                default:
                    System.out.println(" Введите цифру 1, 2 или 3 для выбора пункта меню");
            }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(" Введите цифру 1, 2 или 3 для выбора пункта меню");
            }
        }
    }
}
