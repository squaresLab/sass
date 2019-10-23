public class Plan1571772758926 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("B");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

DecreaseTraffic("A");

}



}
}
