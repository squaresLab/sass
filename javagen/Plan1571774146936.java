public class Plan1571774146936 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}


}

StartServer("A");

}
}
