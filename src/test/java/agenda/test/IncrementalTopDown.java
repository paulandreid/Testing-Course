package agenda.test;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.classes.RepositoryUserFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.startApp.MainClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class IncrementalTopDown
{
    RepositoryActivity ra;
    RepositoryContact rc;
    BufferedReader br;
    ByteArrayOutputStream baos;
    User user;
    ContactF01BBT contactF01BBT;
    ActF02WBT actF02WBT;
    F03BBT f03BBT;
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
        contactF01BBT = new ContactF01BBT();
        actF02WBT = new ActF02WBT();
        f03BBT = new F03BBT();
    }

    @Test
    public void testF01()
    {
        try
        {
            contactF01BBT.setUp();
        }
        catch (Exception e)
        {
            assertTrue(false);
        }

        contactF01BBT.testF01BVA1();
        contactF01BBT.testF01BVA2();
        contactF01BBT.testF01BVA3();
        contactF01BBT.testF01BVA4();
        contactF01BBT.testF01ECPV1();
        contactF01BBT.testF01ECPV2();
        contactF01BBT.testF01ECPV3();
        contactF01BBT.testF01ECPV4();
    }
    @Test
    public void testF02()
    {
        try
        {
            actF02WBT.setUp();
        }
        catch(Exception e)
        {
            assertTrue(false);
        }

        actF02WBT.testInstr();
        actF02WBT.testLoops();
        actF02WBT.testDecisions();
    }
    @Test
    public void testF03()
    {
        try
        {
            f03BBT.setUp();
        }
        catch(Exception e)
        {
            assertTrue(false);
        }

        f03BBT.testInstr();
        f03BBT.testInstr2();
    }
    @Test
    public void PA()
    {
        buildBufferedReader();
        writeToBaos("username1");
        writeToBaos("pass1");
        writeToBaos("1");
        writeToBaos("Gorila");
        writeToBaos("Joe");
        writeToBaos("+4032");
        writeToBaos("4");

        InputStream targetStream = new ByteArrayInputStream(baos.toByteArray());
        System.setIn(targetStream);

        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("Redirecting System.out to BAOS");
        PrintStream baosPS = new PrintStream(baos);
        System.setOut(baosPS);
        MainClass.main(null);
        System.setOut(console);
        System.out.println("Redirecting System.out to CONSOLE");
        String result = new String(baos.toByteArray());
        if (result.contains("S-a adugat cu succes"))
        {
            Contact foundContact;
            assertTrue((foundContact = MainClass.contactRep.getByName("Gorila"))!=null);
            assertTrue(foundContact.getAddress().equals("Joe"));
            assertTrue(foundContact.getTelefon().equals("+4032"));
        }
        else
        {
            assertTrue(false);
        }
    }
    @Test
    public void PAB()
    {
        buildBufferedReader();
        writeToBaos("username1");
        writeToBaos("pass1");
        writeToBaos("1");
        writeToBaos("Gorila");
        writeToBaos("Joe");
        writeToBaos("+4032");
        writeToBaos("2");
        writeToBaos("Quack");
        writeToBaos("03/20/2019");
        writeToBaos("10:00");
        writeToBaos("03/20/2019");
        writeToBaos("11:00");
        writeToBaos("Gorila");
        writeToBaos("");
        writeToBaos("4");

        InputStream targetStream = new ByteArrayInputStream(baos.toByteArray());
        System.setIn(targetStream);

        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("Redirecting System.out to BAOS");
        PrintStream baosPS = new PrintStream(baos);
        System.setOut(baosPS);
        MainClass.main(null);
        System.setOut(console);
        System.out.println("Redirecting System.out to CONSOLE");
        String result = new String(baos.toByteArray());

        List<Activity> a = MainClass.activityRep.getActivities();
        boolean found = false;
        for (Activity a2:a)
        {
            if (a2.getDescription().equals("Quack"))
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
    public void PABC()
    {
        buildBufferedReader();
        writeToBaos("username1");
        writeToBaos("pass1");
        writeToBaos("1");
        writeToBaos("Gorila");
        writeToBaos("Joe");
        writeToBaos("+4032");
        writeToBaos("2");
        writeToBaos("Quack");
        writeToBaos("03/20/2019");
        writeToBaos("10:00");
        writeToBaos("03/20/2019");
        writeToBaos("11:00");
        writeToBaos("Gorila");
        writeToBaos("");
        writeToBaos("3");
        writeToBaos("03/20/2019");
        writeToBaos("4");

        InputStream targetStream = new ByteArrayInputStream(baos.toByteArray());
        System.setIn(targetStream);

        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("Redirecting System.out to BAOS");
        PrintStream baosPS = new PrintStream(baos);
        System.setOut(baosPS);
        MainClass.main(null);
        System.setOut(console);
        System.out.println("Redirecting System.out to CONSOLE");
        String result = new String(baos.toByteArray());
        if (result.contains("Quack with: Gorila"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }
}
