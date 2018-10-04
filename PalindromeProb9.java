class PalindromProb9 {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        else{
            int new_int =x,rev;
            String new_string="";
            while(new_int>0){
                new_string=Integer.toString(new_int%10);
                new_int/=10;
            }
            rev=Integer.parseInt(new_string);
            if(rev==x)
                return true;
            else
                return false;
        }
    }
    public static void main(String[] args)throws Exception
	{
    	System.out.println(new PalindromProb9().isPalindrome(121));
    }    
}
