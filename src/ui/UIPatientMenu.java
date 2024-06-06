package ui;

import model.Doctor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UIPatientMenu {
    public static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome " + UIMenu.patinetLogged.getName());
            System.out.println("1.Book an appointment");
            System.out.println("2.My appointments");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppoinmentMenu();
                    break;
                case 2:
                    showPatientMyAppointments();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }
        }while(response != 0);
    }
    private static void showBookAppoinmentMenu(){
        int response = 0;
        do{
            System.out.println("::Book an appoinment");
            System.out.println(":: Select date: ");
            //numeracion de la listya de fechas
            //indice de la fecha que seleccione nustro poasiente
            //[doctors]
            //1.doctor1
                //-1 fecha 1
                //-2 fecha 2
            //2.doctor2
            //3.doctor3
            Map<Integer,Map<Integer, Doctor>>doctors = new TreeMap<>();
            int k=0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvailableAppointments.size(); i++) {
                ArrayList<Doctor.AvailableAppointment> availableAppointments
                        = UIDoctorMenu.doctorsAvailableAppointments.get(i).getAvailableAppointments();

                Map<Integer, Doctor> doctorAppoiments = new TreeMap<>();
                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k +". " + availableAppointments.get(j).getDate());
                    doctorAppoiments.put(Integer.valueOf(j),UIDoctorMenu.doctorsAvailableAppointments.get(i));
                    doctors.put(Integer.valueOf(k), doctorAppoiments);
                }
            }
            Scanner sc = new Scanner(System.in);
            int responseDataSelected = Integer.valueOf(sc.nextLine());
            Map<Integer,Doctor> doctorAvaleabreSelected = doctors.get(responseDataSelected);
            Integer indexDate = 0;
            Doctor doctorSeleccted = new Doctor("","");
             for (Map.Entry<Integer,Doctor> doc :doctorAvaleabreSelected.entrySet() ) {
                indexDate = doc.getKey();
                doctorSeleccted = doc.getValue();

             }
            System.out.println(doctorSeleccted.getName() +
                    ". Date: " + doctorSeleccted.getAvailableAppointments().get(indexDate).getDate() +
                    ". Time: " +
                            doctorSeleccted.getAvailableAppointments().get(indexDate).getTime());
            System.out.println("Confirm your Appointment: \n 1. Yes \n 2. Change Data");
            response = Integer.valueOf(sc.nextLine());

            if (response == 1){
                UIMenu.patinetLogged.addAppointmentDoctors(
                        doctorSeleccted,
                        doctorSeleccted.getAvailableAppointments().get(indexDate).getDate(null),
                        doctorSeleccted.getAvailableAppointments().get(indexDate).getDate());
                    showPatientMenu();
            }

        }while (response != 0);
    }

    private static void showPatientMyAppointments(){
        int response = 0;
        do {
            System.out.println(":: My Appointments");
            if (UIMenu.patinetLogged.getAppointmentDoctors().size()==0){
                System.out.println("Don't have appointments");
                break;
            }
            for (int i = 0; i < UIMenu.patinetLogged.getAppointmentDoctors().size(); i++) {
                int j=i+1;
                System.out.println(j + ". " +
                        "Date: " + UIMenu.patinetLogged.getAppointmentDoctors().get(i).getDate() +
                        " Time: " + UIMenu.patinetLogged.getAppointmentDoctors().get(i).getTime() +
                        "\n Doctor: " + UIMenu.patinetLogged.getAppointmentDoctors().get(i).getDoctor().getName());
            }
            System.out.println("0. Return");

        }while(response !=0);
    }

}
