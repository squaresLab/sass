public class Plan1571771712718 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");
StartServer("A");


DecreaseTraffic("A");

StartServer("C");

}

StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("B") ) {
if ( IncreaseTraffic("C") ) {
DecreaseTraffic("C");
} else {
StartServer("A");
}

} else {
StartServer("A");
}

} else {
StartServer("B");
}



}
}
