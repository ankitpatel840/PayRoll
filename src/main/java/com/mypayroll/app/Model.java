package com.mypayroll.app;

import java.sql.*;

public class Model {
    String E_CODE;
    String E_NAME;

    int HRA;
    int Net_Take__Home;
    int CTC;
    int Basic;
    int Bonus;

    int EmployerPF;
    int EmployerESI;
    int Gratuity;
    int Gross;
    int EmployeePF;
    int EmployeeESI;

    int ded;
    int diff;
    int PTGross;

    public String getLOC() {
        return LOC;
    }

    String LOC;
    int NET_PAY;

    public void setNET_PAY(int NET_PAY) {
        this.NET_PAY = NET_PAY;
    }

    public void setLOC(String LOC) {
        this.LOC = LOC;
    }

    public int getMINIMUM_WAGE() {
        return MINIMUM_WAGE;
    }

    int MINIMUM_WAGE;


    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet res = null;

    public void setE_CODE(String e_CODE) {
        E_CODE = e_CODE;
    }

    public void setE_NAME(String e_NAME) {
        E_NAME = e_NAME;
    }

    public int getHRA() {
        return HRA;
    }

    public void setHRA(int HRA) {
        this.HRA = HRA;
    }

    public void setNet_Take__Home(int net_Take__Home) {
        Net_Take__Home = net_Take__Home;
    }

    public void setCTC(int CTC) {
        this.CTC = CTC;
    }

    public void setBasic(int basic) {
        Basic = basic;
    }

    public void setBonus(int bonus) {
        Bonus = bonus;
    }

    public void setEmployerPF(int employerPF) {
        EmployerPF = employerPF;
    }

    public void setEmployerESI(int employerESI) {
        EmployerESI = employerESI;
    }

    public void setGratuity(int gratuity) {
        Gratuity = gratuity;
    }

    public void setGross(int gross) {
        Gross = gross;
    }

    public void setEmployeePF(int employeePF) {
        EmployeePF = employeePF;
    }

    public void setEmployeeESI(int employeeESI) {
        EmployeeESI = employeeESI;
    }

    public void setDed(int ded) {
        this.ded = ded;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public void setPTGross(int PTGross) {
        this.PTGross = PTGross;
    }


    Model() {
//
        try {

            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "ANKIT", "SYSTEM");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    boolean cheak() throws SQLException {
        String s = "select * from EMPLOYEECTC where E_NAME=? and  E_CODE=?";
        pstmt = con.prepareStatement(s);
        pstmt.setString(1, E_NAME);
        System.out.println(E_NAME);
        pstmt.setString(2, E_CODE);
        System.out.println(E_CODE);
        res = pstmt.executeQuery();
        //  res=pstmt.executeQuery();

        System.out.println(res);

       if(res.next())
       {
           E_CODE = res.getString(3);

            return true;
       }
        return false;
    }



    public boolean ctcCheak() {
        try {

            pstmt = con.prepareStatement("UPDATE EMPLOYEECTC SET CTC=? WHERE E_CODE=?");

            pstmt.setInt(1, CTC);
            pstmt.setString(2, E_CODE);
            int row = pstmt.executeUpdate();

            System.out.println(CTC);
            System.out.println(E_CODE);

            if (row == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean basic_salary() {
        try {

            pstmt = con.prepareStatement("UPDATE EMPLOYEECTC SET BASIC=?,BONUS=?,EMPLOYER_PF=?, GRATUITY=?, GROSS=?,EMPLOYER_ESI=?, EMPLOYEE_PF=?,EMPLOYEE_ESI=?,NET_PAY=?,NET_TAKE_HOME=?,PT_GROSS=?, DIFF_=?,HRA=? WHERE E_CODE=?");

            pstmt.setInt(1, Basic);
            pstmt.setInt(2, Bonus);

            pstmt.setInt(3, EmployerPF);
            pstmt.setInt(4, Gratuity);
            pstmt.setInt(5, Gross);
            pstmt.setInt(6, EmployerESI);
            pstmt.setInt(7, EmployeePF);
            pstmt.setInt(8, EmployeeESI);
            pstmt.setInt(9, NET_PAY);
            pstmt.setInt(10, Net_Take__Home);

            pstmt.setInt(11, PTGross);
            pstmt.setInt(12, diff);
            pstmt.setInt(13, HRA);

            pstmt.setString(14, E_CODE);


            int row = pstmt.executeUpdate();


            System.out.println(HRA);
            System.out.println(Basic);
            System.out.println(E_CODE);

            if (row == 1) {

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean getLocation() throws SQLException {


        String s = "select * from BRANCHES where LOCATION=?";

        pstmt = con.prepareStatement(s);


        pstmt.setString(1, LOC);
        res = pstmt.executeQuery();
        if (res.next()) {

            HRA = res.getInt(3);
            System.out.println(LOC);

            System.out.println(getHRA());

            MINIMUM_WAGE = res.getInt(2);
            System.out.println(MINIMUM_WAGE);
            return true;

        }
        return false;
    }


}


