import java.util.Scanner;	//For Using Scanner Class to accept input from user
public class BoothsAlgos	//Creating Class
{
	public static String zero(String str) //Appending zero method
	{
		while(str.length()<5)	//Making every number as 5 bit
		str="0"+str;
		return str;				//returning final result as string
	}
		
	public static int[] convert(String s)  //Method for converting String array into integer array
	{
		int n[]=new int[5];
		String a[]=s.split("");		//Using Split method for performing bit by bit operation
		for (int i=0;i<5;i++)
		{
			n[i]=Integer.parseInt(a[i]);	//Converting to integer and storing elements 1 by 1
		}
		return n;							//returning integer array 
	}
	public static int[] add(int m[],int z[])  //Method For addition of two inputs
	{
		int carry=0;
		int sum[]=new int[5];
		for(int i=4;i>=0;i--)			//Initialiaizng with right most and traversing till left most
		{
			sum[i]=(m[i]+z[i]+carry)%2;		//Formula for getting sum
			carry=(m[i]+z[i]+carry)/2;		//Formula for getting carry
		}
		return sum;							//returning final sum
	}
	public static int[] com(int m[])  	//Method for getting complement of input
	{
		int z[]={0,0,0,0,1};			//initializing a temp array for finding 2's complement
		
		for(int i=0;i<5;i++)
		{
			if(m[i]==0)					
				m[i]=1;					//whenever element is found 0 make it 1
			else
				m[i]=0;					//whenever element is found 1 make it 0
		}
		int comp[]=add(m,z);			//calling add method to add 1's comp with temp array to get 2's complement
		return comp;					//returning 2's complement of M
	}
	public static int[] shift(int m[],int []q,int ac[],int q1)		//Method for Implementing Booth's Algorithm principle
	{
		int md[]=new int[5];			//creating array to just store 2's complement value
		for(int c=1;c<6;c++)			//intitialzing cycle with 1 going till 5
		{
			System.out.print("Cycle "+c+"  ");
			int ac_temp[]=new int[5];
			int q_temp[]=new int[5];
			int m2[]=new int[5];		//creating 3 array of size 5 for temporary storing ac ,q and m values respectively
			
			
			for(int j=0;j < 5;j++)
			{
				m2[j]=m[j];				//copying the content of M into another array and performing operation on the new array
			}
			if(q[4]==0&&q1==1)			//checking whether Q last bit and Q-1 is 0 and 1 
			{
				int ac_add[]=add(ac,m2);
				for(int add=0;add<5;add++)
					ac[add]=ac_add[add];		//Doing AC+M storing result into temp array and then into original AC array
			}
			if((q[4]==1&&q1==1)||(q[4]==0&&q1==0)) //checking whether Q last bit and Q-1 is 0 and 0 or 1 and 1
			{}										//Doing Nothing it will be Just right shift
			if(q[4]==1&&q1==0)			//checking whether Q last bit and Q-1 is 1 and 0 
			{
				int m1[]=com(m2);		//Getting the 2's Complement of M into an array M1
				for(int j=0;j < 5;j++)
				{
					md[j]=m1[j];		//Copying the content of M1 into another array , just for displaying purpose
				}
				int ac_sub[]=add(ac,m1);
				for(int sub=0;sub<5;sub++)
					ac[sub]=ac_sub[sub];	//Doing AC+(-M) storing result into temp array and then into original AC array
			}
			
			for(int i=0;i<5;i++)
			{	
				ac_temp[i]=ac[i];		
				q_temp[i]=q[i];			//using two temporary array to store contents AC and Q values temporarily
			}
			q1=q_temp[4];				//Q's last bit will be right shifted by 1 and becomes Q-1
			for(int i=0;i<4;i++)		//For right shifting everything
			{	
				ac[0]=ac[1];			//AC's 1st bit will be AC's second bit right shifted by 1
				q[0]=ac[4];				//AC's last bit will be Q's first bit right shifted by 1
				ac[i+1]=ac_temp[i];		
				q[i+1]=q_temp[i];		//Just right shifting every content of AC and Q leaving 1st bit 
			}
			//For AC
			for(int i=0;i<5;i++)
			{
				System.out.print(ac[i]);		//Displaying AC contents Cycle by Cycle till 5
			}
			System.out.print("  ");			//For Tabular format
			//For Q
			for(int i=0;i<5;i++)
			{
				System.out.print(q[i]);			//Displaying Q contents Cycle by Cycle till 5
			}
			//For Q-1
			System.out.print("\t"+q1);			//Displaying Q-1 contents Cycle by Cycle till 5
			System.out.print("\n");				//Every cycle in new line
		}
		return md;								//Returning 2's complement integer array
	}
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);		//Creating object with name scan for accepting inputs
		int ac[]={0,0,0,0,0};						//initializing AC array with 0
		int q1=0;									//initializing Q-1 with 0
		
		System.out.print("Enter 1st No. Between 1-15 => ");
		int num1=scan.nextInt();					//Taking 1st number from user
		System.out.print("Enter 2nd No. Between 1-15 => ");
		int num2=scan.nextInt();					//Taking 2nd number from user
		
		String s1=Integer.toBinaryString(num1);		//Converting 1st number into Binary as String
		String s2=Integer.toBinaryString(num2);		//Converting 2nd number into Binary as String
			   
		s1=zero(s1);								//Appending 0's and making the input 5 bit for 1st number
		s2=zero(s2);								//Appending 0's and making the input 5 bit for 2nd number
		
		int m[]=convert(s1);						//Getting the converted integer array into M
		int q[]=convert(s2);						//Getting the converted integer array into Q
		
		int qd[]=new int[5];
		for(int i=0;i<5;i++)
			qd[i]=q[i];								//For storing the Q value for display purpose before manipulation 
		
		System.out.println("\n---- Multiplcation Table -----\n");	   
		System.out.println("State\t  AC\tQ\tQ-1");
		System.out.print("Initial  ");
		for(int i=0;i<5;i++)
		{
			System.out.print(ac[i]);				//Displaying the Initial Value Of AC
		}
		System.out.print("  ");
		for(int i=0;i<5;i++)
		{
			System.out.print(q[i]);					//Displaying the Initial Value Of Q
		}
		System.out.print("\t"+q1);					//Displaying the Initial Value Of Q-1
		System.out.print("\n");
		int md[]=shift(m,q,ac,q1);					//Calling shift method performing all operation getting the 2's Complement of M
		System.out.println("\n-------------------------------\n");	
		System.out.print("M = "+num1+" = ");		
		for(int i=0;i<5;i++)
			System.out.print(m[i]);
		System.out.print("\t-M = -"+num1+" = ");	//Displaying the value of M in decimal and Binary
		for(int i=0;i<5;i++)
		{
			System.out.print(md[i]);				//Displaying the value of -M in decimal and Binary
		}
		System.out.print("\nQ = "+num2+" = ");
		for(int i=0;i<5;i++)						//Displaying the value of Q in decimal and binary
		{
			System.out.print(qd[i]);
		}
		int result=num1*num2;						//Calculating result of two inputs in decimal
		System.out.print("\nM * Q =  "+result+"\t   = "+Integer.toBinaryString(result)); //Displaying the final result in decimal and Binary
	}
}