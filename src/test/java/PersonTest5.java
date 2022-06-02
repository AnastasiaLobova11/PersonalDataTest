import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PersonTest5 {
    private Person a;
    private Person b;

    @BeforeEach
    public  void create() throws Exception {
        a = new Person();
        b = new Person();
        b.changeFirstName(2001, "Nastya");
        b.changeFirstName(2011, "Olya");
        b.changeFirstName(2005, "Nastya");
        b.changeFirstName(1996, "Marina");
        b.changeLastName(2001, "Lobova");
        b.changeLastName(1998, "Olonech");
        b.changeLastName(2021, "Lobova");
        b.changeLastName(1991, "Li");
        b.changeLastName(1993, "Maslenik");
        b.changeLastName(1995, "Loboda");

    }

    //changeFirstName, добавляем в историю изменение имени
    @Test
    void changeFirstName() throws Exception {
        String[] s ={"Masha","Nastya", "Olya"};
        a.changeFirstName(2001, "Nastya");
        a.changeFirstName(2011, "Olya");
        a.changeFirstName(1991, "Masha");
        Assertions.assertTrue(Arrays.equals(s, a.getFirstName().toArray()));
    }

    //changeFirstName, добавляем в историю изменение имени и выбрасываем исключение,
    //когда пытаемся два раза за год изменить имя
    @Test
    void changeFirstNameException() {
        Assertions.assertThrows(Exception.class, () -> {
            a.changeFirstName(2001, "Nastya");
            a.changeFirstName(2011, "Olya");
            a.changeFirstName(2001, "Nastya");
        });
    }

    //changeLastName, добавляем в историю изменение фамилии
    @Test
    void changeLastNameException() {
        Assertions.assertThrows(Exception.class, () -> {
            a.changeLastName(2001, "Lobova");
            a.changeLastName(2001, "Olonech");
            a.changeFirstName(2011, "Kotova");
            a.changeFirstName(1991, "Li");
        });
    }

    //changeLastName, добавляем в историю изменение фамилии, и выбрасываем исключение,
    //когда пытаемся два раза за год изменить фамилию
    @Test
    void changeLastName() throws Exception {
        String[] s= {"Li","Olonech","Lobova","Kotova"};
        a.changeLastName(2001, "Lobova");
        a.changeLastName(1998, "Olonech");
        a.changeLastName(2011, "Kotova");
        a.changeLastName(1991, "Li");
        Assertions.assertTrue(Arrays.equals(s, a.getLastName().toArray()));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда до него было информации
    @Test
    void getFullNameIncognito() throws Exception {
        Assertions.assertEquals("Incognito", b.getFullName(1990));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация только о фамилии
    @Test
    void getFullNameIncognitoFirstName() {
        Assertions.assertEquals("Li with unknown first name", b.getFullName(1992));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация о смене фамилии несколько раз
    @Test
    void getFullNameIncognitoFirstName1() {
        Assertions.assertEquals("Maslenik with unknown first name", b.getFullName(1994));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация только об имени
    @Test
    void getFullNameIncognitoLastName() throws Exception {
        a.changeFirstName(2000,"Nastya");
        a.changeFirstName(2010,"Olya");
        a.changeLastName(2001,"Lobova");
        Assertions.assertEquals("Nastya with unknown last name", a.getFullName(2000));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация о смене имени несколько раз
    @Test
    void getFullNameIncognitoLastName1() throws Exception {
        a.changeFirstName(2020,"Masha");
        a.changeFirstName(2000,"Nastya");
        a.changeFirstName(2010,"Olya");
        a.changeLastName(2012,"Lobova");
        Assertions.assertEquals("Olya with unknown last name", a.getFullName(2011));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация о смене имени несколько раз
    @Test
    void getFullName() throws Exception {
        Assertions.assertEquals("Marina Loboda", b.getFullName(1996));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год, до которого не было информации
    @Test
    void getFullNameWithHistoryIncognito(){
        Assertions.assertEquals("Incognito", b.getFullNameWithHistory(1990));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 1 смена фамилии
    @Test
    void getFullNameWithHistoryLastNames(){
        Assertions.assertEquals("Li with unknown first name", b.getFullNameWithHistory(1992));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 2 изменения фамилии
    @Test
    void getFullNameWithHistoryLastNames1(){
        Assertions.assertEquals("Maslenik(Li) with unknown first name", b.getFullNameWithHistory(1993));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений фамилии
    @Test
    void getFullNameWithHistoryLastNames2(){
        Assertions.assertEquals("Loboda(Maslenik, Li) with unknown first name", b.getFullNameWithHistory(1995));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 1 смена имени
    @Test
    void getFullNameWithHistoryFirstNames() throws Exception {
        a.changeFirstName(2020,"Masha");
        a.changeFirstName(2000,"Nastya");
        a.changeFirstName(2010,"Olya");
        a.changeLastName(2012,"Lobova");
        Assertions.assertEquals("Nastya with unknown last name", a.getFullNameWithHistory(2002));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 2 смены имени
    @Test
    void getFullNameWithHistoryFirstNames1() throws Exception {
        a.changeFirstName(2020,"Masha");
        a.changeFirstName(2000,"Nastya");
        a.changeFirstName(2010,"Olya");
        a.changeLastName(2012,"Lobova");
        Assertions.assertEquals("Olya(Nastya) with unknown last name", a.getFullNameWithHistory(2011));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений фамилии
    @Test
    void getFullNameWithHistoryFirstNames2() throws Exception {
        a.changeFirstName(2020,"Masha");
        a.changeFirstName(2000,"Nastya");
        a.changeFirstName(2010,"Olya");
        a.changeLastName(2032,"Lobova");
        Assertions.assertEquals("Masha(Olya, Nastya) with unknown last name", a.getFullNameWithHistory(2022));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 1 смена имени и несколько изменений фамилии
    @Test
    void getFullNameWithHistoryNames(){
        Assertions.assertEquals("Marina Loboda(Maslenik, Li)", b.getFullNameWithHistory(1997));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений имени и несколько изменений фамилии
    @Test
    void getFullNameWithHistoryNames1(){
        String s ="Nastya(Marina) Lobova(Olonech, Loboda, Maslenik, Li)";
        Assertions.assertEquals(s, b.getFullNameWithHistory(2002));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений имени и несколько изменений фамилии
    //причем два раза подряд меняли имя на одно и тоже
    @Test
    void getFullNameWithHistoryNames2(){
        String s ="Nastya(Marina) Lobova(Olonech, Loboda, Maslenik, Li)";
        Assertions.assertEquals(s, b.getFullNameWithHistory(2007));
    }
    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений имени и несколько изменений фамилии
    //причем два раза подряд меняли имя и фамилию на одно и тоже
    @Test
    void getFullNameWithHistoryNames4(){
        String s ="Olya(Nastya, Marina) Lobova(Olonech, Loboda, Maslenik, Li)";
        Assertions.assertEquals(s, b.getFullNameWithHistory(2030));
    }
}