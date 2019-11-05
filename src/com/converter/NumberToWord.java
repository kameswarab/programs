package com.converter;

import java.util.*;

/**
 * @author KameswaraRao
 *
 */
public class NumberToWord {
	private static String AND=" and";
    private static String inputNumber;
    private static int number;
    private static List<String> unitList=  new ArrayList<>(Arrays.asList(""," One", " Two"," Three", " Four"," Five"," Six"," Seven"," Eight"," Nine"));
    private static List<String> teens=new ArrayList<>(Arrays.asList(" Ten"," Eleven"," Twelve"," Thirteen"," Fourteen"," Fifteen", " Sixteen"," Seventeen"," Eighteen"," Nineteen"));
    private static List<String> tenMultiples=new ArrayList<>(Arrays.asList(" Twenty"," Thirty"," Forty"," Fifty"," Sixty"," Seventy"," Eighty"," Ninety"));
    private static List<String> highestNumbers=new ArrayList<>(Arrays.asList("",""," Hundred"," Thousand"," Lakh"," Crore"));
    public String convertNumberToWords(int n)
    {
        inputNumber=numToString(n);
        String converted=""; 
        int pos=1; 
        boolean hun=false;
        while(inputNumber.length()> 0)
        {
            if(pos==1) /**tenth and units*/ 
            {   if(inputNumber.length()>= 2) /**two digit number*/
                {   
                 String temp=inputNumber.substring(inputNumber.length()-2,inputNumber.length());
                 inputNumber=inputNumber.substring(0,inputNumber.length()-2);
                 converted+=digits(temp);
                }
                else if(inputNumber.length()==1) /** one digit*/
                {
                 converted+=digits(inputNumber); 
                 inputNumber="";
                }
                pos++;
            }
            else if(pos==2) /**hundred position*/
            { 
                String temp=inputNumber.substring(inputNumber.length()-1,inputNumber.length());
                inputNumber=inputNumber.substring(0,inputNumber.length()-1);
                if(converted.length()> 0&&digits(temp)!="")
                {
                    converted=(digits(temp)+highestNumbers.get(pos)+AND)+converted;
                    hun=true;
                }
                else
                {
                    if
                    (digits(temp)=="");
                    else
                    converted=(digits(temp)+highestNumbers.get(pos))+converted;hun=true;
                }
                pos++;
            }
            else if(pos > 2) /**remaining nos which is paired by 2*/
            {
                if(inputNumber.length()>= 2) /**exact 2 digits*/
                {  
                 String temp=inputNumber.substring(inputNumber.length()-2,inputNumber.length());
                 inputNumber=inputNumber.substring(0,inputNumber.length()-2);
                   if(!hun&&converted.length()> 0)
                        converted=digits(temp)+highestNumbers.get(pos)+AND+converted;
                    else
                    {
                        if(digits(temp)=="")  ;
                        else
                        converted=digits(temp)+highestNumbers.get(pos)+converted;
                    }
                 }
                 else if(inputNumber.length()==1) // EXTRACT 1 DIGIT
                 {
                   if(!hun&&converted.length()> 0)
                    converted=digits(inputNumber)+highestNumbers.get(pos)+AND+converted;
                    else
                    {
                        if(digits(inputNumber)=="")  ;
                        else
                        converted=digits(inputNumber)+highestNumbers.get(pos)+converted;
                        inputNumber="";
                    }
                 }
                 pos++; 
             }
        }
        return converted;
    }
    private String digits(String temp)/**to return selected numbers in words*/
    {
        String converted="";
        for(int i=temp.length()-1;i >= 0;i--)
        {   int ch=temp.charAt(i)-48;
            if(i==0&&ch>1 && temp.length()> 1)
            converted=tenMultiples.get(ch-2)+converted;/**if ten digit starts with 2 or more*/
            else if(i==0&&ch==1&&temp.length()==2) /**if ten digit starts with 1*/
            {
                int sum=0;
                for(int j=0;j < 2;j++)
                sum=(sum*10)+(temp.charAt(j)-48);
                return teens.get(sum-10);
            }
            else
            {
                if(ch > 0)
                converted=unitList.get(ch)+converted;
            } /** for single digit*/
        }
        return converted;
    }
    private String numToString(int x)/**convert number to string*/
    {
        String num="";
        while(x!=0)
        {
            num=((char)((x%10)+48))+num;
            x/=10;
        }
        return num;
    }
    private void inputNumber()
    {
        Scanner in=new Scanner(System.in);
        try
        {
          System.out.print("Please enter number to Convert into Words (max 9 digits) : ");
          number=in.nextInt();
        }
        catch(Exception e)
        {
         System.out.println("Max number should be  999999999 (9 digits)");
         System.exit(1);
        }
    }
    public static void main(String[] args)
    {
        NumberToWord obj=new NumberToWord();
        obj.inputNumber();
        System.out.println("input in Words : "+obj.convertNumberToWords(number));
    }
	
 }
