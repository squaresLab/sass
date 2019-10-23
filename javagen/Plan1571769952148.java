public class Plan1571769952148 extends Plan { 
public static void main(String[] args) { 
DecreaseDimmer("B");
if ( StartServer("B") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("B");
}


for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}


}

StartServer("A");


}
}
