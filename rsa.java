import java.io.*;
import java.util.*;

class rsa
{
  static int p, q, i, f, n, m;
  static int e[] = new int[100];
  static int d[] = new int[100];
  int en[] = new int[100];
  int temp[] = new int[100];
  int m1[] = new int[100];
  static String msg;

    public static int prime(int x)
    {
      int c1=0;
      if(x==0 || x==1)
      return 0;

      for(i=2;i<Math.sqrt(x);i++)
      {
        if(x%i==0)
        {
          c1++;
          break;
        }
      }
      if(c1==1)
      return 0;
      else
      return 1;
    }

    public void finde()
    {
      int k, flag=0;
      k=0;
      for(i=2;i<f;i++)
           {
               if(f%i==0)
               {
                continue;
               }
              // System.out.println(i);

                   flag=prime(i);
               if(flag==1&&i!=p&&i!=q)
               {
                   e[k]=i;
                   flag=findd(e[k]);
                   if(flag>0)
         {
            d[k]=flag;
            k++;
         }
         //System.out.println(k);
         if(k==99)
            break;
         }
      }
    }

    public int findd(int i)
    {
        int x=1;
        while(true)
        {
          x = x+f;
          if(x%i==0)
          return (x/i);
        }
    }

    public void encrypt()
    {
        int pubkey = e[0], j = 0, k, c;
        i = 0;
        int l = msg.length();
        while(i!=l)
        {
          int ch = msg.charAt(i);
          ch = ch-96;
          k = 1;
          for(j=0;j<pubkey;j++)
          {
            k = k * ch;
            k = k%n;
          }
          temp[i] = k;
          c = k+96;
          en[i] = c;
          i++;
        }
        en[i] = -1;
        System.out.println("ENCRYPTED MESSAGE :");
        for(i=0;en[i]!=-1;i++)
        System.out.print(en[i]);
    }

    public void decrypt()
    {
      int pt, prikey = d[0], c, k;
      i = 0;
      while(en[i]!=-1)
      {
          c = temp[i];
          k=1;
          for(int j=0;j<prikey;j++)
          {
            k = k*c;
            k=k%n;
          }
          pt = k+96;
          m1[i] = pt;
          i++;
      }
      m1[i] = -1;
      System.out.println("\nDECRYPTED MESSAGE :");
      for(i=0;m1[i]!=-1;i++)
      System.out.print(m1[i]);

      System.out.println();
    }

    public static void main(String args[])throws IOException
    {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Prime Numbers:");
      p = sc.nextInt();
      q = sc.nextInt();
      if(prime(p)!=1 || prime(q)!=1)
      {
        System.out.println("1 OR MORE INVALID INPUTS");
        System.exit(0);
      }

      System.out.println("ENTER MESSAGE :");
      msg = sc.next();

      n = p*q;
      f = (p-1)*(q-1);

     rsa r = new rsa();
     r.finde();
     System.out.println("POSSIBLE VALUES OF D AND E ARE:");
     for(i=0;i<m;i++)
     System.out.println(e[i] + " " + d[i]);
     r.encrypt();
     r.decrypt();
    }
}
