public class Plan1571768526741 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

StartServer("C");

} else {
StartServer("B");
}

}

StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}



}
}
