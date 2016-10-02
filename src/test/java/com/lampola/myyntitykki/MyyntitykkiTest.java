package com.lampola.myyntitykki;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.lampola.myyntitykki.domain.Henkilo;
import com.lampola.myyntitykki.repository.HenkiloRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Markus
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyyntitykkiTest {

    @Autowired
    HenkiloRepository henkiloRepository;
            
    public MyyntitykkiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHenkilo() {
        String nimi = "Albert Edelfeldt";
        String username = "aedelfeldt";
        
        Henkilo henkilo = new Henkilo();
        henkilo.setUsername(username);
        henkilo.setPassword("ae");
        henkilo.setNimi(nimi);
        henkilo.setKatuosoite("Aleksanterinkatu 13");
        henkilo.setKaupunki("Helsinki");

        henkiloRepository.save(henkilo);
        
        Henkilo dbHenkilo = henkiloRepository.findByUsername(username);
        assertNotNull(dbHenkilo);
        assertEquals(nimi, dbHenkilo.getNimi());
        assertEquals(username, dbHenkilo.getUsername());
    }
}
