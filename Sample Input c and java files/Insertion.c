		/*
		Title		: Insertion Sorting		Date of performance :	10/01/2018
		Description	: To Perform insertion sorting technique
		Student name: Shaikh Shadab Ali		Roll No:	17DCO74
		*/
		#include<stdio.h>   //for using functions like printf

		int main()
		{
			int a[20],i,j,k,n;     //declaration of variables
			printf("Enter the size of an array");
			scanf("%d",&n);   //taking the inputs from user of array size
			for(i=0;i<n;i++)        
			{
				printf("Enter the elements one by one");
				scanf("%d",&a[i]);     //accepting the values one by one
			}
			for(i=1;i<n;i++)        
			{
				k=a[i];          //assigning the key value
				j=i-1;           //initializing j variable
				while(j>=0&&a[j]>k)      
				{
					a[j+1]=a[j];
					j--;			//shifting such that j should be 								//greater than 0
				}
				a[j+1]=k;			//assigning the key value to the 								//vacant position
			}
			printf("Sorted Array\n");
			for(i=0;i<n;i++)
			{
				printf("%d\n",a[i]);		//finally printing the 										//sorted array
			}
		}
		
		/*
		Enter the size of an array5
		Enter the elements one by one5
		Enter the elements one by one4
		Enter the elements one by one3
		Enter the elements one by one2
		Enter the elements one by one1
		Sorted Array
		1
		2
		3
		4
		5


		------------------
		(program exited with code: 0)
		Press return to continue
		
		 */
					
