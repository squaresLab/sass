public class Plan1571769557245 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 5 ; i++) {
DecreaseTraffic("C");
}

if ( ShutdownServer("C") ) {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("C") ) {
ShutdownServer("C");
} else {
DecreaseTraffic("C");
}

}

} else {
ShutdownServer("C");
}


}

}
}
