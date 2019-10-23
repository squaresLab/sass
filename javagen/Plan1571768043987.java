public class Plan1571768043987 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( ShutdownServer("A") ) {
ShutdownServer("A");
DecreaseTraffic("A");

} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}

}
}
