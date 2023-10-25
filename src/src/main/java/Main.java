import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       StringBuilder sb = new StringBuilder(newMan());
       String[] line =sb.toString().split(" ");
       String fileName = line[0]+".txt";
       writeFile(sb,fileName);
    }

    private static Date parseDate(String line) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(line);
    }
    private static String fomatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }
    public static StringBuilder newMan(){
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные(в формате: Фамилия Имя Отчество, Дата рождения, Номер телефона, Пол.\n" +
                "через пробел, без запятых):");
        String input = scanner.nextLine();
        try{
            String[] lines = input.split(" ");
            if(lines.length != 6){
                throw new IllegalArgumentException("Кол-во аргументов введено не верно!");
            }
            String lastName = lines[0];
            sb.append(lastName);
            sb.append(" ");
            String firstName = lines[1];
            sb.append(firstName);
            sb.append(" ");
            String middleName = lines[2];
            sb.append(middleName);
            sb.append(" ");
            Date dateOfBirth = parseDate(lines[3]);
            sb.append(fomatDate(dateOfBirth));
            sb.append(" ");
            int phoneNumber = Integer.parseInt(lines[4]);
            sb.append(phoneNumber);
            sb.append(" ");
            String gender;

            if(lines[5].equals("M")| lines[5].equals("F")){
                gender = lines[5];
            } else{
                throw new GenderFormatException("Гендер указан не верно(Может быть либо M, либо F)");
            }
            sb.append(gender);

            System.out.println(lastName);
            System.out.println(firstName);
            System.out.println(middleName);
            System.out.println(fomatDate(dateOfBirth));
            System.out.println(phoneNumber);
            System.out.println(gender);
        } catch (NumberFormatException e){
            System.out.println("Неверный номер телефона");
        } catch (IllegalArgumentException e){
            System.out.println("Ошибка: " + e.getMessage());
        }catch (ParseException e){
            System.out.println("Ошибка: Неверный формат даты");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка: Недостаточно данных");
        }catch (GenderFormatException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        return sb;
    }
    static void writeFile(StringBuilder sb, String fileName){
        try(FileWriter fw = new FileWriter(fileName,true)){
            fw.write(sb.toString());
            fw.write("\n");
        }catch (IOException e){
            e.getMessage();
        }
    }
}
