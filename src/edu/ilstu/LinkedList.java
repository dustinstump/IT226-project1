package edu.ilstu;

public class LinkedList
{
    private Node head;
    private int listCount;
    private int studentIdPosition;
    private String courseName, courseSemester, courseYear;
    public LinkedList(String courseName)
    {
        this.courseName = courseName;
    }

    public LinkedList(String courseSemester, String courseYear)
    {
        this.courseSemester = courseSemester;
        this.courseYear = courseYear;
    }

    public LinkedList(String courseName, String courseSemester,String courseYear)
    {
        this(courseSemester, courseYear);
        this.courseName= courseName;
    }

    public int size()
    {
        return listCount;
    }

    public void addFirst(Grade label, int studentIdLocation)
    {
        head = new Node(label, head);
        this.studentIdPosition=studentIdLocation;
    }

    public void addLast(Grade studentInformation)
    {
        if(head != null)
        {
            Node curr = head;

            while(curr.next != null)
            {
                curr = curr.next;
            }
            curr.next = new Node(studentInformation);
        }
        else
        {
            head = new Node(studentInformation);
        }

        listCount++;
    }

    public String toString()
    {
        String toReturn = "";
        Node curr = head;

        if(curr != null)
        {
            while(curr.next != null)
            {
                toReturn +=curr.studentInformation.toString()+"\n";
                curr = curr.next;
            }
            toReturn +=curr.studentInformation.toString();
        }
        return toReturn;
    }

    public String getStudent(String studentID)
    {
        Node curr = head;
        String toReturn = "";
        if(curr!= null)
        {
            while(!curr.studentInformation.getStudentID().contains(studentID) && curr.next!= null)
            {
                curr=curr.next;
            }
            if(curr.studentInformation.getStudentID().contains(studentID))
            {
                toReturn += studentID + ",";
                for(int i = 0; i<head.studentInformation.getCourseAssignmentInfo().length; i++)
                {
                    if(head.studentInformation.getCourseAssignmentInfo()[i].contains("Assignment")
                            ||head.studentInformation.getCourseAssignmentInfo()[i].contains("Grade"))
                    {
                        toReturn+=courseName+"-" + courseSemester.substring(0, 1)
                        + "-" + courseYear+"-"+head.studentInformation.getCourseAssignmentInfo()[i] +",";
                    }
                }
            }
        }
        return toReturn;
    }


    public int[] gradeSummary(int[] gradeSummary)
    {
        Node curr = head;
        if(curr != null)
        {
            while(curr.next != null)
            {
                if(curr.studentInformation.getGrade().toUpperCase().equals("A"))
                {
                    gradeSummary[0]++;
                }
                else if(curr.studentInformation.getGrade().toUpperCase().equals("B"))
                {
                    gradeSummary[1]++;
                }
                else if(curr.studentInformation.getGrade().toUpperCase().equals("C"))
                {
                    gradeSummary[2]++;
                }
                else if(curr.studentInformation.getGrade().toUpperCase().equals("D"))
                {
                    gradeSummary[3]++;
                }
                else if(curr.studentInformation.getGrade().toUpperCase().equals("F"))
                {
                    gradeSummary[4]++;
                }
                curr= curr.next;
            }
        }
        return gradeSummary;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getCourseSemester()
    {
        return courseSemester;
    }

    public String getCourseYear()
    {
        return courseYear;
    }
    private class Node
    {
        private Grade studentInformation;
        private Node next;

        public Node(Grade studentInformation)
        {
            this.studentInformation= studentInformation;
            studentInformation.setStudentID(studentIdPosition);
            next=null;    
        }

        public Node(Grade studentInformation, Node next)
        {
            this(studentInformation);
            studentInformation.setStudentID(studentIdPosition);
            this.next = next;
        }
    }
}
