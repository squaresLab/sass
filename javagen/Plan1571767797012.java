public class Plan1571767797012 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("C");
}

StartServer("B");

StartServer("C");
for (int i = 0; i < 5 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}

StartServer("B");

}



}
}
