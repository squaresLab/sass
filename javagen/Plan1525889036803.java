public class Plan1525889036803 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("B") ) {
if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}


}

}

} else {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
ShutdownServer("B");
}

DecreaseTraffic("B");

}

}

}
}
