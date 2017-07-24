package model;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiteTest {

    /** Local Site Creation first*/
    Site site = new Site(1,"Nord","4 Grand Place","Lille","59000");

    @Test
    public void should_get_id() throws Exception {
        Assert.assertEquals(1,site.getId());
        System.out.println("Site ID correctly recovered");
    }


    @Test
    public void should_set_id() throws Exception {
        site.setId(3);

        Assert.assertEquals(3,site.getId());
        System.out.println("Site ID correctly modified");
    }

    @Test
    public void should_get_name() throws Exception {
        String name = site.getName();

        assertTrue("Site name recover failed",name.equals("Nord"));
        System.out.println("Site name correctly recovered");
    }

    @Test
    public void should_set_name() throws Exception {
        site.setName("Nord Europe");
        String name = site.getName();

        assertTrue("Site name modification failed",name.equals("Nord Europe"));
        System.out.println("Site name correctly modified");
    }

    @Test
    public void should_get_address() throws Exception {
        String address = site.getAdress();

        assertTrue("Site address recover failed",address.equals("4 Grand Place"));
        System.out.println("Site address correctly recovered");
    }

    @Test
    public void should_set_address() throws Exception {
        site.setAdress("11 Rue de la Monnaie");
        String address = site.getAdress();

        assertTrue("Site address modification failed",address.equals("11 Rue de la Monnaie"));
        System.out.println("Site address correctly modified");
    }

    @Test
    public void should_get_city() throws Exception {
        String city = site.getCity();

        assertTrue("Site city recover failed",city.equals("Lille"));
        System.out.println("Site city correctly recovered");
    }

    @Test
    public void should_set_city() throws Exception {
        site.setCity("Lambersart");
        String city = site.getCity();

        assertTrue("Site city modification failed",city.equals("Lambersart"));
        System.out.println("Site city correctly modified");
    }

    @Test
    public void should_get_postal() throws Exception {
        String postal = site.getPostalCode();

        assertTrue("Site postal code recover failed",postal.equals("59000"));
        System.out.println("Site postal code correctly recovered");
    }

    @Test
    public void should_set_postal() throws Exception {
        site.setPostalCode("59100");
        String postal = site.getPostalCode();

        assertTrue("Site postal code modification failed", postal.equals("59100"));
        System.out.println("Site postal code correctly modified");
    }

    @Test
    public void should_set_invalid() throws Exception {
        site.setInvalid(false);

        assertTrue("Site valid modification failed",!site.isInvalid());
        System.out.println("Site valid correctly modified");
    }

    @Test
    public void should_get_invalid() throws Exception {
        assertTrue("Site valid recover failed",!site.isInvalid());
        System.out.println("Site valid correctely recovered");
    }

    @Test
    public void should_output_json() throws Exception {
        String json = "{\"city\":\"Lille\",\"name\":\"Nord\",\"adress\":\"4 Grand Place\",\"id\":\"1\",\"postal\":\"59000\"}";
        JSONObject convert = site.jsonSite();

        assertTrue("Site json conversion failed",convert.toString().equals(json));
        System.out.println("Site json correctly converted");
    }

    @Test
    public void should_create_site() throws Exception {
        boolean isInserted = false;
        Site toCreate = new Site(1,"Nord","4 Grand Place","Lille","59000");
        toCreate.createSite();

        for(Site registered : toCreate.getSites()) {
            if(registered.toString().equals(toCreate.toString())) {
                isInserted = true;
                toCreate.setId(registered.getId());
                break;
            }
        }

        toCreate.deleteSite();

        assertTrue("Site insertion failed",isInserted);
        System.out.println("Site correctly inserted");
    }

    @Test
    public void should_get_site() throws Exception {
        boolean same = false;
        Site toGet = new Site(1,"Nord","4 Grand Place","Lille","59000");
        toGet.createSite();

        for(Site registered : toGet.getSites()) {
            if(registered.toString().equals(site.toString())) {
                same = true;
                toGet.setId(registered.getId());
                break;
            }
        }

        toGet.deleteSite();

        assertTrue("Site comparison from DB failed",same);
        System.out.println("Site comparison from DB correctly done");
    }

    @Test
    public void should_change_site() throws Exception {
        boolean isModified = false;

        Site toUpdate = new Site(1,"Nord","4 Grand Place","Lille","59000");
        toUpdate.createSite();



        for(Site registered : toUpdate.getSites()) {
            if(registered.toString().equals(toUpdate.toString())) {
                toUpdate.setId(registered.getId());
                toUpdate.setName("Grand Nord");
                toUpdate.changeSite();
                break;
            }
        }

        for(Site registered : toUpdate.getSites()){
            if(registered.toString().equals(toUpdate.toString())){
                isModified = true;
                break;
            }
        }

        toUpdate.deleteSite();

        assertTrue("Site modification in DB failed",isModified);
        System.out.println("Site correctly modified in DB");
    }



}