package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> template(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> logindata(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"marinas@gmail.com", "Mmarina12345$"});
        list.add(new Object[]{"noa@gmail.com", "Nnoa12345$"});
        list.add(new Object[]{"marinas@gmail.com", "Mmarina12345$"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> logindataModel(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User().withEmail("marinas@gmail.com").withPassword("Mmarina12345$")});
        list.add(new Object[]{new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$")});
        list.add(new Object[]{new User().withEmail("marinas@gmail.com").withPassword("Mmarina12345$")});

        return list.iterator();
    }
}
