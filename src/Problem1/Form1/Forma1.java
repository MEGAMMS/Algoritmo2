import tree.Node;
import Filereader;
public class Forma1 {
public static Node imp(String s){
    int cnt=0;
    int pos;
for(int i=0;i<s.length();i++){
if(s.charAt(i)=='('){
cnt++;
}
if(s.charAt(i)==')'){
    cnt--;
}
if(s.charAt(i)=='|'&& cnt==0){
pos=i;break;
}
if(s.charAt(i)=='-'&& cnt==0){
    pos=i;break;
    }
    
}
char c=s.charAt(pos);
String sl=s.substring(1,pos-2);
String sr=s.substring(pos+2,s.length()-1);
Node l=imp(sl);Node r=imp(sr);
Node n=new Node(c,l,r);
return n;
}

public static void main(String[] args) throws Exception {
    String s= Filereader.stringreader(test.txt);
    imp(s);

}
}
