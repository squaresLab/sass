public class Plan1571773441738 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

StartServer("C");

for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}

} else {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


}

DecreaseDimmer("A");

}



}
}
