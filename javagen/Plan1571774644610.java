public class Plan1571774644610 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}

}

StartServer("C");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}



}
}
