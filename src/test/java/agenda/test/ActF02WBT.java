package agenda.test;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.classes.RepositoryContactMock;
import agenda.model.repository.classes.RepositoryUserFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.model.repository.interfaces.RepositoryUser;
import agenda.startApp.MainClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ActF02WBT
{
    RepositoryActivity ra;
    RepositoryContact rc;
    BufferedReader br;
    ByteArrayOutputStream baos;
    User user;
    public void buildBufferedReader()
    {
        if (baos!=null)
        {
            try
            {
                baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        baos = new ByteArrayOutputStream();
    }
    public void writeToBaos(String toWrite)
    {
        try
        {
            baos.write((toWrite+"\n").getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public BufferedReader getReader()
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        return new BufferedReader(new InputStreamReader(bais));
    }
    @Before
    public void setUp() throws Exception
    {
        rc = new RepositoryContactFile();
        RepositoryUserFile ru = new RepositoryUserFile();
        ra = new RepositoryActivityFile(rc);
        user = ru.getByUsername("username1");
    }

    @Test
    public void testInstr()
    {
        buildBufferedReader();
        writeToBaos("test");
        writeToBaos("10/10/2019");
        writeToBaos("11:11");
        writeToBaos("10/10/2019");
        writeToBaos("11:12");
        writeToBaos("name2");
        writeToBaos("");
        MainClass.adaugActivitate(ra,rc,getReader(),user);

        List<Activity> a = ra.activitiesByName("name1");
        boolean found = false;
        for (Activity a2:a)
        {
            if (a2.getDescription().equals("test"))
            {
                found = true;
                if (a2.getContacts().size() == 1)
                {
                    assertTrue(true);
                }
                else
                {
                    assertTrue(false);
                }
            }
        }
        if (!found)
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
    @Test
    public void testInstr2()//Invalid username
    {
        buildBufferedReader();
        writeToBaos("test");
        writeToBaos("10/10/2019");
        writeToBaos("11:11");
        writeToBaos("10/10/2019");
        writeToBaos("11:12");
        writeToBaos("nam34");
        writeToBaos("");
        MainClass.adaugActivitate(ra,rc,getReader(),user);

        List<Activity> a = ra.activitiesByName("name1");
        boolean found = false;
        for (Activity a2:a)
        {
            if (a2.getDescription().equals("test"))
            {
                found = true;
                if (a2.getContacts().size() == 0)
                {
                    assertTrue(true);
                }
                else
                {
                    assertTrue(false);
                }
            }
        }
        if (!found)
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
    @Test
    public void testLoops()//Invalid user test
    {
        buildBufferedReader();
        writeToBaos("test");
        writeToBaos("10/10/2019");
        writeToBaos("11:11");
        writeToBaos("10/10/2019");
        writeToBaos("11:12");
        writeToBaos("nam34");
        writeToBaos("nam34");
        writeToBaos("nam34");
        writeToBaos("nam34");
        writeToBaos("name2");
        System.out.println(new String(baos.toByteArray()).replace("\n","\\n"));
        writeToBaos("");
        MainClass.adaugActivitate(ra,rc,getReader(),user);


        List<Activity> a = ra.activitiesByName("name1");
        boolean found = false;
        for (Activity a2:a)
        {
            if (a2.getDescription().equals("test"))
            {
                found = true;
                if (a2.getContacts().size() == 1)
                {
                    assertTrue(true);
                }
                else
                {
                    assertTrue(false);
                }
            }
        }
        if (!found)
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
    @Test
    public void testDecisions() //Conflicts especially
    {
        buildBufferedReader();
        writeToBaos("test");
        writeToBaos("10/10/2019");
        writeToBaos("11:11");
        writeToBaos("10/10/2019");
        writeToBaos("11:12");
        writeToBaos("name3");
        writeToBaos("");
        MainClass.adaugActivitate(ra,rc,getReader(),user);
        MainClass.adaugActivitate(ra,rc,getReader(),user);


        List<Activity> a = ra.activitiesByName("name1");
        boolean found = false;
        int ok = 0;
        for (Activity a2:a)
        {
            if (a2.getDescription().equals("test"))
            {
                found = true;
                ok ++;
            }
        }
        if (!found)
        {
            assertTrue(false);

        }
        else
        {
            assertTrue(true);
            if (ok == 1)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
        }
    }
}
