package com.example.identificadorderaca;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RacaTest {


    @Test
    public void testeConstrutor(){

        String ra = "batata";
        String[] k = {"frita","cozida"};
        Raca r = new Raca("batata",k);

        Assert.assertEquals("batata", r.getRaca());

    }

}