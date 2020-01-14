package com.mypayroll.app;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model m=new Model();

    @Test
    void cheak() throws SQLException
    {
        m.setE_NAME("Hameed Khan");
        m.setE_CODE("VET20179");
        boolean s=m.cheak();
        assertTrue(s);
    }

    @Test
    void ctcCheak()
    {
        m.setE_CODE("VET20179");
        m.setCTC(19967);
        boolean s=m.ctcCheak();
        assertTrue(s);

    }



}