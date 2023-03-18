package com.example.tuitonfx;

public class Enrollment {
   private EnrollStudent[] enrollStudents;
   private int size;

   public Enrollment(int maxSize) {
      this.enrollStudents = new EnrollStudent[maxSize];
      this.size = 0;
   }
   public int getSize(){
      return this.size;
   }
   public EnrollStudent[] getEnrollStudents(){
      return this.enrollStudents;
   }

   public int find(Student student) {
      int index = 0;
      while (index < this.enrollStudents.length && !student.getProfile().equals(this.enrollStudents[index].getProfile())) {
         index++;
      }
      if (index == this.enrollStudents.length) {
         return -1;
      } else {
         return index;
      }
   }
   public void add(EnrollStudent enrollStudent) {
      if (this.size >= this.enrollStudents.length) {
         // If the array is full, create a new array with double the capacity
         EnrollStudent[] newEnrollStudents = new EnrollStudent[(this.enrollStudents.length * 2)+1];
         // Copy the existing elements to the new array
         for (int i = 0; i < this.size; i++) {
            newEnrollStudents[i] = this.enrollStudents[i];
         }
         this.enrollStudents = newEnrollStudents;
      }
      // Add the new element at the end of the array
      this.enrollStudents[this.size] = enrollStudent;
      this.size++;
      //System.out.println("current number of Students:" + this.size);
   }

   public void remove(EnrollStudent enrollStudent) {
      for (int i = 0; i < size; i++) {
         if (this.enrollStudents[i].equals(enrollStudent)) {
            this.enrollStudents[i] = this.enrollStudents[this.size - 1];
            this.enrollStudents[size - 1] = null;
            this.size--;
            return;
         }
      }
   }

   public boolean contains(EnrollStudent enrollStudent) {
      for (int i = 0; i < this.size; i++) {
         if (this.enrollStudents[i].getProfile().equals(enrollStudent.getProfile())) {
            return true;
         }
      }
      return false;
   }

   public void print() {
      System.out.println("Enrollment:");
      for (int i = 0; i < this.size; i++) {
         System.out.println(this.enrollStudents[i].toString());
      }
   }
}