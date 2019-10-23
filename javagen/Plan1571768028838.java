public class Plan1571768028838 extends Plan { 
public static void main(String[] args) { 
ShutdownServer("C");
for (int i = 0; i < 5 ; i++) {
DecreaseTraffic("C");
}


if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 5 ; i++) {
DecreaseTraffic("C");
}

}

} else {
DecreaseDimmer("C");
}


}
}
