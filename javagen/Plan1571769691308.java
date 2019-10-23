public class Plan1571769691308 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseDimmer("C") ) {
for (int i = 0; i < 3 ; i++) {
ShutdownServer("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");


}


}

}
}
