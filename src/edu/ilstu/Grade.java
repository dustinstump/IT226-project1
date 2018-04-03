package edu.ilstu;

public class Grade
{
    private String[] courseAssignmentInfo = null;
    private String studentID, grade;

    public Grade(String[] courseAssignmentInfo)
    {
        this.courseAssignmentInfo = courseAssignmentInfo;
        grade = courseAssignmentInfo[courseAssignmentInfo.length-1];
    }

    public String toString()
    {
        String toBeBuilt = "";
        if(courseAssignmentInfo !=null)
        {
            for(int i =0; i<courseAssignmentInfo.length; i++)
            {
                toBeBuilt += courseAssignmentInfo[i];
                if(i<courseAssignmentInfo.length-1)
                    toBeBuilt+= ",";
            }
        }
        return toBeBuilt;
    }

    public String getGrade()
    {
        return grade;
    }

    public String getStudentID()
    {
        return studentID;
    }
    
    public String[] getCourseAssignmentInfo()
    {
        return courseAssignmentInfo;
    }
    public void setStudentID(int studentIDPosition)
    {
        if(courseAssignmentInfo[studentIDPosition]!=null)
            this.studentID = courseAssignmentInfo[studentIDPosition];
    }


}



