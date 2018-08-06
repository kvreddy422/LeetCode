# include <stdio.h>
# include <stdlib.h>

struct node
{
  int data;
  struct node *next;
};



/*Here is the code to reverse a LL. Ex: 1->2->3->4->5->6->7 turns out to be 7->6->5->4->3->2->1*/

struct node *reverseLL (struct node *head)
{
	struct node *next_node=NULL,*prev=NULL;
	while(head!=NULL)
	{
		next_node=head->next;
		head->next=prev;
		prev=head;
		head=next_node;	
	}
return prev;
}

void insertBegin(struct node **head, int data)
{
  struct node* new_node= (struct node*)malloc(sizeof(struct node));
  
  new_node->data=data;
  new_node->next=(*head);
  (*head)=new_node;

}
void insertEnd(struct node *head, int data)
{
  struct node* new_node=(struct node*)malloc(sizeof(struct node));
  
  new_node->data=data;
  
  while(head->next!=NULL)
    head=head->next;
  head->next=new_node;
  new_node->next=NULL;
}

void insertMiddle(struct node *head,int data,int pos_data,int flag)
{
  struct node* new_node=(struct node*) malloc(sizeof(struct node));
  new_node->data=data;
  if(flag==0)
    {
        int i=0;
	while(head->next!=NULL && i<=pos_data)
		{
			head=head->next;
			i++;
		}
        if(pos_data>i+1)
		printf("Cannot insert %d at the middle\n",data);
        else
	{  
		new_node->next=head->next;
       		head->next=new_node;
	}    		
    }
  else
    {
	while(head->next!=NULL)
	{
		if(head->data==data)
		{
			break;
		}
		else
		head=head->next;
	}
	if(head->data==data)
	{
		new_node->next=head->next;
		head->next=new_node;
	}
	else
		printf("Node with the given data not found \n");				
    }		
}
void printList(struct node *head)
{
  while(head->next!=NULL)
  {
  printf("%d->",head->data);
  head=head->next;
  }
  printf("%d",head->data);
  printf("\n");
}

struct node* addTwoNumbers(struct node* l1, struct node* l2) {
int count=0;
struct node *N = malloc(sizeof(struct node));
struct node *N_header=N,*prev;
while(l1 && l2){
	if(l1->data+l2->data+count<=9){
		N->data=l1->data+l2->data+count;
		prev=N;
		N->next = malloc(sizeof(struct node));
		N=N->next;
		l1=l1->next;
		l2=l2->next;
		count=0;
	}
	else{
		N->data=(l1->data+l2->data+count)%10;
		prev=N;
		N->next = malloc(sizeof(struct node));
		N=N->next;
		l2=l2->next;
		l1=l1->next;
		count=1;
		
	}
}
while(l1){
	N->data=l1->data;
	N->next = malloc(sizeof(struct node));
	prev=N;
	N=N->next;
	l1=l1->next;
}
while(l2){
	N->data=l2->data;
	N->next = malloc(sizeof(struct node));
	prev=N;	
	N=N->next;
	l2=l2->next;
}
if(count==1){
	N->next = malloc(sizeof(struct node));
	N->data=1;
	prev=N;
	N=N->next;
}
if(N->data==0)
	prev->next=NULL;
return N_header;
}


int main()
{
  struct node *l1 = NULL;
  // inserts 7 at the begining ; LL = 7->NULL 
  insertBegin(&l1,2);
  // inserts 5 at the begining ; LL = 5->7->NULL
  insertEnd(l1,2);
  insertEnd(l1,8);
  insertEnd(l1,4);
  insertEnd(l1,7);
insertEnd(l1,2);
  insertEnd(l1,0);
  insertEnd(l1,3);
  insertEnd(l1,4);
insertEnd(l1,1);
// inserts 7 at the begining ; LL = 7->NULL 
 struct node *l2 = NULL;
  insertBegin(&l2,8);
  // inserts 5 at the begining ; LL = 5->7->NULL
  insertEnd(l2,7);
  insertEnd(l2,9);
  insertEnd(l2,2);
  insertEnd(l2,7);
  insertEnd(l2,6);
  insertEnd(l2,7);
  printf("LL adding \n");		
  //printList(head);
  struct node *head=addTwoNumbers(l1,l2);
  printList(head);	
  return 0;  
}
/*
[2,2,8,4,7,2,0,3,4,1]
[8,7,9,2,7,6,7]
[0,0,8,7,4,9,7,3,4,1]
*/
