public class Plan1571772129042 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

}

StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");
DecreaseTraffic("A");




}

}
}
