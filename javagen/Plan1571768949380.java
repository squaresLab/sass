public class Plan1571768949380 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

StartServer("A");

} else {
StartServer("B");
}


}

}
}
