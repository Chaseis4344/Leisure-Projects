#include <iostream>
#include <string>
#include <cmath>
#include "string.h"
#include <fstream>

/*
  Context: Canvas has a Plugin called A+ Attendance, which my school uses for Attendance purposes
  I enjoy breaking things, so what better way to break attendance than to stroe every possible code
  it will ever generate on a single file?

  Make something that does it automatically

  WARNING DO NOT RUN THIS ON A WEB BASED COMPILER
  IT WILL CRASH YOUR COMPILER OF CHOICE
*/


using namespace std;

void swap(char* point1,char* point2)
{
  //Store first pointer in temp var
  char* temp = point1;
  //assign first pointer to second
  point1=point2;
  //assign pont to to prev point 1
  point2=temp;

}

string reverse(string const str)
{
  
    int n = str.length();
    string rev = str;
 
    for (int i=0, j=n-1; i<j; i++,j--)
    {
      swap(rev[i], rev[j]);
    }
    
    return rev;
}
 
char reVal(int num)
{
  //If between 0 and 9 take the number and add ascii for 0-9 else add ascii for A-Z
  return (num>=0&&num<=9)? ((char)(num + '0')) : (char)(num - 10 + 'A');
}
string baseConv(int base, int inputNum)
{
    // Store the result
    string result = "";
    // Repeatedly divide inputNum by base and take remainder
    while (inputNum > 0) 
    {
        // Update result
        result += reVal(inputNum % base);
        // Update inputNum
        inputNum /= base;
    }
    // Reverse the result
    reverse(result);
    return result;
}




int main()
{
  //36^5 is the max num of possible codes via Multiplication Rule
  long int max = pow(36,5);
  long int min =  1679615; //First 5-digit base 36 number in Base-10
  //File Output
  ofstream Out;
  Out.open("Attendance_Codes.txt");

  //Generate Decimal numbers from 0 to 36^5
 for(;min<max;min++)
 {
    //Convert them to Base-36 and throw them into a file with a tab
    Out<<baseConv(36,min)<<"\t";
    //add a newline
    if(min%13==0)
    {
      Out<<"\n";
    }
  }
  cout<<"Write Complete";
  return 0;
} 