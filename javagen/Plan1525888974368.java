public class Plan1525888974368 extends Plan { 
public static void main(String[] args) { 
ShutdownServer("B");
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

if ( StartServer("A") ) {

} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("C");
}

}


}


}
}
