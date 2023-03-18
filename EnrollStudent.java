package com.example.tuitonfx;

public class EnrollStudent {
   private Profile profile;
   private int creditsEnrolled;

   public EnrollStudent(Profile profile, int creditsEnrolled) {
      this.profile = profile;
      this.creditsEnrolled = creditsEnrolled;
   }

   public Profile getProfile() {
      return profile;
   }

   public int getCreditsEnrolled() {
      return creditsEnrolled;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null || getClass() != obj.getClass()) {
         return false;
      }
      EnrollStudent other = (EnrollStudent) obj;
      return this.profile.equals(other.profile);
   }

   @Override
   public String toString() {
      return this.profile.toString() + ", Credits Enrolled: " + this.creditsEnrolled;
   }
}

