package agenda.test;

import agenda.model.base.Activity;
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

public class F03BBT
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
        writeToBaos("03/20/2013");

        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("Redirecting System.out to BAOS");
        PrintStream baosPS = new PrintStream(baos);
        System.setOut(baosPS);
        MainClass.afisActivitate(ra,getReader(),user);
        System.setOut(console);
        System.out.println("Redirecting System.out to CONSOLE");
        String result = new String(baos.toByteArray());
        try
        {
            baos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (result.contains("description1") && result.contains("description2"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }
    @Test
    public void testInstr2()
    {

        buildBufferedReader();
        writeToBaos("02/29/2013");

        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("Redirecting System.out to BAOS");
        PrintStream baosPS = new PrintStream(baos);
        System.setOut(baosPS);
        MainClass.afisActivitate(ra,getReader(),user);
        System.setOut(console);
        System.out.println("Redirecting System.out to CONSOLE");
        String result = new String(baos.toByteArray());
        try
        {
            baos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (result.contains("Fri Mar 01"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
        if (result.contains("description1") && result.contains("description2"))
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
}
