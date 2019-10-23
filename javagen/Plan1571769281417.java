public class Plan1571769281417 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}

if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

}

DecreaseTraffic("A");


}
}
