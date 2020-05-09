package agenda.test;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Contact;
import agenda.model.repository.classes.RepositoryContactMock;
import agenda.model.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ContactF01BBT
{
    private Contact con;
    private RepositoryContact rep;

    @Before
    public void setUp() throws Exception
    {
        rep = new RepositoryContactMock();
    }

    @Test
    public void testF01ECPV1()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("F");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setTelefon("ABCDEFGHIJLKMNO");
            assertTrue(false);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }

        rep.addContact(toAdd);
        Contact c = rep.getByName("F");
        if (c.getName().equals("F"))
        {
            assertTrue(true);
        }
        if (c.getTelefon().equals("ABCDEFGHIJLKMNO")){

            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }

    }
    @Test
    public void testF01ECPV2()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("0");
            assertTrue(true);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setTelefon("44");
            assertTrue(false);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }


        rep.addContact(toAdd);
        Contact c = rep.getByName("0");
        if (c.getName().equals("0"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
        if (c.getTelefon().equals("44")){

            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
    @Test
    public void testF01ECPV3()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("A.B.C");
            assertTrue(false);
        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }
        try
        {
            toAdd.setTelefon("+40");
            assertTrue(true);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }


        rep.addContact(toAdd);
        Contact c = rep.getByName("A.B.C");
        if (c == null)
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }
    @Test
    public void testF01ECPV4()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("A.B");
            assertTrue(true);
        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setTelefon("0746309058");
            assertTrue(true);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }

        rep.addContact(toAdd);
        Contact c = rep.getByName("A.B");
        if (c.getName().equals("A.B"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
        if (c.getTelefon().equals("0746309058"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }
    @Test
    public void testF01BVA1()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("0123456");
            assertTrue(true);
        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setTelefon("0123456");
            assertTrue(true);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }


        rep.addContact(toAdd);
        Contact c = rep.getByName("0123456");
        if (c.getName().equals("0123456"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
        if (c.getTelefon().equals("0123456"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }
    @Test
    public void testF01BVA2()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("0123456");
            assertTrue(true);
        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setTelefon("1234560");
            assertTrue(false);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }


        rep.addContact(toAdd);
        Contact c = rep.getByName("0123456");
        if (c.getName().equals("0123456"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
        if (c.getTelefon().equals("123456"))
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
    @Test
    public void testF01BVA3()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("A.B.C");
            assertTrue(false);
        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }
        try
        {
            toAdd.setTelefon("1234560");
            assertTrue(false);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }


        rep.addContact(toAdd);
        Contact c = rep.getByName("A.B.C");
        if (c != null)
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
    @Test
    public void testF01BVA4()
    {
        Contact toAdd = new Contact();
        try
        {
            toAdd.setAddress("Luceafar");

        }
        catch (InvalidFormatException e)
        {
            assertTrue(false);
        }
        try
        {
            toAdd.setName("1.2.3");
            assertTrue(false);
        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }
        try
        {
            toAdd.setTelefon("1234560");
            assertTrue(false);

        }
        catch (InvalidFormatException e)
        {
            assertTrue(true);
        }


        rep.addContact(toAdd);
        Contact c = rep.getByName("1.2.3");
        if (c != null)
        {
            assertTrue(false);
        }
        else
        {
            assertTrue(true);
        }
    }
}
