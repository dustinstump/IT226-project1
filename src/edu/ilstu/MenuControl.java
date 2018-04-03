package edu.ilstu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuControl
{
    private Scanner keyboard = new Scanner(System.in);
    private String filename, studentID;
    private String sortInfo[];
    private LinkedList addData;
    private ArrayList<LinkedList> repository = new ArrayList<LinkedList>();
    public void userMenu()
    {
        String choice = "";
        while(!choice.equals("E"))
        {
            System.out.println("Menu Options:\nA. Add Data\nG. Get Grades\nS. Save Data\nE. Exit");
            System.out.print("Operation: ");
            choice = keyboard.nextLine();
            String temp = choice.toUpperCase();
            switch(temp)
            {
            case "A":
                addData();
                break;
            case "G":
                gatherGrades();
                break;
            case "S":
                saveData();
                break;
            case "E":
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("\"" + choice + "\"" +  " is not a valid menu option\nPlease try again\n");
            }
            choice = temp;
            //to add a short pause between the reloop
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
            }
        }
    }


    private void addData()
    {
        boolean addInfo = true;
        System.out.print("Enter Filename: ");
        filename = keyboard.nextLine();
        sortInfo = filename.split("-");
        sortInfo[0] = "IT "+ sortInfo[0];
        if(sortInfo.length==3 && sortInfo[2].contains("."))
        {
            sortInfo[2]=sortInfo[2].substring(0,sortInfo[2].indexOf("."));
            for(int i=0; i<repository.size();i++)
            {
                if(repository.get(i).getCourseName().equals(sortInfo[0]) && repository.get(i).getCourseSemester().equals(sortInfo[1])
                        &&repository.get(i).getCourseYear().equals(sortInfo[2]))
                {
                    System.out.println("Course Data for \"" + filename +"\" has been already added");
                    addInfo = false;
                }
            }
        }
        if(addInfo)
        {
            fileReader();
            //System.out.println(addData.toString());
            if(!addData.equals(null))
            {
                System.out.println(addData.size() + " students added.");
                repository.add(addData);
                int total = 0;
                for(int j=0; j<repository.size();j++)
                {
                    total+=repository.get(j).size();
                }
                System.out.println(total + " students in repository");
                addData = null;
            }
        }
        System.out.println("\n\n");
    }

    private void gatherGrades()
    {
        System.out.print("Enter Course Number: ");
        sortInfo[0]  = "IT " + keyboard.nextLine().toUpperCase();
        System.out.print("Enter Semester and Year: ");
        String semesterYear[] = keyboard.nextLine().toUpperCase().split(" ");
        sortInfo[1] = semesterYear[0];
        if(semesterYear.length>2)
            sortInfo[2] = semesterYear[1];
        determineGrades();
    }

    private void determineGrades()
    {
        int[] gradeSummary = {0,0,0,0,0};
        int[] defaultArray = gradeSummary;
        String[] gradeValue = {"A", "B", "C", "D", "F"};
        if(repository.size()!=0)
        {
            if(!sortInfo[0].equals("NONE") && !sortInfo[1].equals("NONE"))
            {
                for(int i=0; i< repository.size();i++)
                {
                    if(repository.get(i).getCourseName().toUpperCase().equals(sortInfo[0]) && repository.get(i).getCourseSemester().toUpperCase().equals(sortInfo[1])
                            &&repository.get(i).getCourseYear().toUpperCase().equals(sortInfo[2]))
                    {
                        gradeSummary = repository.get(i).gradeSummary(gradeSummary);
                    }
                }
                if(gradeSummary.equals(defaultArray))
                    System.out.println("No grades matched criteria");
                else
                    for(int i=0; i<gradeSummary.length; i++)
                        System.out.println(gradeValue[i] +": " + gradeSummary[i]);
            }

            else if(!sortInfo[1].equals("NONE"))
            {
                for(int i=0; i< repository.size();i++)
                {
                    if(repository.get(i).getCourseSemester().toUpperCase().equals(sortInfo[1])&&repository.get(i).getCourseYear().toUpperCase().equals(sortInfo[2]))
                    {
                        gradeSummary = repository.get(i).gradeSummary(gradeSummary);
                    }
                }
                if(gradeSummary.equals(defaultArray))
                    System.out.println("No grades matched criteria");
                else
                    for(int i=0; i<gradeSummary.length; i++)
                        System.out.println(gradeValue[i] +": " + gradeSummary[i]);
            }


            else if (!sortInfo[0].equals("NONE"))
            {
                for(int i=0; i< repository.size();i++)
                {
                    if(repository.get(i).getCourseName().toUpperCase().equals(sortInfo[0]))
                    {
                        gradeSummary = repository.get(i).gradeSummary(gradeSummary);
                    }
                }
                if(gradeSummary.equals(defaultArray))
                    System.out.println("No grades matched criteria");
                else
                    for(int i=0; i<gradeSummary.length; i++)
                        System.out.println(gradeValue[i] +": " + gradeSummary[i]);
            }
        }
        else
            System.out.println("Repository is empty");
        System.out.println("\n\n");
    }

    private void saveData()
    {
        System.out.print("Enter Student ID: ");
        studentID = keyboard.nextLine();
        System.out.print("Enter Filename: ");
        filename = keyboard.nextLine();
        try
        {
            FileWriter outputStream = new FileWriter(filename);
            if(repository.size()>0)
            {
                for(int i = 0; i<repository.size(); i++)
                {
                    String temp = repository.get(i).getStudent(studentID);
                    //System.out.println(temp);
                    if(!temp.equals(""))
                        outputStream.write(temp+"\n");
                }
            }
            else
            {
                System.out.println("No Students are in the repository yet");
            }
            outputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("Data output to " + "\"" + filename + "\" failed");
        }

        //System.out.println(studentID+"'s information was published\n\n");
    }

    private void fileReader()
    {
        addData = new LinkedList(sortInfo[0], sortInfo[1],sortInfo[2]);
        try
        {
            Scanner inputFile = new Scanner(new File(filename));
            String[] temp = inputFile.nextLine().split(",");
            int location=0;
            boolean notFound= true;
            for(int i= 0; i<temp.length && notFound; i++)
            {
                if(temp[i].toLowerCase().equals("user id") || temp[i].toLowerCase().equals("student id"))
                {
                    location = i;
                    notFound = false;
                }
            }
            addData.addFirst(new Grade(temp), location);
            while (inputFile.hasNextLine()) {
                String studentInfo = inputFile.nextLine();
                String[] columns = studentInfo.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                columns = quoteRemover(columns);
                addData.addLast(new Grade(columns));
            }        
            inputFile.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\"" + filename + "\" is not found");
        }

    }

    private String[] quoteRemover(String[] defaultArray)
    {
        ArrayList<String> newArray = new ArrayList<String>();
        for(int i=0; i<defaultArray.length; i++)
        {
            if(defaultArray[i].contains(","))
            {
                newArray.add(defaultArray[i].substring(1,defaultArray[i].indexOf(",")));
                newArray.add(defaultArray[i].substring(defaultArray[i].indexOf(","), defaultArray[i].length()-1));
            }
            else
                newArray.add(defaultArray[i]);
        }
        String[] newStringArray = new String[newArray.size()];
        for(int i=0; i<newArray.size(); i++)
        {
            newStringArray[i] = newArray.get(i);
        }
        return newStringArray;
    }

    public void closeKeyboard()
    {
        keyboard.close();
    }
}



