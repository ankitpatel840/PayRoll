package com.mypayroll.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;


public  class ControllerTest
{
    Controller c=new Controller();
    int minimumwage;
    int ctc;
    String state="BANGALORE";
    int basic;
    int employerPf;
    @BeforeEach
    void setUp()
    {

         minimumwage=12244;
         ctc=19967;
         // basic=12244;
          employerPf=0;


    }

    @Test
    void basicCalculation()
    {
        int d=Controller.basicCalculation(ctc,minimumwage);
        basic=d;
        System.out.println(basic);
        assertEquals("True",12244,d);
    }

    @Test
    void bonusCalulation()
    {

      int d=c.bonusCalulation(Controller.basicCalculation(ctc,minimumwage));
      assertEquals("True",1020,d);

    }

    @Test
    void employePfCalculation()
    {
        int d=c.employePfCalculation(Controller.basicCalculation(ctc,minimumwage));
        assertEquals("True",1469,d);
    }

    @Test
    void gratutityCalculation()
    {
           int d=Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage));
           assertEquals("true",589,d);
    }

    @Test
    void grossCalculation()
    {
         int d=Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage)));
         assertEquals("true",17097,d);
    }

    @Test
    void employerEsiCalculation()
    {
       int d=Controller.employeeEsiCalculation(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))));
       assertEquals("True",299,d);
    }

    @Test
    void employeePf()
    {
        int d= (int) Controller.employeePf(Controller.basicCalculation(ctc,minimumwage));
        assertEquals("True",1469,d);
    }

    @Test
    void employeeEsiCalculation()
    {
        int d=Controller.employerEsiCalculation(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))));
        assertEquals("True",812,d);
    }

    @Test
    void grossAndDeductionCalculation()
    {
       int d=Controller.grossAndDeductionCalculation(Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage)));
       assertEquals("True",1497,d);
    }

    @Test
    void netPayCalucaltion()
    {
    int d=Controller.netPayCalucaltion(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage)),Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))));
    assertEquals("Ture",15600,d);
    }

    @Test
    void netTakeHomeCalculation()
    {
        int d=Controller.netTakeHomeCalculation(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.grossAndDeductionCalculation(Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage))));
        assertEquals("True",15600,d);
    }

    @Test
    void ptGrossCalculation()
    {
      int d=Controller.ptGrossCalculation(Controller.netPayCalucaltion(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage)),Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage)))),Controller.grossAndDeductionCalculation(Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage))));
      assertEquals("True",17097,d);
    }

    @Test
    void differneceCalculation()
    {
       int d=Controller.differneceCalculation(Controller.netPayCalucaltion(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage)),Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage)))),Controller.netTakeHomeCalculation(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.grossAndDeductionCalculation(Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage)))));
       assertEquals("True",0,d);
    }


    @Test
    void homerentallowance() throws SQLException {
          int d=Controller.homerentallowance(Controller.basicCalculation(ctc,minimumwage),c.bonusCalulation(Controller.basicCalculation(ctc,minimumwage)),Controller.grossAndDeductionCalculation(Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage))),Controller.netPayCalucaltion(Controller.grossCalculation(ctc,Controller.employePfCalculation(Controller.basicCalculation(ctc,minimumwage)),Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage))),Controller.employeePf(Controller.basicCalculation(ctc,minimumwage)),Controller.employerEsiCalculation(Controller.gratutityCalculation(Controller.basicCalculation(ctc,minimumwage)))),state);
          assertEquals("True",3833,d);
    }
}