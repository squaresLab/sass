public class Plan1571768722500 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");

} else {
StartServer("A");
}

} else {
StartServer("A");
}

}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}




}
}
