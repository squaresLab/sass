public class Plan1571769556736 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {

}


}


}
}
