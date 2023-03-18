package com.example.tuitonfx;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManager {
    public static final int input_size = 10;
    public static final int roster_len = 3;
    public static final int starting_amount = 0;
    String[] input = new String[input_size];
    Student[] student_roster = new Student[roster_len];
    Roster roster = new Roster(student_roster, starting_amount);
    Enrollment enrollment = new Enrollment(0);
    public TuitionManager(){

    }
    /**
     * Assigns a major based on major code.
     *
     * @param major Name of major code.
     * @return object of type Major with a specific major.
     */
    public Major assign_Major(String major){
        if(major.equalsIgnoreCase("EE")){
            Major new_major = Major.EE;
            return new_major;
        }else if(major.equalsIgnoreCase("CS")){
            Major new_major = Major.CS;
            return new_major;
        }else if(major.equalsIgnoreCase("MATH")){
            Major new_major = Major.MATH;
            return new_major;
        }else if(major.equalsIgnoreCase("BAIT")){
            Major new_major = Major.BAIT;
            return new_major;
        }else if(major.equalsIgnoreCase("ITI")){
            Major new_major = Major.ITI;
            return new_major;
        }else{
            return null;
        }
    }

    /**
     * This method checks to see if student is 16 or older.
     *
     * @param curr_date Today's date in the form of a Date object
     * @param date Date object that contains the date of birth of a specific student
     * @return True or False based on if student is atleast 16.
     */
    public boolean old_enough(Date curr_date, Date date){
        int age_target = 16;
        if(curr_date.get_year() - date.get_year() > age_target){
            return true;
        }else if(curr_date.get_year() - date.get_year() == age_target){
            if(curr_date.get_month() > date.get_month()){
                return true;
            }else if(curr_date.get_month() < date.get_month()){
                System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old.");
                return false;
            }else{
                if(curr_date.get_day() > date.get_day()){

                }else if(curr_date.get_day() < date.get_day()){
                    System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old.");
                    return false;
                }else{
                    System.out.println("They are old enough");
                    return true;
                }
            }
        }
        System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old.");
        return false;
    }

    /**
     * Assigns major object based on name of school.
     *
     * @param target Name of school.
     * @return Specified major object of type Major.
     */
    public Major retrieve_majors(String target){
        if(target.equalsIgnoreCase("SAS")){
            Major major = Major.CS;
            return major;
        }else if(target.equalsIgnoreCase("SOE")){
            Major major = Major.EE;
            return major;
        }else if (target.equalsIgnoreCase("SC&I")){
            Major major = Major.ITI;
            return major;
        }else if(target.equalsIgnoreCase("RBS")){
            Major major = Major.BAIT;
            return major;
        }else{
            return null;
        }
    }

    /**
     * Prints out students of a specific major.
     *
     * @param target Target major.
     */
    public void print_major(String target){
        int match = 0;
        Major target_major = retrieve_majors(target);
        if(target_major!= null){
            if(target_major == Major.CS){
                Student [] temp_roster = this.roster.getRoster();
                for(int i = 0; i <this.roster.getSize(); i++){
                    if(temp_roster[i].getMajor().compareTo(target_major) == match || temp_roster[i].getMajor().compareTo(Major.MATH)== match){
                        System.out.println(temp_roster[i].toString());
                    }
                }
            }else{
                Student [] temp_roster = this.roster.getRoster();
                for(int i = 0; i <this.roster.getSize(); i++){
                    if(temp_roster[i].getMajor().compareTo(target_major) == match){
                        System.out.println(temp_roster[i].toString());
                    }
                }
            }
        }else{
            System.out.println("School doesn't exist: " + target);
        }
    }

    /**
     * Checks to see if Student has a valid major, credit, and date of birth. Then creates the Student object.
     *
     * @param first_name First name of the student.
     * @param last_name Last name of the student
     * @param date_of_birth date of birth of the student.
     * @param student_major major of the student
     * @param student_cred credits of the student
     * @return Constructed student object.
     */
    public Student create_student(String first_name, String last_name, String date_of_birth, String student_major, String student_cred){
        int illegal_cred = -1;
        Date date = new Date(date_of_birth);
        Major major = assign_Major(student_major);
        if(date.isValid()&& major != null){
            Profile profile = new Profile(first_name, last_name,date);
            try{
                int credits = Integer.parseInt(student_cred);
                if(credits > illegal_cred){
                    Resident student = new Resident(profile, major, credits, 0);//Can not create abstract objects
                    return student;
                }else{
                    System.out.println("Credits completed invalid: can not be negative!");
                    return null;
                }
            }catch(NumberFormatException e) {
                System.out.println("Credits completed invalid: not an integer!");
            }
        }else if(date.isValid() == false){
            System.out.println("DOB invalid: " + date.toString()+ " not a valid calendar date!");
            if(major == null){
                System.out.println("Major code invalid: " + student_major);
            }
        }
        if(major == null){
            System.out.println("Major code invalid: " + student_major);
        }
        return null;
    }

    /**
     * Reads tokenized string into an array of inputs.
     *
     * @param st1 Tokenized string of user inputs.
     * @return array of user inputs in the form of a string array.
     */
    public String[] read_info(StringTokenizer st1){
        for(int i = 0; i < input.length; i++){
            try{
                input[i] = st1.nextToken();
            }catch(NoSuchElementException e){
                break;
            }
        }
        return input;
    }

    /**
     * Adds student to Roster object and resizes based on if there is enough space.
     *
     * @param newStudent Target student intended to be added to student array.
     */
    public boolean add_student(Student newStudent){
        if(newStudent != null) {
            if(roster.contains(newStudent)){
                return false;
                //System.out.println(newStudent.getProfile().toString()+ " is already in the roster");
            }else{
                if(roster.add(newStudent) == false){
                    roster.grow();
                    roster.add(newStudent);
                    return true;
                    //System.out.println(newStudent.getProfile().toString() + " added to the roster");
                }else{
                    return true;
                    //System.out.println(newStudent.getProfile().toString() + " added to the roster");
                }

            }

        }
        return false;
    }

    /**
     * Checks to see if the Student array is empty.
     *
     * @return True or False based on if Student array is empty.
     */
    public boolean isEmpty(){
        int empty = 0;
        if(roster.getSize() == empty){
            System.out.println("Student roster is empty!");
            return true;
        }
        return false;
    }

    /**
     * Searches for student and changes student major if student is found in student array
     *
     * @param firstName First name of student.
     * @param lastName Last name of student.
     * @param date Date of birth of student.
     * @param major Major that is meant to be changed to.
     */
    public void search_student(String firstName, String lastName, String date, String major){
        int match = 0;
        Date student_date = new Date(date);
        Profile student_profile = new Profile(firstName, lastName, student_date);
        Major student_major = assign_Major(major);
        Student []temp_arr = roster.getRoster();
        boolean found = false;
        if(student_major!=null){
            for(int i = 0; i < roster.getSize(); i++){
                if(temp_arr[i].getProfile().compareTo(student_profile) == match){
                    temp_arr[i].setMajor(student_major);
                    System.out.println(temp_arr[i].toString());
                    found = true;
                    break;
                }
            }
            if(found == false){
                System.out.println(student_profile.toString() +  " is not in the roster.");
            }
        }else{
            System.out.println("Major code invalid: " + major);
        }

    }

    public String[] scanStudent(String file_name){


        try {
            File file = new File(file_name);
            Scanner scan = new Scanner(file);
            int count = 0;
            while (scan.hasNextLine()) {
                scan.nextLine();
                count++;
            }
            String[] student_enroll = new String[count];
            int i = 0;
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNextLine()) {
                String line = scan2.nextLine();
                student_enroll[i] = line;
                i++;
            }
            return student_enroll;
        }catch(FileNotFoundException e){
            return null;
        }
    }
    public void add_AR(Profile profile, Major major, Date date,int credit){
        Resident resident = new Resident(profile, major, credit, 0);
        Date curr_date = new Date();
        if (roster.contains(resident)) {
            System.out.println(resident.getProfile().toString() + " is already in the roster.");
            return;
        }

        if (!roster.add(resident) && date.isValid() && old_enough(curr_date, date)) {
            roster.grow();
            roster.add(resident);
            System.out.println(resident.getProfile().toString() + " added to the roster");
        } else if (!date.isValid()) {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
        } else if (!old_enough(curr_date, date)) {

        } else {
            System.out.println(resident.getProfile().toString() + " is already in the roster.");
        }

    }

    public void add_AN(Profile profile, Major major, Date date, int credit){
        NonResident non_resident = new NonResident(profile, major, credit);
        Date curr_date = new Date();
        if (old_enough(curr_date, date) && !roster.add(non_resident) && date.isValid()) {
            roster.grow();
            roster.add(non_resident);
            System.out.println(non_resident.getProfile().toString() + " added to the roster.");
        } else if (!date.isValid()) {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
        } else {
            System.out.println(non_resident.getProfile().toString() + " added to the roster.");
        }
    }

    public void add_AT(Profile profile, Major major, Date date, int credit, String state){
        TriState triState = new TriState(profile, major, credit, state);
        Date curr_date = new Date();
        if (roster.contains(triState)) {
            System.out.println(triState.getProfile().toString() + " is already in the roster.");
            return;
        }
        if (!roster.add(triState) && date.isValid() && old_enough(curr_date, date)) {
            roster.grow();
            roster.add(triState);
            System.out.println(triState.getProfile().toString() + " added to the roster.");
        } else if (!date.isValid()) {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
        } else if (!old_enough(curr_date, date)) {
            System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old");
        } else {
            System.out.println(triState.getProfile().toString() + " added to the roster.");
        }
    }
    public void add_AI(Profile profile, Major major, Date date, int credit, String here, int input_num, boolean isAbroad){
        International international;
        if (input_num == 5 || here.equalsIgnoreCase("false")) {
            isAbroad = false;
            international = new International(profile, major, credit, isAbroad);
        } else {
            international = new International(profile, major, credit, isAbroad);
        }
        Date curr_date = new Date();
        if (roster.student_index(international.getProfile()) != -1) {
            System.out.println(international.getProfile().toString() + " is already in the roster.");
            return;
        }
        if (old_enough(curr_date, date) && date.isValid() && !roster.add(international)) {
            roster.grow();
            roster.add(international);
            System.out.println(international.getProfile().toString() + " added to roster");
            return;
        } else if (!date.isValid()) {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
        }
        System.out.println(international.getProfile().toString() + " added to roster");
    }
    public char[] scanCommand(){
        try {
            File file = new File("studentList.txt");
            Scanner scan = new Scanner(file);
            Scanner scan2 = new Scanner(file);
            int count = 0;
            while (scan.hasNextLine()) {
                count++;
                scan.nextLine();
            }
            char[] command = new char[count];
            int i = 0;
            while (scan2.hasNextLine()) {
                String line = scan2.nextLine();
                command[i] = line.charAt(0);
                i++;
            }
            return command;
        }catch(FileNotFoundException e){
            return null;
        }

    }

    public int credits_convert(String credits){
        try{
            int cred = Integer.parseInt(credits);
            return cred;
        }catch(NumberFormatException e){
            return -1000;
        }

    }
    public Student[] create_student_obj(String []student_enroll, char[] command){
        Student[] student_arr = new Student[student_enroll.length];
        for(int i = 0; i < student_enroll.length; i++){
            StringTokenizer st1 = new StringTokenizer(student_enroll[i], ",");
            if(command[i] == 'R'){
                String[] input = read_info(st1);
                Date date = new Date(input[3]);
                Profile profile = new Profile(input[1],input[2],date);
                Major m1 = assign_Major(input[4]);
                int resident_cred = credits_convert(input[5]);
                if(resident_cred == -1){
                    System.out.println("Invalid Credit Input");
                }else{
                    Resident resident = new Resident(profile,m1,resident_cred,0);
                    student_arr[i] = resident;
                }
            }else if(command[i] == 'N'){
                String[] input = read_info(st1);
                Date date = new Date(input[3]);
                Profile profile = new Profile(input[1],input[2],date);
                Major m1 = assign_Major(input[4]);
                int resident_cred = credits_convert(input[5]);
                if(resident_cred == -1){
                    System.out.println("Invalid Credit Input");
                }else{
                    NonResident nonResident = new NonResident(profile,m1,resident_cred);
                    student_arr[i] = nonResident;
                }
            }else if(command[i] == 'I'){
                String[] input = read_info(st1);
                Date date = new Date(input[3]);
                Profile profile = new Profile(input[1],input[2],date);
                Major m1 = assign_Major(input[4]);
                int resident_cred = credits_convert(input[5]);
                if(resident_cred == -1){
                    System.out.println("Invalid Credit Input");
                }else{
                    String away = input[6];
                    if(away.equalsIgnoreCase("true")){
                        International international = new International(profile,m1,resident_cred,true);
                        student_arr[i] = international;
                    }else{
                        International international = new International(profile,m1,resident_cred,false);
                        student_arr[i] = international;
                    }
                }
            }else if (command[i] == 'T'){
                String[] input = read_info(st1);
                Date date = new Date(input[3]);
                Profile profile = new Profile(input[1],input[2],date);
                Major m1 = assign_Major(input[4]);
                int resident_cred = credits_convert(input[5]);
                if(resident_cred == -1){
                    System.out.println("Invalid Credit Input");
                }else{
                    String state = input[6];
                    if(state.equalsIgnoreCase("NY") || state.equalsIgnoreCase("CT")){
                        TriState triState = new TriState(profile,m1,resident_cred,state);
                        student_arr[i] = triState;
                    }else{
                        System.out.println("INVALID STATE");
                    }

                }
            }
        }
        return student_arr;
    }
    public int enroll_credConvert(String num){
        try{
            int credit = Integer.parseInt(num);
            return credit;
        }catch(NumberFormatException e){
            System.out.println("Credits completed invalid: not an integer!");
            return -1000;
        }
    }
    public void printEnroll(Enrollment enrollment){
        EnrollStudent[] arr = enrollment.getEnrollStudents();
        System.out.println("** Enrollment **");
        for(int i =0; i < enrollment.getSize(); i++){

            System.out.println(arr[i].getProfile() + ": credit enrolled:" + arr[i].getCreditsEnrolled());
        }
        System.out.println("* end of enrollment *");
    }
    public void printTuition(Enrollment enrollment){
        EnrollStudent[] arr = enrollment.getEnrollStudents();
        Student[] arr_student = roster.getRoster();
        System.out.println("** Tuition due **");
        for(int i =0; i < enrollment.getSize(); i++){
            Student s1 = arr_student[roster.student_index(arr[i].getProfile())];
            DecimalFormat df = new DecimalFormat("##,###.00");
            System.out.println(arr[i].getProfile().toString() + " " + s1.specific_type()+ " enrolled " + arr[i].getCreditsEnrolled() + " credits: tuition due: $" + df.format(s1.tuitionDue(arr[i].getCreditsEnrolled())));

        }
        System.out.println("* end of tuition due *");
    }
    /**
     * Runs the entire program and reads the user inputs.
     */
    public void run() {
        int total_input = 5;
        int zero_input = 0; int first_input = 1; int second_input = 2; int third_input =3; int fourth_input = 4;
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        char []command = new char[3];
        String [] student_enroll;
        Student[] student_arr = new Student[3];
        boolean flag = false;
        int num = 0;
        while (true) {
            int limit = student_arr.length;
            if (num == limit) {
                flag = false;
                num = 0;
                System.out.println("Students loaded to the roster.");
            }
            String line;
            if (flag == false) {
                line = scanner.nextLine();
            } else {
                line = Character.toString(command[num]);
                if (line.equals("R")) {
                    line = "AR";
                } else if (line.equals("I")) {
                    line = "AI";
                } else if (line.equals("T")) {
                    line = "AT";
                } else if (line.equals("N")) {
                    line = "AN";
                }
            }
            if (line.equals("Q")) {
                break;
            }
            StringTokenizer st1 = new StringTokenizer(line, " ");
            if(st1.countTokens() == 0){
                continue;
            }
            String first = st1.nextToken();
            if (first.equals("A") && st1.countTokens() == total_input) {
                input = read_info(st1);
                Student newStudent = create_student(input[zero_input], input[first_input], input[second_input], input[third_input], input[fourth_input]);
                add_student(newStudent);
            } else if (first.equals("P")) {
                if (!isEmpty()) {
                    roster.print();
                }
            } else if (first.equals("LS")) {
                student_enroll = scanStudent(st1.nextToken());
                command = scanCommand();
                student_arr = create_student_obj(student_enroll, command);
                flag = true;
            } else if (first.equals("PS")) {
                if (!isEmpty()) {
                    roster.printByStanding();
                }
            } else if (first.equals("PC")) {
                if (!isEmpty()) {
                    roster.printBySchoolMajor();
                }
            } else if (first.equals("L")) {
                if (!isEmpty()) {
                    input = read_info(st1);
                    print_major(input[zero_input]);
                }
            } else if (first.equals("C")) {
                input = read_info(st1);
                search_student(input[zero_input], input[first_input], input[second_input], input[third_input]);
            } else if (first.equals("AR")) {
                if (flag == true) {
                    if (!roster.add(student_arr[num])) {
                        roster.grow();
                        roster.add(student_arr[num]);
                    }
                    num++;
                    continue;
                }

                if (st1.countTokens() > 1) {
                    input = read_info(st1);
                    Date date = new Date(input[2]);
                    Profile profile = new Profile(input[0], input[1], date);
                    Major major = assign_Major(input[3]);
                    int credit = enroll_credConvert(input[4]);
                    if (credit == -1000) {
                        continue;
                    } else if (credit < 0) {
                        System.out.println("Credits completed invalid: cannot be negative!");
                        continue;
                    }
                    add_AR( profile, major, date, credit);
                } else {
                    System.out.println("Missing data in line command.");
                }

            } else if (first.equals("AN")) {
                if (flag == true) {
                    if (!roster.add(student_arr[num])) {
                        roster.grow();
                        roster.add(student_arr[num]);
                    }
                    num++;
                    continue;
                }

                if (st1.countTokens() > 1) {
                    input = read_info(st1);
                    Date date = new Date(input[2]);
                    Profile profile = new Profile(input[0], input[1], date);
                    Major major = assign_Major(input[3]);
                    int credit = enroll_credConvert(input[4]);
                    if (credit == -1000) {
                        continue;
                    } else if (credit < 0) {
                        System.out.println("Credits completed invalid: cannot be negative!");
                        continue;
                    }
                    add_AN(profile, major, date, credit);
                } else {
                    System.out.println("Missing data in line command.");
                }
            } else if (first.equals("AT")) {
                if (flag == true) {
                    if (!roster.add(student_arr[num])) {
                        roster.grow();
                        roster.add(student_arr[num]);
                    }
                    num++;
                    continue;
                }

                if (st1.countTokens() > 1) {
                    if (st1.countTokens() == 3) {
                        System.out.println("Missing data in command line.");
                        continue;
                    } else if (st1.countTokens() == 5) {
                        System.out.println("Missing state code.");
                        continue;
                    }
                    input = read_info(st1);
                    Date date = new Date(input[2]);
                    Profile profile = new Profile(input[0], input[1], date);
                    Major major = assign_Major(input[3]);
                    int credit = enroll_credConvert(input[4]);
                    if (input[5].equalsIgnoreCase("NY") || input[5].equalsIgnoreCase("CT")) {
                        add_AT(profile,major, date, credit, input[5]);
                    } else {
                        System.out.println(input[5] + ": Invalid state code.");
                        continue;
                    }

                } else {
                    System.out.println("Missing data in line command.");
                }
            } else if (first.equals("AI")) {
                if (flag == true) {
                    if (!roster.add(student_arr[num])) {
                        roster.grow();
                        roster.add(student_arr[num]);
                    }
                    num++;
                    continue;
                }
                boolean isAbroad = true;
                int input_num = st1.countTokens();
                if (st1.countTokens() > 1) {
                    if (st1.countTokens() == 2) {
                        System.out.println("Missing data in line command.");
                        continue;
                    }
                    input = read_info(st1);
                    Date date = new Date(input[2]);
                    Profile profile = new Profile(input[0], input[1], date);
                    Major major = assign_Major(input[3]);
                    int credit = credits_convert(input[4]);
                    add_AI(profile,major,date, credit, input[5], input_num, isAbroad);
                } else {
                    System.out.println("Missing data in line command.");
                }
            } else if (first.equals("E")) {
                if(st1.countTokens() < 4 ){
                    System.out.println("Missing data in line command.");
                    continue;
                }
                input = read_info(st1);
                Date date = new Date(input[2]);
                Profile profile = new Profile(input[0], input[1], date);
                int credit = credits_convert(input[3]);
                if(credit == -1){
                    System.out.println("Credits enrolled is not an integer.");
                    continue;
                }
                EnrollStudent enrollStudent = new EnrollStudent(profile, credit);
                if (roster.find_student(profile)) {
                    Student[] temp = roster.getRoster();
                    Student s1 = temp[roster.student_index(profile)];
                    if (s1.isValid(credit)) {
                        if (enrollment.contains(enrollStudent)) {
                            enrollment.remove(enrollStudent);
                            enrollment.add(enrollStudent);
                            System.out.println(profile.toString() + " enrolled " + enrollStudent.getCreditsEnrolled() + " credits");
                            continue;
                        } else {
                            enrollment.add(enrollStudent);
                            System.out.println(profile.toString() + " enrolled " + enrollStudent.getCreditsEnrolled() + " credits");
                            continue;
                        }
                    } else if (!s1.isValid(credit)) {
                        System.out.println(s1.printType() + " " + credit + ": invalid credit hours.");
                        continue;
                    }
                }
                System.out.println("Cannot enroll: " + profile.toString() + " is not in the roster");
            } else if (first.equals("D")) {
                input = read_info(st1);
                Date date = new Date(input[2]);
                Profile profile = new Profile(input[0], input[1], date);
                EnrollStudent enrollStudent = new EnrollStudent(profile, 0);
                if (enrollment.contains(enrollStudent)) {
                    enrollment.remove(enrollStudent);
                    System.out.println(enrollStudent.getProfile().toString() + " dropped.");
                    continue;
                }
                System.out.println(profile.toString() + " is not enrolled.");
            } else if (first.equals("S")) {
                boolean missing_scholarship = false;
                if(st1.countTokens() < 3){
                    System.out.println("Missing data in line command.");
                    continue;
                }else if(st1.countTokens() == 3){
                    missing_scholarship = true;
                }
                int maximum_scholarship = 10000;
                int minimum_scholarship = 1;
                input = read_info(st1);
                Date date = new Date(input[2]);
                Profile profile = new Profile(input[0], input[1], date);
                Student []tempArr = roster.getRoster();
                if (roster.find_student(profile)) {
                    Student student = tempArr[roster.student_index(profile)];
                    if (student.isResident()) {
                        int scholarship;
                        if(missing_scholarship){
                            scholarship = 1;
                        }else{
                            scholarship = credits_convert(input[3]);
                        }

                        if (scholarship == -1) {
                            System.out.println("Amount is not an integer");
                            continue;
                        }
                        if (minimum_scholarship <= scholarship && scholarship <= maximum_scholarship) {
                            EnrollStudent[] temp = enrollment.getEnrollStudents();
                            EnrollStudent check_student = temp[enrollment.find(student)];
                            Student [] temp_arr = roster.getRoster();
                            if (student.isPartTime(check_student.getCreditsEnrolled())) {
                                System.out.println(student.getProfile().toString() + " part time student is not eligible for the scholarship.");
                                continue;
                            } else {
                                Resident s2 = new Resident(student.getProfile(), student.getMajor(), student.getCreditCompleted(), scholarship);
                                Student to_remove = temp_arr[roster.student_index(s2.getProfile())];
                                temp_arr[roster.student_index(s2.getProfile())] = s2;
                                roster.setRoster(temp_arr);
                                System.out.println(student.getProfile().toString() + ": scholarship amount updated.");
                                continue;
                            }
                        }
                        System.out.println(scholarship + ": invalid amount.");
                    }else{
                        System.out.println(student.getProfile().toString() + " " + student.specific_type() + " is not eligible for the scholarship");
                    }
                }else{

                    System.out.println(profile.toString() + " is not in the roster.");
                }
            } else if (first.equals("SE")) {
                int target_cred = 120;
                EnrollStudent[] temp_arr = enrollment.getEnrollStudents();
                Student[] student_roster = roster.getRoster();
                for (int i = 0; i < enrollment.getSize(); i++) {
                    EnrollStudent temp_student = temp_arr[i];
                    Student temp = student_roster[roster.student_index(temp_student.getProfile())];
                    temp.setCredit(temp.getCreditCompleted() + temp_student.getCreditsEnrolled());
                    student_roster[roster.student_index(temp_student.getProfile())] = temp;
                }
                Student[] student_roster2 = roster.getRoster();
                System.out.println("** list of students eligible for graduation **");
                for (int i = 0; i < roster.getSize(); i++) {
                    if (student_roster2[i].getCreditCompleted() >= target_cred) {
                        System.out.println(student_roster2[i].toString() + " " + student_roster2[i].printType() + student_roster2[i].specific_type());
                    }
                }

            } else if (first.equals("PE")) {
                if(enrollment.getSize() == 0){
                    System.out.println("Enrollment is empty!");
                    continue;
                }
                printEnroll(enrollment);
            } else if (first.equals("PT")) {
                printTuition(enrollment);
            } else if (first.equals("R")){
                input = read_info(st1);
                Date date = new Date(input[2]);
                Profile profile = new Profile(input[0], input[1], date);
                int student_loc = roster.student_index(profile);
                Student[] temp_arr = roster.getRoster();
                if(student_loc != -1) {
                    roster.remove(temp_arr[student_loc]);
                    System.out.println(profile.toString() + " dropped.");
                }else{
                    System.out.println(profile.toString() + " is not in the roster.");
                }

            }else{
                System.out.println(first + " is an invalid command!");
            }
        }
        System.out.println("Tuition Manager terminated.");
    }
}
