public class Plan1571767780670 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


}

if ( DecreaseDimmer("B") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

}

} else {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
ShutdownServer("A");
}

}

} else {
DecreaseTraffic("A");
}

}



}
}
