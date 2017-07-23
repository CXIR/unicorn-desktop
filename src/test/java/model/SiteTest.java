package model;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiteTest {
    Site site = new Site(1,"Montparnasse","26 Avenue de Bretagne","Paris","75014");

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        Assert.assertEquals(1,site.getId());
        System.out.println("Site ID correctly recovered");
    }


    @Test
    public void setId() throws Exception {
        site.setId(3);

        Assert.assertEquals(3,site.getId());
        System.out.println("Site ID correctly modified");
    }

    @Test
    public void getName() throws Exception {
        String name = site.getName();

        assertTrue("Site name recover failed",name.equals("Montparnasse"));
        System.out.println("Site name correctly recovered");
    }

    @Test
    public void setName() throws Exception {
        site.setName("Paris Est");
        String name = site.getName();

        assertTrue("Site name modification failed",name.equals("Paris Est"));
        System.out.println("Site name correctly modified");
    }

    @Test
    public void getAdress() throws Exception {
        String address = site.getAdress();

        assertTrue("Site address recover failed",address.equals("26 Avenue de Bretagne"));
        System.out.println("Site address correctly recovered");
    }

    @Test
    public void setAdress() throws Exception {
        site.setAdress("28 Boulevard de Bretagne");
        String address = site.getAdress();

        assertTrue("Site address modification failed",address.equals("28 Boulevard de Bretagne"));
        System.out.println("Site address correctly modified");
    }

    @Test
    public void getCity() throws Exception {
        String city = site.getCity();

        assertTrue("Site city recover failed",city.equals("Paris"));
        System.out.println("Site city correctly recovered");
    }

    @Test
    public void setCity() throws Exception {
        site.setCity("Montrouge");
        String city = site.getCity();

        assertTrue("Site city modification failed",city.equals("Montrouge"));
        System.out.println("Site city correctly modified");
    }

    @Test
    public void getPostalCode() throws Exception {
        String postal = site.getPostalCode();

        assertTrue("Site postal code recover failed",postal.equals("75014"));
        System.out.println("Site postal code correctly recovered");
    }

    @Test
    public void setPostalCode() throws Exception {
        site.setPostalCode("92200");
        String postal = site.getPostalCode();

        assertTrue("Site postal code modification failed", postal.equals("92200"));
        System.out.println("Site postal code correctly modified");
    }

    @Test
    public void setInvalid() throws Exception {
        site.setInvalid(false);

        assertTrue("Site valid modification failed",!site.isInvalid());
        System.out.println("Site valid correctly modified");
    }

    @Test
    public void isInvalid() throws Exception {
        assertTrue("Site valid recover failed",!site.isInvalid());
        System.out.println("Site valid correctely recovered");
    }

    @Test
    public void getSite() throws Exception {
        Site expected_site = new Site(1,"Campus Etoile","120 Avenue de Wagram","Paris","75017");
        Site received_site = expected_site.getSite();
        boolean same = false;

        if(expected_site.toString().equals(received_site.toString())) same = true;
        assertTrue("Site comparison from DB failed",same);
        System.out.println("Site comparison from DB correctly done");
    }

    @Test
    public void changeSite() throws Exception {

    }

    @Test
    public void jsonSite() throws Exception {
        String json = "{\"city\":\"Paris\",\"name\":\"Montparnasse\",\"adress\":\"26 Avenue de Bretagne\",\"id\":\"1\",\"postal\":\"75014\"}";
        JSONObject convert = site.jsonSite();

        assertTrue("Site json conversion failed",convert.toString().equals(json));
        System.out.println("Site json correctly converted");
    }

    @Test
    public void createSite() throws Exception {

    }

    @Test
    public void deleteSite() throws Exception {

    }

}