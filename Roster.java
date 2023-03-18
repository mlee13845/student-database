package com.example.tuitonfx;
/**
    This program creates and allows access to an array of Student objects as well as lets users manipulate the size of
    the array, search for students, add students and remove students. Lastly, this program allows for sorting the
    Student array based on School/Major, Standing and Students.

    @author Michael Lee
 */
public class Roster {
    private Student [] roster;
    private int size;

    /**
     * Constructor for the Roster object.
     *
     * @param roster Array of Student objects.
     * @param size Number of students in Student object array.
     */
    public Roster (Student[] roster, int size){
        this.roster = roster;
        this.size = size;
    }

    /**
     * Getter method for the array of students in Roster object.
     *
     * @return Array of Student objects.
     */
    public Student[] getRoster(){

        return this.roster;
    }
    public void setRoster(Student[]roster){
        this.roster = roster;
    }
    /**
     * Getter method for the number of students in Student roster array.
     *
     * @return Number of students in roster array.
     */
    public int getSize(){

        return this.size;
    }

    /**
     * Searches Student array for a specified Student object
     *
     * @param student Target Student object.
     * @return The index of Student object being searched for. -1 for if student was not found.
     */
    public int find(Student student){
        int not_found = -1;
        for(int i = 0; i< this.size; i++){
            if(this.roster[i].equals(student)){
                return i;
            }
        }
        return not_found;
    }

    /**
     * Resizes Student array by +4
     */
    public void grow(){
        int addition_space = 4;
        int newSize = this.roster.length + addition_space;
        Student[] resize_roster = new Student[newSize];
        for(int i = 0; i < this.size; i++){
            resize_roster[i] = this.roster[i];
        }
        this.roster = resize_roster;
    }

    /**
     * Adds a student object to the Student array
     *
     * @param student Student object being added to Student array.
     * @return True or False based on whether the student was successfully added.
     */
    public boolean add(Student student){
        int increase_size = 1;
        for(int i = 0; i < this.roster.length; i++){
            if(this.roster[i]== null){
                this.roster[i] = student;
                this.size = this.size +1;
                return true;
            }
        }
        if(this.size == 0){
            this.roster[0] = student;
            this.size = this.size +increase_size;
            return true;
        }
        return false;
    }

    /**
     * Removes student object from the student array.
     *
     * @param student Student intended to be removed.
     * @return  True or False based on if student was successfully removed
     */
    public boolean remove(Student student){
        for(int i = 0; i< this.size+1; i++){
            if(this.roster[i] == null){
                return false;
            }
            if(this.roster[i].equals(student)){
                Student s1 = this.roster[this.size-1];
                this.roster[i] = s1;
                this.roster[this.size] = null;
                this.size--;
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks to see if the student array contains a specified student.
     *
     * @param student student meant to be searched for.
     * @return True or False based on if student was found or not.
     */
    public boolean contains(Student student){
        for(int i = 0; i <this.size; i++){
            if(student.equals(roster[i])){
                return true;
            }
        }
        return false;
    }

    public boolean find_student(Profile profile){
        for(int i = 0; i < this.size; i++){
            if(profile.equals(roster[i].getProfile())){
                return true;
            }
        }
        return false;
    }
    public int student_index(Profile profile){
        for(int i = 0; i < this.size; i++){
            if(profile.equals(roster[i].getProfile())){
                return i;
            }
        }
        return -1;
    }
    /**
     * Sorts & prints student array based on last name, first name and date of birth
     */
    public String print(){
        String list = " ";
        int index_shift = 1;
        int limit = 0;
        for (int i = 1; i < this.size; ++i) {
            Student key = this.roster[i];
            int j = i - index_shift;

            while (j >= limit && this.roster[j].getProfile().compareTo(key.getProfile())>limit) {
                roster[j + index_shift] = roster[j];
                j = j - index_shift;
            }
            roster[j + 1] = key;
        }
        System.out.println("** Student roster sorted by last name, first name, DOB **");
        list = list + "** Student roster sorted by last name, first name, DOB **\n";
        for(int i = 0; i < this.size; i++){
            list = list + roster[i].toString() + " " + roster[i].specific_type() + "\n";
        }
        list = list + "* end of roster *";
        System.out.println(list);
        return list;
    }

    public String print2(){
        String list = " ";
        int index_shift = 1;
        int limit = 0;
        for (int i = 1; i < this.size; ++i) {
            Student key = this.roster[i];
            int j = i - index_shift;

            while (j >= limit && this.roster[j].getProfile().compareTo(key.getProfile())>limit) {
                roster[j + index_shift] = roster[j];
                j = j - index_shift;
            }
            roster[j + 1] = key;
        }
        System.out.println("** Student roster sorted by last name, first name, DOB **");
        list = list + "** Student roster sorted by last name, first name, DOB **\n";
        for(int i = 0; i < this.size; i++){
            list = list + roster[i].toString() + " " + "\n";
        }
        list = list + "* end of roster *";
        System.out.println(list);
        return list;
    }

    /**
     * Sorts and prints student array based on School/Major.
     */
    public String printBySchoolMajor(){
        String list = " ";
        int index_shift = 1;
        int limit = 0;
        for (int i = 1; i < this.size; ++i) {
            Student key = this.roster[i];
            int j = i - index_shift;

            while (j >= limit && this.roster[j].getMajor().compareTo(key.getMajor())>limit) {
                roster[j + index_shift] = roster[j];
                j = j - index_shift;
            }
            roster[j + index_shift] = key;
        }
        System.out.println("** Student roster sorted by school, major **");
        list = list + "** Student roster sorted by school, major **\n";
        for(int i = 0; i < this.size; i++){
            System.out.println(roster[i].toString());
            list = list + roster[i].toString() + " " + "\n";

        }
        System.out.println("* end of roster *");
        list = list + "* end of roster *";
        return list;
    }

    /**
     * Sorts and prints students based on standing/grade level.
     */
    public String printByStanding(){
        String list = " ";
        int index_shift = 1;
        int limit = 0;
        for (int i = 1; i < this.size; ++i) {
            Student key = this.roster[i];
            int j = i - index_shift;

            while (j >= limit && this.roster[j].getGrade().compareTo(key.getGrade()) > limit) {
                roster[j + index_shift] = roster[j];
                j = j - index_shift;
            }
            roster[j + index_shift] = key;
        }
        System.out.println("** Student roster sorted by standing **");
        list = list + "** ** Student roster sorted by standing ** **\n";
        for(int i = 0; i < this.size; i++){
            System.out.println(roster[i].toString());
            list = list + roster[i].toString() + " " + "\n";
        }
        System.out.println("* end of roster *");
        list = list + "* end of roster *";
        return list;
    }

}
