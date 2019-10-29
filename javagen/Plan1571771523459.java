public class Plan1571771523459 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

if ( IncreaseTraffic("A") ) {
StartServer("C");
} else {
if ( DecreaseTraffic("A") ) {
IncreaseTraffic("C");
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



} else {
if ( DecreaseTraffic("A") ) {
IncreaseTraffic("C");
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



} else {
IncreaseTraffic("A");
}

}

}


}

}
}
