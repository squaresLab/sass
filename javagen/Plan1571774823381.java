public class Plan1571774823381 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}


}

}
}
