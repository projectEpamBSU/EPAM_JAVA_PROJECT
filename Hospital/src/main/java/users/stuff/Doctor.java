package main.java.users.stuff;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.users.Patient;
import main.java.users.User;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.PatientDB;

public class Doctor extends User {
    String department;
    boolean isHeadOfDepartment;

    public Doctor(String name, String surname, int age, String login, String password, String department, boolean isHeadOfDepartment){
        super(name, surname, age, login, password);
        this.department = department;
        this.isHeadOfDepartment = isHeadOfDepartment;
    }

    public Doctor(User user, String department, boolean isHeadOfDepartment) {
        super(user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());
        this.department = department;
        this.isHeadOfDepartment = isHeadOfDepartment;
    }

    public String showProfile() {
        return this.getName() + " " +
                this.getSurname() + '\n' +
                this.getAge() + " years old\n" +
                "Department: " + this.getDepartment() + '\n';
    }

    public void setTreatmentToPatient(Patient patient, Treatment treatment) {
        PatientDB patientDB = new PatientDB();
        patientDB.writeTreatment(patient, treatment);
    }

    public void setDiagnoseToPatient(Patient patient, String diagnose) {
        PatientDB patientDB = new PatientDB();
        patientDB.writeDiagnose(patient, diagnose);
    }

    public void setRecoverToPatient(Patient patient, boolean isRecovered) {
        PatientDB patientDB = new PatientDB();
        patientDB.writeIsRecovered(patient, isRecovered);
    }

    public String getPatientAppointments() {
        DoctorDB doctorDB = new DoctorDB();
        return doctorDB.getAppointments(this);
    }

    public Doctor() {
        this(new User(), "", false);
    }

    public String getDepartment() {
        return department;
    }

    public void setHeadOfDepartment(boolean headOfDepartment) {
        isHeadOfDepartment = headOfDepartment;
    }

    public boolean isHeadOfDepartment() {
        return isHeadOfDepartment;
    }

    @Override
    public String toString() {
        return "[" + super.toString() + "]" + " " + this.department + " " + this.isHeadOfDepartment;
    }

    @Override
    public Doctor parseString(String str) {
        int openingBrIndex = 0;
        int closingBrIndex = str.indexOf(']');

        String userString = "";
        for(int i = openingBrIndex + 1; i < closingBrIndex; i ++) {
            userString += str.charAt(i);
        }

        User user = (new User()).parseString(userString);

        String cutString = str.substring(closingBrIndex + 2);
        String[] cutStringArr = cutString.split(" ");

        String department = cutStringArr[0];
        String isHeadOfDepartment = cutStringArr[1];

        boolean isHead = (isHeadOfDepartment.equals("true"));

        return new Doctor(user, department, isHead);
    }
}