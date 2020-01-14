package com.mypayroll.app;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Controller extends HttpServlet
{

    static   int hr;

    //<<<<<<<<<<<<<<<<<<<<basic Claulation Methode>>>>>>>>>>>>>>>>>>>>>>>//
    public static int basicCalculation(int ctc_session, int min_wage_session) {
        int basic;
        System.out.println(ctc_session+"?????????");
        int ctcCheck = (ctc_session * 30 / 100);
        System.out.println(ctcCheck+"////////////////////////////");
        System.out.println(">>>>>>>>>"+min_wage_session);
        if (ctcCheck > min_wage_session) {
            basic = ((ctcCheck) + ((ctcCheck) - min_wage_session) * 12 / 100 * 76 / 100);


        } else {
            if (ctcCheck > min_wage_session) {
                basic = Math.round(ctcCheck);
            } else {
                basic = Math.round(min_wage_session);
            }



        }
        System.out.println("........"+basic);
        return basic;

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<BonusCalucalation>>>>>>>>>>><>//

    public int bonusCalulation(int BasicGet) {

        return (int) (Math.round(BasicGet * 8.33 / 100));
    }

    //<<<<<<<<<<<<<<<<<<<<<<< Employee PF >>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

    public static int employePfCalculation(int basicGet) {
        return Math.round(basicGet * 12 / 100);
    }


    //<<<<<<<<<<<<<<<<<<<<<<Gratutiy>>>>>>>>>>>>>//

    public static int gratutityCalculation(int basic) {
        return (int) Math.round((basic * 4.81 / 100));
    }

    // <<<<<<<<<<<<<<<<<<<<<<Gross>>>>>>>>>>>>>>>>//
    public static int grossCalculation(int ctc_session, int employerPf, int gratuity) {
        return (int) Math.round((ctc_session - (employerPf + gratuity)) / (1 + 0.0475));
    }

    //<<<<<<<<<<<<<<Employer ESI>>>>>>>>>>>>>>>>>>>>>>//
    public static int employerEsiCalculation(int gross) {
        return (int) Math.round((gross * 4.75 / 100));
    }

    //Employee PF>>>>>>>>>>>>>>>>>>>>>>/./
    public static int employeePf(int basicGet) {
        return Math.round((basicGet*12/100));
    }


    //Employee ESI

    public static int employeeEsiCalculation(int gross) {
        return (int) Math.round((gross * 1.75 / 100));

    }

    //GrossDeduction>>>>>>>>>>>>>>>>>>>>>>>>>//
    public static int grossAndDeductionCalculation(int employee_esi, int employee_pf) {
        return employee_esi + employee_pf;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<NetPay?>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static int netPayCalucaltion(int gross, int employee_pf, int employee_esi) {
        return gross - employee_pf - employee_esi;
    }

    //Net Take Home//
    public static int netTakeHomeCalculation(int gross, int grossDed) {
        return gross - grossDed;
    }


    public static int ptGrossCalculation(int netpay, int grossDed) {
        return netpay + grossDed;
    }


    public static int differneceCalculation(int netTakeHome, int netPay) {

        return netTakeHome - netPay;
    }


    public static int homerentallowance(int basic, int bonus, int gross_deducted, int netpay, String loc) throws SQLException {


        int hra_inter_if;
        int hra;


        Model m = new Model();


        m.setLOC(loc);
        m.getLocation();
        int a=m.getHRA();

        int HRA_PERCENT = a;
        System.out.println(HRA_PERCENT);
        System.out.println(hr+"ankit");
        //System.out.println(minimumum+"patel");
        System.out.println();

        System.out.println();


        int hra_calc = netpay + gross_deducted - basic - bonus;

        hra_inter_if = Math.max(hra_calc, 0);

        int basic_mul_hra_percentage = basic * HRA_PERCENT / 100;

        if (hra_inter_if < basic_mul_hra_percentage) {

            hra = hra_calc;

        } else {
            hra = basic * HRA_PERCENT / 100;

        }

        return hra;
    }


    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {

            HttpSession session = req.getSession();


            String E_CODE = (String) session.getAttribute("ECODE_Session");

            System.out.println(E_CODE);


            String getLoc = req.getParameter("LOC");

            int ctc_session = (int) session.getAttribute("get_Ctc");
            System.out.println(ctc_session);

            System.out.println(ctc_session);


            //Baisc Salary Methode//
          //  HYDERABAD

            Model sa = new Model();
            sa.setLOC(getLoc);
            System.out.println(getLoc);
            sa.getLocation();


            hr= sa.getHRA();
            System.out.println(sa.getLocation());
            int miGOt = sa.getMINIMUM_WAGE();
            System.out.println(miGOt+"ankit");


            int basicGet = basicCalculation(ctc_session, miGOt);


            //Bonus Salary Mrethode//

            int bonusGet = bonusCalulation(basicGet);

            //EmployerPfMethode//
            int employerPf = employePfCalculation(basicGet);


            //Gratutiy//

            int getGratuity = gratutityCalculation(basicGet);

            //gross>>>>>>>>>>>>>>>//
            int getGross = grossCalculation(ctc_session, employerPf, getGratuity);

            //EmployerEsi>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
            int getEmployerEsi = employerEsiCalculation(getGross);


            //Employee PF...........................>>>>>>>>>>>>>>>>>>>>//

            double getEmployeePF = employeePf(basicGet);

            //EmployeeEsi>>>>>>>>>>>>>>>>//
            int getEmployeeEsi = employeeEsiCalculation(getGross);


            //GorossAnd Decduction//
            int getGrossAndDeduction = grossAndDeductionCalculation(getEmployeeEsi, (int) getEmployeePF);

            //NetPay Methode//
            int getNetPay = netPayCalucaltion(getGross, getEmployeeEsi, (int) getEmployeePF);

            //NetTakeHome metjhoder>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

            int getNetTakeHome = netTakeHomeCalculation(getGross, getGrossAndDeduction);

            //PT Gross//
            int getPTGross = ptGrossCalculation(getNetPay, getGrossAndDeduction);

            //Differnece>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

            int getDifference = differneceCalculation(getNetTakeHome, getNetPay);

            //HomeRenALlowance>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
            int getHRA = homerentallowance(basicGet, bonusGet, getGrossAndDeduction, getNetPay, getLoc);

            Model m = new Model();

            m.setE_CODE(E_CODE);
            m.setBasic(basicGet);
            m.setE_CODE(E_CODE);
            m.setBonus(bonusGet);
            m.setEmployerPF(employerPf);
            m.setGratuity(getGratuity);
            m.setGross(getGross);
            m.setEmployerESI(getEmployerEsi);
            m.setEmployeePF((int) getEmployeePF);
            m.setEmployeeESI(getEmployeeEsi);
            m.setDed(getGrossAndDeduction);
            m.setNET_PAY(getNetPay);
            m.setNet_Take__Home(getNetTakeHome);
            m.setPTGross(getPTGross);
            m.setDiff(getDifference);
            m.setHRA(getHRA);
            //  m.setLOC(getLoc);

            boolean status = m.basic_salary();
            System.out.println(status);

            System.out.println("calling basic function");

            if (status) {

                //Basic O/p//

                session.setAttribute("getBasic", basicGet);

                System.out.println(session.getAttribute("getBasic"));

                //Bonus o/p//


                session.setAttribute("bonusGet", bonusGet);
                // resp.sendRedirect("BonusComplite.jsp");

                //emp Pf//
                session.setAttribute("getEmployerPF", employerPf);

                //Gratituy//
                session.setAttribute("getGatutiy", getGratuity);

                //Gross>>>>>>>>>>>>>>>>>//
                session.setAttribute("getGross", getGross);

                //employer Esi//
                session.setAttribute("getEmployerEsi", getEmployerEsi);

                //employeePF//
                session.setAttribute("getEmployeePf", getEmployeePF);

                //employeePf//
                session.setAttribute("getEmloyeeEsi", getEmployeeEsi);

                //Gross and deduction//
                session.setAttribute("getGrossAndDeduction", getGrossAndDeduction);

                //<<<<<<<<<<<<<<<<<<<<<<<<<NetPay>>>>>>>>>>>>>>>>>>>>>>//

                session.setAttribute("getNetPay", getNetPay);


                //Net+Take+Home>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
                session.setAttribute("netTakeHome", getNetTakeHome);

                //PT+Gross>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
                session.setAttribute("getPTGross", getPTGross);

                //<<<<<<<<<<<<<<<<Differnece>>>>>>>>>>>>>>>>>>>>>>>>>>//
                session.setAttribute("getDiffernce", getDifference);

                session.setAttribute("getHRA", getHRA);


                resp.sendRedirect("BasicComplite.jsp");
            } else {

                resp.sendRedirect("resolve.html");
            }


        } catch (IOException | SQLException e) {
            e.printStackTrace();

        }


    }


}
