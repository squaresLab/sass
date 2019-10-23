public class Plan1571771794257 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("B");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

DecreaseTraffic("A");

}



}
}
