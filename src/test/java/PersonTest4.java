import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class PersonTest4 {
    private Person a;
    private Person b;

    @Before
    public void create() throws Exception {
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
    public void changeFirstName() throws Exception {
        String[] s = {"Masha", "Nastya", "Olya"};
        a.changeFirstName(2001, "Nastya");
        a.changeFirstName(2011, "Olya");
        a.changeFirstName(1991, "Masha");
        assertTrue(Arrays.equals(s, a.getFirstName().toArray()));
    }

    //changeFirstName, добавляем в историю изменение имени и выбрасываем исключение,
    //когда пытаемся два раза за год изменить имя
    @Test(expected = Exception.class)
    public void changeFirstNameException() throws Exception {

        a.changeFirstName(2001, "Nastya");
        a.changeFirstName(2011, "Olya");
        a.changeFirstName(2001, "Nastya");

    }

    //changeLastName, добавляем в историю изменение фамилии
    @Test(expected = Exception.class)
    public void changeLastNameException() throws Exception {
        a.changeLastName(2001, "Lobova");
        a.changeLastName(2001, "Olonech");
        a.changeFirstName(2011, "Kotova");
        a.changeFirstName(1991, "Li");

    }

    //changeLastName, добавляем в историю изменение фамилии, и выбрасываем исключение,
    //когда пытаемся два раза за год изменить фамилию
    @Test
    public void changeLastName() throws Exception {
        String[] s = {"Li", "Olonech", "Lobova", "Kotova"};
        a.changeLastName(2001, "Lobova");
        a.changeLastName(1998, "Olonech");
        a.changeLastName(2011, "Kotova");
        a.changeLastName(1991, "Li");
        assertTrue(Arrays.equals(s, a.getLastName().toArray()));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда до него было информации
    @Test
    public void getFullNameIncognito() throws Exception {
        assertEquals("Incognito", b.getFullName(1990));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация только о фамилии
    @Test
    public void getFullNameIncognitoFirstName() {
        assertEquals("Li with unknown first name", b.getFullName(1992));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация о смене фамилии несколько раз
    @Test
    public void getFullNameIncognitoFirstName1() {
        assertEquals("Maslenik with unknown first name", b.getFullName(1994));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация только об имени
    @Test
    public void getFullNameIncognitoLastName() throws Exception {
        a.changeFirstName(2000, "Nastya");
        a.changeFirstName(2010, "Olya");
        a.changeLastName(2001, "Lobova");
        assertEquals("Nastya with unknown last name", a.getFullName(2000));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация о смене имени несколько раз
    @Test
    public void getFullNameIncognitoLastName1() throws Exception {
        a.changeFirstName(2020, "Masha");
        a.changeFirstName(2000, "Nastya");
        a.changeFirstName(2010, "Olya");
        a.changeLastName(2012, "Lobova");
        assertEquals("Olya with unknown last name", a.getFullName(2011));
    }

    //getFullName, запрашиваем имя и фамилию, на год, когда была информация о смене имени несколько раз
    @Test
    public void getFullName() throws Exception {
        assertEquals("Marina Loboda", b.getFullName(1996));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год, до которого не было информации
    @Test
    public void getFullNameWithHistoryIncognito() {
        assertEquals("Incognito", b.getFullNameWithHistory(1990));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 1 смена фамилии
    @Test
    public void getFullNameWithHistoryLastNames() {
        assertEquals("Li with unknown first name", b.getFullNameWithHistory(1992));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 2 изменения фамилии
    @Test
    public void getFullNameWithHistoryLastNames1() {
        assertEquals("Maslenik(Li) with unknown first name", b.getFullNameWithHistory(1993));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений фамилии
    @Test
    public void getFullNameWithHistoryLastNames2() {
        assertEquals("Loboda(Maslenik, Li) with unknown first name", b.getFullNameWithHistory(1995));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 1 смена имени
    @Test
    public void getFullNameWithHistoryFirstNames() throws Exception {
        a.changeFirstName(2020, "Masha");
        a.changeFirstName(2000, "Nastya");
        a.changeFirstName(2010, "Olya");
        a.changeLastName(2012, "Lobova");
        assertEquals("Nastya with unknown last name", a.getFullNameWithHistory(2002));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 2 смены имени
    @Test
    public void getFullNameWithHistoryFirstNames1() throws Exception {
        a.changeFirstName(2020, "Masha");
        a.changeFirstName(2000, "Nastya");
        a.changeFirstName(2010, "Olya");
        a.changeLastName(2012, "Lobova");
        assertEquals("Olya(Nastya) with unknown last name", a.getFullNameWithHistory(2011));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений фамилии
    @Test
    public void getFullNameWithHistoryFirstNames2() throws Exception {
        a.changeFirstName(2020, "Masha");
        a.changeFirstName(2000, "Nastya");
        a.changeFirstName(2010, "Olya");
        a.changeLastName(2032, "Lobova");
        assertEquals("Masha(Olya, Nastya) with unknown last name", a.getFullNameWithHistory(2022));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была только 1 смена имени и несколько изменений фамилии
    @Test
    public void getFullNameWithHistoryNames() {
        assertEquals("Marina Loboda(Maslenik, Li)", b.getFullNameWithHistory(1997));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений имени и несколько изменений фамилии
    @Test
    public void getFullNameWithHistoryNames1() {
        String s = "Nastya(Marina) Lobova(Olonech, Loboda, Maslenik, Li)";
        assertEquals(s, b.getFullNameWithHistory(2002));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений имени и несколько изменений фамилии
    //причем два раза подряд меняли имя на одно и тоже
    @Test
    public void getFullNameWithHistoryNames2() {
        String s = "Nastya(Marina) Lobova(Olonech, Loboda, Maslenik, Li)";
        assertEquals(s, b.getFullNameWithHistory(2007));
    }

    //getFullNameWithHistory, запрашиваем полную информацию об имени и фамилии на год,
    // до которого была несколько изменений имени и несколько изменений фамилии
    //причем два раза подряд меняли имя и фамилию на одно и тоже
    @Test
    public void getFullNameWithHistoryNames4() {
        String s = "Olya(Nastya, Marina) Lobova(Olonech, Loboda, Maslenik, Li)";
        assertEquals(s, b.getFullNameWithHistory(2030));
    }
}
