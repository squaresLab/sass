public class Plan1571775730513 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
StartServer("A");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("B");

}

}

} else {
IncreaseTraffic("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



} else {
DecreaseTraffic("C");
}

}
}
