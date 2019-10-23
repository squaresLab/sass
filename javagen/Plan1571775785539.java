public class Plan1571775785539 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}


if ( ShutdownServer("A") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

} else {
for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}

}


}
}
