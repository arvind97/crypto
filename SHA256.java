import java.io.*;
import java.util.*;

class SHA256
{
  public static void main(String args[]) throws IOException
  {
    Scanner sc = new Scanner(System.in);
    String[] w = new String[64];
    String msg1 = "", slen1 = "", msg2 = "", b0 = "", b1 = "", x1 = "", x2 = "", x3 = "", x4 = "", x5 = "", x6 = "", s0 = "", s1 = "", temp1 = "", temp2 = "", temp3 = "",
     temp4 = "", temp5 = "", temp6 = "";
    int p = 0, i, j, y=0, a, b, c , d, e, f, g, h;long t1 = 0, t2 = 0;
    System.out.println("Please Enter A Message");
    String msg = sc.next();
    byte[] s =msg.getBytes("UTF-8");
    for(byte b11:s)
    {

      msg1= msg1+Integer.toBinaryString(b11);
    }
  //  System.out.println(msg1);
  //  System.out.println(msg1.length());
    int len = msg.length() * 8;
    String slen = Integer.toHexString(len);

    //System.out.println(slen);
    while(slen.length()%16!=0)
    slen = 0 + slen;
    //System.out.println(slen);

    //INITIALIZING HASH VALUES
  int h0 = 0x6a09e667;
  int h1 = 0xbb67ae85;
  int h2 = 0x3c6ef372;
  int h3 = 0xa54ff53a;
  int h4 = 0x510e527f;
  int h5 = 0x9b05688c;
  int h6 = 0x1f83d9ab;
  int h7 = 0x5be0cd19;

  //INITIALIZING ARRAY OF ROUND CONSTANTS
  int k[] = {0x428a2f98,0x71374491,0xb5c0fbcf,0xe9b5dba5,0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
              0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
              0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
              0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
              0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
              0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
               0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
                0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2};

  msg1 = msg1 + 1;
  while(msg1.length() % 512 != 448)
  {
    msg1 = msg1 +0;
  }

 while(p<slen.length())
  {
    String x = Integer.toBinaryString(slen.charAt(p));
     slen1 = slen1 + x.substring(2);
     p++;
  }
  msg1 = msg1 + slen1;

  //System.out.println("\n" +msg1);
  //System.out.println("\n");
  for(i=4;i<=512;i+=4)
  {
    msg2 = msg2 + Integer.toHexString(Byte.parseByte(msg1.substring(i-4,i),2));
  }

  for(i=0;i<=15;i++)
  {
    w[i] = msg2.substring(y,y+8);
    y+=8;
    //System.out.println(w[i]);
  }

//System.out.println();
  for(i=16;i<=63;i++)
  {
    temp1 = temp2 = temp3 = w[i-15];
    for(j=1;j<=7;j++)
    {
        temp1 = temp1.charAt(temp1.length()-1) + temp1.substring(0,temp1.length()-1);
    }
    x1 = temp1;

     //temp2 = w[i-15];
    for(j=1;j<=18;j++)
    {
       temp2 = temp2.charAt(temp2.length()-1) + temp2.substring(0,temp2.length()-1);
    }
    x2 = temp2;

  //  System.out.println(w[i-15]);

     long m1 = Long.parseLong(w[i-15],16);

    m1 = m1>>3;
    temp3 = Long.toHexString(m1);
    x3 = temp3;

    s0 = Long.toHexString(Long.parseLong(x1,16)^Long.parseLong(x2,16)^Long.parseLong(x3,16));
    //System.out.println(s0);
    temp4 = w[i-15];
    for(j=1;j<=17;j++)
    {
      temp4 = temp4.charAt(temp4.length()-1) + temp4.substring(0,temp4.length()-1);
    }
    x4 = temp4;

    temp5 = w[i-15];
    for(j=1;j<=19;j++)
    {
      temp5 = temp5.charAt(temp5.length()-1) + temp5.substring(0,temp5.length()-1);
    }
    x5 = temp5;

    temp6 = w[i-15];
    long m2 = Long.parseLong(w[i-15],16);
    m2 = m2>>10;
    temp6 = Long.toHexString(m2);
    x6 = temp6;

    s1 = Long.toHexString(Long.parseLong(x4,16)^Long.parseLong(x5,16)^Long.parseLong(x6,16));
    w[i] = Long.toHexString(Long.parseLong(w[i-16],16) + Long.parseLong(s0,16) + Long.parseLong(w[i-7],16) +  Long.parseLong(s1,16));
    //System.out.println(w[i]);
  }

  a = h0;
  b = h1;
  c = h2;
  d = h3;
  e = h4;
  f = h5;
  g = h6;
  h = h7;

  for(i=0;i<=63;i++)
  {
    temp1 = temp2 = temp3 = Integer.toHexString(e);
    for(j=1;j<=6;j++)
    {
       temp1 = temp1.charAt(temp1.length()-1) + temp1.substring(0,temp1.length()-1);
    }


    for(j=1;j<=11;j++)
    {
      temp2 = temp2.charAt(temp2.length()-1) + temp2.substring(0,temp2.length()-1);
    }

    for(j=1;j<=25;j++)
    {
      temp3 = temp3.charAt(temp3.length()-1) + temp3.substring(0,temp3.length()-1);
    }

    s1 = Long.toHexString(Long.parseLong(temp1,16)^Long.parseLong(temp2,16)^Long.parseLong(temp3,16));

    long ch = (e&f) ^ ((~e) & g);
    t1 = h + Long.parseLong(s1,16) + ch + k[i] + Long.parseLong(w[i],16);

    temp1 = temp2 = temp3 = Integer.toHexString(a);

    for(j=1;j<=2;j++)
    {
       temp1 = temp1.charAt(temp1.length()-1) + temp1.substring(0,temp1.length()-1);
    }
    for(j=1;j<=13;j++)
    {
       temp2 = temp2.charAt(temp2.length()-1) + temp2.substring(0,temp2.length()-1);
    }
    for(j=1;j<=22;j++)
    {
       temp3 = temp3.charAt(temp3.length()-1) + temp3.substring(0,temp3.length()-1);
    }

    s0 = Long.toHexString(Long.parseLong(temp1,16)^Long.parseLong(temp2,16)^Long.parseLong(temp3,16));
    int maj = (a&b) ^ (a&c) ^ (b&c);
    t2 = Long.parseLong(s1,16) + maj;
  }

  h = g;
  g = f;
  f = e;
  e = d + (int)t1;
  d = c;
  c = b;
  b = a;
  a = (int)t1 + (int)t2;

   //Add the compressed chunk to the current hash value:
   h0 = h0 + a;
   h1 = h1 + b;
   h2 = h2 + c;
   h3 = h3 + d;
   h4 = h4 + e;
   h5 = h5 + f;
   h6 = h6 + g;
   h7 = h7 + h;

   String hash = Integer.toHexString(h0) + Integer.toHexString(h1) + Integer.toHexString(h2) + Integer.toHexString(h3) +
                 Integer.toHexString(h4) + Integer.toHexString(h5) + Integer.toHexString(h6) + Integer.toHexString(h7);

                 System.out.println("Hashed Message => " + hash);
                 }

  }
