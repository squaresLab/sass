public class Plan1571772072351 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( ShutdownServer("A") ) {
IncreaseDimmer("A");
DecreaseDimmer("A");

} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}

}
}
