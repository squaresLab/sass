public class Plan1571771639011 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}



}

}
}
